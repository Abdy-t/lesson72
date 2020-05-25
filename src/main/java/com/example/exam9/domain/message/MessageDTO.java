package com.example.exam9.domain.message;
import lombok.*;

@Getter
@Setter
@ToString
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageDTO {
    private Integer id;
    private String text;
    private String email;
    private String date;

    public static MessageDTO from(Message comment) {
        return builder()
                .id(comment.getId())
                .text(comment.getText())
                .email(comment.getUser().getEmail())
                .date(comment.getDate().toString())
                .build();
    }
}
