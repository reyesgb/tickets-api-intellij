package cl.duocuc.psreyes.ticketsapii.ticket.repository;

import cl.duocuc.psreyes.ticketsapii.ticket.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
