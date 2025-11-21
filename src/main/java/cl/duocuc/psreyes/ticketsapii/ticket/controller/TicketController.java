package cl.duocuc.psreyes.ticketsapii.ticket.controller;

import cl.duocuc.psreyes.ticketsapii.ticket.dto.TicketCreateRequest;
import cl.duocuc.psreyes.ticketsapii.ticket.dto.TicketResponse;
import cl.duocuc.psreyes.ticketsapii.ticket.dto.TicketUpdateRequest;
import cl.duocuc.psreyes.ticketsapii.ticket.filter.TicketFilter;
import cl.duocuc.psreyes.ticketsapii.ticket.model.TicketStatus;
import cl.duocuc.psreyes.ticketsapii.ticket.service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @PostMapping
    public ResponseEntity<TicketResponse> createTicket(@Valid @RequestBody
                                                       TicketCreateRequest request) {
        return
                ResponseEntity.status(HttpStatus.CREATED).body(ticketService.createTicket(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketResponse> getTicketById(@PathVariable Long
                                                                id) {
        return ResponseEntity.ok(ticketService.getTicketById(id));
    }

    @GetMapping
    public ResponseEntity<Page<TicketResponse>> getTickets(
            @RequestParam(required = false) TicketStatus status,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime
                    from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to,
            Pageable pageable
    ) {
        TicketFilter filter = TicketFilter.builder()
                .status(status)
                .category(category)
                .from(from)
                .to(to)
                .build();
        return ResponseEntity.ok(ticketService.findTickets(filter, pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketResponse> updateTicket(
            @PathVariable Long id,
            @Valid @RequestBody TicketUpdateRequest request) {
        return ResponseEntity.ok(ticketService.updateTicket(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }
}
