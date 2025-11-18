package cl.duocuc.psreyes.ticketsapii.ticket.dto;



import cl.duocuc.psreyes.ticketsapii.ticket.model.TicketPriority;
import cl.duocuc.psreyes.ticketsapii.ticket.model.TicketStatus;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
public class TicketCreateRequest {

    @NotBlank
    private String title;
    private String description;
    @NotNull
    private TicketStatus status;
    @NotNull
    private TicketPriority priority;
    @NotBlank
    private String category;
    @NotBlank
    private String createdBy;

}
