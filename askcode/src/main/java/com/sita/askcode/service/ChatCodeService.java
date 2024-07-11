package com.sita.askcode.service;

import com.sita.askcode.model.ChatCodeModel;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ChatCodeService {
    private final OllamaChatModel chatModel;

    private final ChatCodeModel chatCodeModel;

    @Autowired
    public ChatCodeService(OllamaChatModel chatModel, ChatCodeModel chatCodeModel) {
        this.chatModel = chatModel;
        this.chatCodeModel = chatCodeModel;
    }

    public Object generateCode(String prompt){
        Map response = Map.of("generation", chatModel.call(prompt));
        return response.get("generation");
    }
}
