package com.sivalabs.bookstore.orders.web.controllers;

import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@Controller
@RequiredArgsConstructor
@Slf4j
class ChatSupportController {
    private final ChatClient chatClient;

    @GetMapping("/chat")
    String chat() {
        return "chat";
    }

    @HxRequest
    @PostMapping("/chat")
    String sendMessage(@RequestParam("question") String question,
                       Model model){
        log.info("Received question: {}", question);
        UserMessage userMessage = new UserMessage(question);
        ChatResponse response = chatClient.call(new Prompt(
                List.of(userMessage),
                OpenAiChatOptions.builder()
                        .withFunctions(Set.of(
                            "orderStatusEnquiryFn",
                            "orderCancellationFn")
                        )
                        .build()));

        String answer = response.getResult().getOutput().getContent();
        log.info("Answer: {}", answer);
        model.addAttribute("answer", answer);
        return "partials/chat-answer";
    }
}
