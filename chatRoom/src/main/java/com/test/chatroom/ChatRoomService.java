package com.test.chatroom;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ChatRoomService {
    private final Map<String, Set<String>> chatRooms = new ConcurrentHashMap<>();

    // 加入聊天室
    public void joinRoom(String roomId, String userId) {
        chatRooms.putIfAbsent(roomId, Collections.synchronizedSet(new HashSet<>()));
        Set<String> users = chatRooms.get(roomId);

        synchronized (users) {
            users.add(userId);
            printOtherMembers(roomId, userId);
        }
    }

    // 退出聊天室
    public void leaveRoom(String roomId, String userId) {
        Set<String> users = chatRooms.get(roomId);

        if (users != null) {
            synchronized (users) {
                users.remove(userId);
                printOtherMembers(roomId, userId);
                if (users.isEmpty()) {
                    chatRooms.remove(roomId);
                }
            }
        }
    }

    // 打印聊天室内除自己之外的其他成员
    private void printOtherMembers(String roomId, String userId) {
        Set<String> users = chatRooms.get(roomId);
        System.out.println("In Room " + roomId + ", other members:");
        users.stream()
                .filter(id -> !id.equals(userId))
                .forEach(System.out::println);
    }
}
