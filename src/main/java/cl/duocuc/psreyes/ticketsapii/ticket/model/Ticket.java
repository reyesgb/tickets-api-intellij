package cl.duocuc.psreyes.ticketsapii.ticket.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(length = 2000)
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketStatus status;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketPriority priority;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @Column(nullable = false)
    private String createdBy;
    @Column(nullable = false)
    private boolean deleted = false;
}
