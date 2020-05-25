package com.example.exam9.domain.message;
import com.example.exam9.domain.user.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/messages")
@AllArgsConstructor
public class MessageRestController {
    private final MessageService commentService;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @GetMapping("/get")
    public List<Message> getMessages(){
        return messageRepository.findAll();
    }
    @PostMapping("/add")
    public void addMessage(@RequestParam("text") String text) {
        String userEmail =  SecurityContextHolder.getContext().getAuthentication().getName();
        Message message = new Message();
        message.setText(text);
        message.setUser(userRepository.findByEmail(userEmail).get());
        message.setDate(LocalDateTime.now());
        messageRepository.save(message);
    }
}
