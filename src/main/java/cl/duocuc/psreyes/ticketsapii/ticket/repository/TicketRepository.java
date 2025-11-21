package cl.duocuc.psreyes.ticketsapii.ticket.repository;

import cl.duocuc.psreyes.ticketsapii.ticket.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface TicketRepository extends JpaRepository<Ticket, Long>, JpaSpecificationExecutor<Ticket> {
}
