package com.test.chatroom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chatroom")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @Autowired
    public ChatRoomController(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    // 加入聊天室的接口
    @PostMapping("/join")
    public String joinRoom(@RequestParam String roomId, @RequestParam String userId) {
        chatRoomService.joinRoom(roomId, userId);
        return "User " + userId + " joined room " + roomId;
    }

    // 退出聊天室的接口
    @PostMapping("/leave")
    public String leaveRoom(@RequestParam String roomId, @RequestParam String userId) {
        chatRoomService.leaveRoom(roomId, userId);
        return "User " + userId + " left room " + roomId;
    }
}

