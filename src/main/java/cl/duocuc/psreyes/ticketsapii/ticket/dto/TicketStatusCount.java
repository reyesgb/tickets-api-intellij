package cl.duocuc.psreyes.ticketsapii.ticket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TicketStatusCount {
    private String status;
    private Long count;
}