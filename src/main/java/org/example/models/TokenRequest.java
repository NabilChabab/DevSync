package org.example.models;

import jakarta.persistence.*;
import lombok.*;
import org.example.models.enums.Request;
import org.example.models.enums.TokenType;

@Entity
@Table(name = "token_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TokenRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Request requestType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TokenType tokenType;

    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;
}
