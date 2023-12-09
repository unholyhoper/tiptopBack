package com.tiptop.users.controller;

import com.tiptop.users.dto.PrizeDTO;
import com.tiptop.users.dto.TicketDTO;
import com.tiptop.users.entities.PRIZE;
import com.tiptop.users.entities.Ticket;
import com.tiptop.users.entities.User;
import com.tiptop.users.exception.NoTicketFoundException;
import com.tiptop.users.service.TicketService;
import com.tiptop.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/tickets")
public class TicketController {

	@Autowired
	TicketService ticketService;

	@Autowired
	UserService userService;
	@GetMapping("")
	public Collection<TicketDTO> findAllTickets(){
		return ticketService.findAllTickets();
	}
	@GetMapping("/me")
	public Collection<TicketDTO> findMyTickets(@AuthenticationPrincipal Object principal){
		User user =userService.findUserByUsername(principal.toString());
		return ticketService.findTicketsByUser(user);
	}




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

	@PostMapping("/play")
	public PrizeDTO spinwheel(@AuthenticationPrincipal Object principal) {
		User user = userService.findUserByUsername(principal.toString());
		Collection<Ticket> tickets = user.getTickets();
		Optional<Ticket> ticketOptional = tickets.stream().filter(ticket -> !ticket.isUsed()).findAny();
		Ticket ticket = ticketOptional.orElseThrow(() -> new NoTicketFoundException("new ticket found for user " + user.getUsername()));
		PrizeDTO prize = ticketService.spinWheel(ticket,user);
		return prize;
	 }



}
