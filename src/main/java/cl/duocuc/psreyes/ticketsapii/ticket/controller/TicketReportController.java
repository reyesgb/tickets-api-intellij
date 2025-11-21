package cl.duocuc.psreyes.ticketsapii.ticket.controller;

import cl.duocuc.psreyes.ticketsapii.ticket.dto.TicketStatusCount;
import cl.duocuc.psreyes.ticketsapii.ticket.repository.TicketReportJdbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class TicketReportController {
    private final TicketReportJdbcRepository repository;

    @GetMapping("/tickets-by-status")
    public List<TicketStatusCount> getTicketsByStatus() {
        return repository.getCountByStatus();
    }
}
