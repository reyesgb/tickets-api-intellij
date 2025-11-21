package cl.duocuc.psreyes.ticketsapii.ticket.filter;

import cl.duocuc.psreyes.ticketsapii.ticket.model.Ticket;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class TicketSpecification {
    public static Specification<Ticket> hasStatus(String status) {
        return (root, query, cb) ->
                status == null ? null : cb.equal(root.get("status"), status);
    }

    public static Specification<Ticket> hasCategory(String category) {
        return (root, query, cb) ->
                category == null ? null :
                        cb.like(cb.lower(root.get("category")), "%" + category.toLowerCase() +
                                "%");
    }

    public static Specification<Ticket> createdAfter(LocalDateTime from) {
        return (root, query, cb) ->
                from == null ? null : cb.greaterThanOrEqualTo(root.get("createdAt"), from);
    }

    public static Specification<Ticket> createdBefore(LocalDateTime to) {
        return (root, query, cb) ->
                to == null ? null : cb.lessThanOrEqualTo(root.get("createdAt"), to);
    }

    public static Specification<Ticket> notDeleted() {
        return (root, query, cb) ->
                cb.isFalse(root.get("deleted"));
    }
}
