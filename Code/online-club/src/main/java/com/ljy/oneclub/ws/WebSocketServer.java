package com.ljy.oneclub.ws;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value="/websocket/{toName}")
@Component
public class WebSocketServer {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();
    //private static ConcurrentHashMap<String,WebsocketTest> webSocketMap=new ConcurrentHashMap<>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    //private HttpSession httpSession;
    private String name;
    private String toName;
    /**
     * 连接建立成功调用的方法
     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig config, @PathParam("toName") String toName){
        this.session = session;
        //this.httpSession=(HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        //this.name= (String) httpSession.getAttribute("userName");
        this.toName=toName;
        //webSocketMap.put(this.name,this);

        webSocketSet.add(this);     //加入set中
        //webSocketMap.put(session.getId(),this);
        System.out.println("session name==>"+this.name);
        System.out.println("to msg name==>"+this.toName);
        addOnlineCount();           //在线数加1
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(){
        webSocketSet.remove(this);  //从set中删除
        //webSocketMap.remove(this.name);
        subOnlineCount();           //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String message,Session session) {
        System.out.println("来自客户端的消息:" + message);
//        if (webSocketMap.containsKey(this.toName)){
//            try {
//                webSocketMap.get(toName).sendMessage(message);
//                System.out.println("消息发往的sessionId==>"+webSocketMap.get(toName).session.getId());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        else {
//            System.out.println("Map没有这个名字的session==>"+this.toName);
//            System.out.println("当前Map的大小==>"+webSocketMap.size());
//        }
        //群发消息
        for(WebSocketServer item: webSocketSet){
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }

    /**
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error){
        System.out.println("发生错误");
        error.printStackTrace();
    }

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
