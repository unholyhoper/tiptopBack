package com.tiptop.users.service;
import com.tiptop.users.entities.Ticket;
import com.tiptop.users.repos.ITicketRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.when;
public class TicketServiceTest {
    @Mock
    private ITicketRepository ticketRepository;

    @InjectMocks
    private TicketService ticketService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    // Test for getMaxNumOfTickets() method
    @Test
    public void testGetMaxNumOfTickets() {
        when(ticketRepository.count()).thenReturn(10000L);

        long maxNumOfTickets = ticketService.getMaxNumOfTickets();

        Assertions.assertEquals(10000L, maxNumOfTickets);
    }

    // Test for verifyNewTicket() method with valid ticket number
    @Test
    public void testVerifyNewTicketWithValidTicketNumber() {
        when(ticketRepository.findByTicketNumber(1234567890L)).thenReturn(null);

        boolean isNewTicket = ticketService.verifyNewTicket(1234567890L);

        Assertions.assertTrue(isNewTicket);
    }

    // Test for verifyNewTicket() method with existing ticket number
    @Test
    public void testVerifyNewTicketWithExistingTicketNumber() {
        Ticket ticket = new Ticket();
        ticket.setTicketNumber(1234567890L);

        when(ticketRepository.findByTicketNumber(1234567890L)).thenReturn(ticket);

        boolean isNewTicket = ticketService.verifyNewTicket(1234567890L);

        Assertions.assertFalse(isNewTicket);
    }

    // Test for addTicket() method with valid ticket
    @Test
    public void testAddTicketWithValidTicket() {
        Ticket ticket = new Ticket();
        ticket.setTicketNumber(1234567890L);

        when(ticketRepository.save(ticket)).thenReturn(ticket);

        ResponseEntity<String> responseEntity = ticketService.addTicket(1234567890L);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals("Ticket ajouté avec succés", responseEntity.getBody());
    }

    // Test for addTicket() method with max number of tickets reached
    @Test
    public void testAddTicketWithMaxNumOfTicketsReached() {
        when(ticketRepository.count()).thenReturn(150001L);

        ResponseEntity<String> responseEntity = ticketService.addTicket(1234567891L);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        Assertions.assertEquals("nous avons atteint le nombre maximale des participations", responseEntity.getBody());
    }

    // Test for addTicket() method with existing ticket number
    @Test
    public void testAddTicketWithExistingTicketNumber() {
        Ticket ticket = new Ticket();
        ticket.setTicketNumber(1234567890L);

        when(ticketRepository.findByTicketNumber(1234567890L)).thenReturn(ticket);

        ResponseEntity<String> responseEntity = ticketService.addTicket(1234567890L);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        Assertions.assertEquals("ce numéro de ticket est déjà utilisé", responseEntity.getBody());
    }

    // Test for addTicket() method with invalid ticket number length
    @Test
    public void testAddTicketWithInvalidTicketNumberLength() {
        Ticket ticket = new Ticket();
        ticket.setTicketNumber(123456789L);

        ResponseEntity<String> responseEntity = ticketService.addTicket(123456789L);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
}
