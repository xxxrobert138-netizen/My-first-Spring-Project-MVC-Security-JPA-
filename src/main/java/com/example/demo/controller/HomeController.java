package com.example.demo.controller;

import com.example.demo.service.UserService;
import com.example.demo.util.ConsoleHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String getHomeHTML() {
        ConsoleHelper.printInformation("home", ConsoleHelper.MessageType.MESSAGETOSERVER);
        return "home";
    }

    @GetMapping("/about")
    public String getMyStory() {
        System.out.println("about");
        return "about";
    }

    @GetMapping("/video")
    public String getVideo() {
        System.out.println("video");
        return "video";
    }

    @GetMapping("/signin")
    public String getLoginPage() {
        return "signin";
    }

    @GetMapping("/signup")
    public String getRegisterPage() {
        return "signup";
    }

    @GetMapping("/new")
    public String getNewPage() {
        ConsoleHelper.printInformation("User enter to page \"new\"", ConsoleHelper.MessageType.MESSAGETOSERVER);
        return "newsite";
    }

    @GetMapping("/tochat")
    public String getChat() {
        System.out.println("chat");
        return "chat";
    }

    @MessageMapping("/register")
    @SendTo("/topic/messages")
    public String register() {
        System.out.println("Зарегестрировался новый пользователь,id: " + 0);
        return String.format("{\"id\" : %s}", 0);
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public String processMessageFromClient(String message, SimpMessageHeaderAccessor simpMessageHeaderAccessor) throws IOException {
        System.out.println("Поступило новое сообщение: " + message);
        System.out.println("Отправлено сообощение: " + message);
        return message;
    }

}
