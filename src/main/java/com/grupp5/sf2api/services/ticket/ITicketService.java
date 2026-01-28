package com.grupp5.sf2api.services.ticket;

import com.grupp5.sf2api.models.tickets.Ticket;
import com.grupp5.sf2api.request.ticket.CreateTicketRequest;
import com.grupp5.sf2api.request.ticket.UpdateTicketRequest;

import java.util.List;
import java.util.UUID;

public interface ITicketService {
    Ticket createTicket(CreateTicketRequest request);
    Ticket updateTicket(UUID ticketid, UpdateTicketRequest request);
    List<Ticket> getAllTickets();
    Ticket deleteTicket(UUID ticketid);
}
