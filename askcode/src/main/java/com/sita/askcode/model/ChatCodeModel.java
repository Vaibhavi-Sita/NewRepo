package com.sita.askcode.model;


import com.sita.askcode.service.ChatCodeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ChatCodeModel {
    String prompt;

    public ChatCodeModel(@Value("${CHAT_DEFAULT}") String prompt){
        this.prompt = prompt;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
}
