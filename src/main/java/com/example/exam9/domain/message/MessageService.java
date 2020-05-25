package com.example.exam9.domain.message;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MessageService {
    private final MessageRepository commentRepository;

//    public List<MessageDTO> getMessages(int idMessage) {
//        var list = commentRepository.getAllByTheme_Id(idMessage);
//        List<MessageDTO> commentDTOs = new ArrayList<>();
//        for (Message c : list) {
//            commentDTOs.add(MessageDTO.from(c));
//        }
//        return commentDTOs;
//    }

//    public Page<MessageDTO> getPageComments(int idTheme, Pageable pageable) {
//        var list = commentRepository.findAllByTheme_Id(idTheme, pageable);
//        return list.map(MessageDTO::from);
//    }

    public Page<MessageDTO> getPageComments(Pageable pageable) {
        return commentRepository.findAll(pageable)
                .map(MessageDTO::from);
    }

    public MessageDTO getComment(int id) {
        return MessageDTO.from(commentRepository.findById(id).get());
    }

}
