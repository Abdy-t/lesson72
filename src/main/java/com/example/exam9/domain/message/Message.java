package com.example.exam9.domain.message;

import com.example.exam9.domain.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Table(name="messages")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 140)
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column
    private LocalDateTime date;
}
