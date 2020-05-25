package com.example.exam9.domain.message;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageService {
    private final MessageRepository commentRepository;


    public Page<MessageDTO> getPageComments(Pageable pageable) {
        return commentRepository.findAll(pageable)
                .map(MessageDTO::from);
    }

    public MessageDTO getComment(int id) {
        return MessageDTO.from(commentRepository.findById(id).get());
    }

}
