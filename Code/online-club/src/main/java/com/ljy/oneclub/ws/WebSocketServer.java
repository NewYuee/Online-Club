package com.ljy.oneclub.ws;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value="/websocket/{from}")
@Component
public class WebSocketServer {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int  onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static ConcurrentHashMap<String,WebSocketServer> webSocketMap=new ConcurrentHashMap<>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    //private HttpSession httpSession;
    private String from;
    /**
     * 连接建立成功调用的方法
     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("from") String name){
        this.session = session;
        webSocketMap.put(name,this);
        addOnlineCount();           //在线数加1
        System.out.println("有新连接加入！当前在线人数为" + onlineCount);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session){
        subOnlineCount();
        System.out.println(this.from);
        System.out.println("有连接断开！当前在线人数为"+onlineCount);
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String message,Session session) {
        System.out.println("来自客户端的消息:" + message);
        JSONObject jsonObject = JSON.parseObject(message);

        System.out.println("context=>"+jsonObject.get("text").toString());
        String to=jsonObject.get("to").toString();
        String text=jsonObject.get("text").toString();
        System.out.println("fageishui=>"+to);
        if (webSocketMap.containsKey(to)){
            try {
                webSocketMap.get(to).sendMessage(text);
                //System.out.println("消息发往的sessionId==>"+webSocketMap.get(toName).session.getId());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Map没有这个名字的session==>"+this.from);
            System.out.println("当前Map的大小==>"+webSocketMap.size());
        }
        //群发消息
//        for(WebSocketServer item: webSocketSet){
//            try {
//                item.sendMessage(message);
//            } catch (IOException e) {
//                e.printStackTrace();
//                continue;
//            }
//        }
    }

    /**
     * 发生错误时调用
     * @param session
     * @param error
     */

    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException{
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
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
