package com.tiptop.users.controller;

import com.tiptop.users.entities.Ticket;
import com.tiptop.users.entities.User;
import com.tiptop.users.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class TicketController {

	@Autowired
	TicketService ticketService;


	@PostMapping("/addTicket")
	public ResponseEntity<String> addTicket(@RequestBody Long ticket) {
		 return ticketService.addTicket(ticket);
	}


	@GetMapping("/getTickets")
	public List<Ticket> getTickets(){
		return ticketService.getAllTickets();
	}

	@PutMapping("/updateTicket/{ticketNumber}")
	public Ticket updateTicketStatus(@PathVariable Long ticketNumber){
		return ticketService.updateTicketStatus(ticketNumber);
	}
	@PutMapping("/asignTicket/{userId}")
	public Ticket asigneTicketToUser(@RequestBody Long ticketNumber,@PathVariable Long userId){
		return ticketService.asigneTicketToUser(ticketNumber,userId);
	}

	@GetMapping("/getTicketByUserId/{userId}")
	public List<Ticket> getTicketByUserId(@PathVariable Long userId){
		return ticketService.getTicketByUserId(userId);
	}
	@GetMapping("/getAllTickets")
	public List<Object[]> findAllTicketsWithUsers(){
		return ticketService.findAllTicketsWithUsers();
	}

}
