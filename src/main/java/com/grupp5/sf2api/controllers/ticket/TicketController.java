package com.grupp5.sf2api.controllers.ticket;

import com.grupp5.sf2api.dtos.ticket.CreateTicketDto;
import com.grupp5.sf2api.dtos.ticket.DeleteTicketDto;
import com.grupp5.sf2api.dtos.ticket.UpdateTicketDto;
import com.grupp5.sf2api.models.tickets.Ticket;
import com.grupp5.sf2api.request.ticket.CreateTicketRequest;
import com.grupp5.sf2api.request.ticket.UpdateTicketRequest;
import com.grupp5.sf2api.services.ticket.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/ticket")
@AllArgsConstructor
public class TicketController {

    private TicketService ticketService;

    @PostMapping("/create")
    public ResponseEntity<CreateTicketDto> createTicket(@RequestBody CreateTicketRequest request) {

        Ticket newTicket = ticketService.createTicket(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(CreateTicketDto.from(newTicket));
    }

    @GetMapping("/tickets")
    public ResponseEntity<List<Ticket>> createTicket() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    @PutMapping("/update/{ticketid}")
    public ResponseEntity<UpdateTicketDto> updateTicket(
            @PathVariable UUID ticketid,
            @RequestBody UpdateTicketRequest request) {
        Ticket updatedTicket = ticketService.updateTicket(ticketid, request);
        return ResponseEntity.ok(UpdateTicketDto.from(updatedTicket));
    }

    @DeleteMapping("/delete/{ticketid}")
    public ResponseEntity<DeleteTicketDto> deleteTicket(@PathVariable UUID ticketid) {
        Ticket deletedTicket = ticketService.deleteTicket(ticketid);

        return ResponseEntity.ok(DeleteTicketDto.from(deletedTicket));
    }

}
