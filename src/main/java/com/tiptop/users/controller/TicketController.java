package com.tiptop.users.controller;

import com.tiptop.users.entities.Ticket;
import com.tiptop.users.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class TicketController {

	@Autowired
	TicketService ticketService;


	@PostMapping("/addTicket")
	@ResponseBody
	public ResponseEntity<String> addTicket(@RequestBody Ticket ticket) {
		 return ticketService.addTicket(ticket);
	}

/*	@GetMapping("/getPrizes")
	@ResponseBody
	public void getPrizes(){
		prizeService.assignPrize();
	}*/

	@GetMapping("/getTickets")
	@ResponseBody
	public List<Ticket> getTickets(){
		return ticketService.getAllTickets();
	}

	@PutMapping("/updateTicket")
	public Ticket updateTicketStatus(@RequestBody Long ticketNumber){
		return ticketService.updateTicketStatus(ticketNumber);
	}
	@PutMapping("/updateTicket/{userId}")
	public Ticket asigneTicketToUser(@RequestBody Long ticketNumber,Long userId){
		return ticketService.asigneTicketToUser(ticketNumber,userId);
	}
}
