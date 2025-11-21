package cl.duocuc.psreyes.ticketsapii.ticket.filter;

import cl.duocuc.psreyes.ticketsapii.ticket.model.TicketStatus;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
public class TicketFilter {
    private TicketStatus status;
    private String category;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime from;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime to;
}