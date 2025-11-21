package cl.duocuc.psreyes.ticketsapii.ticket.service;

import cl.duocuc.psreyes.ticketsapii.ticket.dto.TicketCreateRequest;
import cl.duocuc.psreyes.ticketsapii.ticket.dto.TicketResponse;
import cl.duocuc.psreyes.ticketsapii.ticket.dto.TicketUpdateRequest;
import cl.duocuc.psreyes.ticketsapii.ticket.filter.TicketFilter;
import cl.duocuc.psreyes.ticketsapii.ticket.filter.TicketSpecification;
import cl.duocuc.psreyes.ticketsapii.ticket.model.Ticket;
import cl.duocuc.psreyes.ticketsapii.ticket.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketResponse createTicket(TicketCreateRequest request) {
        Ticket ticket = Ticket.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .status(request.getStatus())
                .priority(request.getPriority())
                .category(request.getCategory())
                .createdBy(request.getCreatedBy())
                .createdAt(LocalDateTime.now())
                .deleted(false)
                .build();
        return mapToResponse(ticketRepository.save(ticket));
    }

    public TicketResponse getTicketById(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Ticket not found with id: " + id));
        return mapToResponse(ticket);
    }

    public Page<TicketResponse> findTickets(TicketFilter filter, Pageable pageable) {
        Specification<Ticket> spec = TicketSpecification.notDeleted()
                .and(TicketSpecification.hasStatus(
                        filter.getStatus() != null ? filter.getStatus().name() : null
                ))
                .and(TicketSpecification.hasCategory(filter.getCategory()))
                .and(TicketSpecification.createdAfter(filter.getFrom()))
                .and(TicketSpecification.createdBefore(filter.getTo()));
        return ticketRepository.findAll(spec, pageable)
                .map(this::mapToResponse);
    }


    public TicketResponse updateTicket(Long id, TicketUpdateRequest
            request) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Ticket not found with id:" + id));
        ticket.setTitle(request.getTitle());
        ticket.setDescription(request.getDescription());
        ticket.setStatus(request.getStatus());
        ticket.setPriority(request.getPriority());
        ticket.setCategory(request.getCategory());
        ticket.setUpdatedAt(LocalDateTime.now());
        return mapToResponse(ticketRepository.save(ticket));
    }

    public void deleteTicket(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Ticket not found with id:" + id));
        ticket.setDeleted(true);
        ticket.setUpdatedAt(LocalDateTime.now());
        ticketRepository.save(ticket);
    }

    private TicketResponse mapToResponse(Ticket ticket) {
        return TicketResponse.builder()
                .id(ticket.getId())
                .title(ticket.getTitle())
                .description(ticket.getDescription())
                .status(ticket.getStatus())
                .priority(ticket.getPriority())
                .category(ticket.getCategory())
                .createdAt(ticket.getCreatedAt())
                .updatedAt(ticket.getUpdatedAt())
                .createdBy(ticket.getCreatedBy())
                .build();
    }
}
