package cl.duocuc.psreyes.ticketsapii.ticket.dto;

import cl.duocuc.psreyes.ticketsapii.ticket.model.TicketPriority;
import cl.duocuc.psreyes.ticketsapii.ticket.model.TicketStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TicketResponse {
    private Long id;
    private String title;
    private String description;
    private TicketStatus status;
    private TicketPriority priority;
    private String category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
}
