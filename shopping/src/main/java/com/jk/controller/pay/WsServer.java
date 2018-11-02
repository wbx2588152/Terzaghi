package com.jk.controller.pay;


import org.springframework.stereotype.Component;


import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;


@ServerEndpoint("/webSocket/chat/{roomName}")
@Component
public class WsServer {
    // 使用map来收集session，key为roomName，value为同一个房间的用户集合
    // concurrentMap的key不存在时报错，不是返回null
    private static final Map<String, Set<Session>> rooms = new ConcurrentHashMap();
    @OnOpen
    public void connect(@PathParam("roomName") String roomName, Session session) throws Exception {
        // 将session按照房间名来存储，将各个房间的用户隔离
        if (!rooms.containsKey(roomName)) {
            // 创建房间不存在时，创建房间
            Set<Session> room = new HashSet<>();
            // 添加用户
 /*           User user = UserUtil.getUser(request);
            List<User> userlist= (List<User>)redisTemplate.boundHashOps("cartList").get("chartname");
            if(roomName.equals(user.getId())){
                userlist.add(user);
            }
            redisTemplate.boundHashOps("cartList").put("chartname", userlist);*/
            room.add(session);
            rooms.put(roomName, room);
        } else {
            // 房间已存在，直接添加用户到相应的房间
            rooms.get(roomName).add(session);
        }
        System.out.println("a client has connected!");
    }

    @OnClose
    public void disConnect(@PathParam("roomName") String roomName, Session session) {
        rooms.get(roomName).remove(session);
        /*List<User> userlist= (List<User>)redisTemplate.boundHashOps("cartList").get("chartname");
        User user = UserUtil.getUser(request);
        if(roomName.equals(user.getId())){
            for (int i = 0; i <userlist.size() ; i++) {
                if(userlist.get(i).getId().equals(user.getId())){
                    userlist.remove(i);
                    redisTemplate.boundHashOps("cartList").put("chartname", userlist);
                    System.out.println("已删除");
                    break;
                }
            }
        }*/

        System.out.println("a client has disconnected!");
    }

    @OnMessage
    public void receiveMsg(@PathParam("roomName") String roomName,
                           String msg, Session session) throws Exception {
        // 此处应该有html过滤
        msg =  msg;
        System.out.println(msg);
        // 接收到信息后进行广播
        broadcast(roomName, msg);
    }

    // 按照房间名进行广播
    public static void broadcast(String roomName, String msg) throws Exception {
        for (Session session : rooms.get(roomName)) {
            session.getBasicRemote().sendText(msg);
        }
    }

}
