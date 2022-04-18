package com.ljy.oneclub.ws;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ljy.oneclub.entity.Message;
import com.ljy.oneclub.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.SessionScope;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@ServerEndpoint(value="/websocket/{from}/{to}")
@Component
public class WebSocketServer {

    private static final Logger logger= LoggerFactory.getLogger(WebSocketServer.class);
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int  onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static HashMap<String,WebSocketServer> webSocketMap=new HashMap<>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    //private HttpSession httpSession;
    private String from;
    private String to;

    static MessageService messageService;

    @Autowired
    public void setMessageService(MessageService messageService){
        WebSocketServer.messageService = messageService;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    /**
     * 连接建立成功调用的方法
     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("from") String name,@PathParam("to") String to){
        this.session = session;
        this.to=to;
        this.from=name;
        webSocketMap.put(name,this);            //发送者对应的socket
        addOnlineCount();           //在线数加1
        System.out.println("用户"+name+"上线，当前在线人数为" + webSocketMap.size());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session){
        subOnlineCount();
        Iterator<String> iterator=webSocketMap.keySet().iterator();
        while (iterator.hasNext()){
            String key=iterator.next();
            if (from.equals(key)){
                iterator.remove();
                System.out.println("用户"+key+"已下线;当前在线人数:"+webSocketMap.size());
            }
        }
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String message, Session session) throws ParseException {
        JSONObject jsonObject = JSON.parseObject(message);
        String text=jsonObject.get("text").toString();
        if (text==null||text.replace(" ","").length()==0){
            logger.error("发送的消息为null或者无内容");
            return;
        }

        Message chatMsg = new Message();
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        Date time=simpleDateFormat.parse(format);
        chatMsg.setUpdateTime(time);
        chatMsg.setFromUid(from);
        chatMsg.setToUid(to);
        chatMsg.setContent(text);
        //如果两个客户端同时在线，消息已读
        if (webSocketMap.containsKey(to)&&webSocketMap.get(to).getTo().equals(from)){
            try {
                chatMsg.setIsread("1");
                int i=messageService.insertMsg(chatMsg);
                webSocketMap.get(to).sendMessage(text);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //有一方不在线，消息未读，存入数据库
        else {
            System.out.println("用户"+to+"离线");
            chatMsg.setIsread("0");
            int i=messageService.insertMsg(chatMsg);
        }
    }

    /**
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session,Throwable error){
        try{
            session.close();
        }catch (Exception e){
            logger.error("用户"+from+"向用户"+to+"聊天的WebSocket连接出错！");
        }
    }
    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException{
        this.session.getBasicRemote().sendText(message);
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}
