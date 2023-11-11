package com.tiptop.users.service;

import java.util.List;
import java.util.Optional;

import com.tiptop.users.entities.Ticket;
import com.tiptop.users.entities.User;
import com.tiptop.users.repos.ITicketRepository;
import com.tiptop.users.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;


@Service
public class TicketService implements ITicketService{
	
	@Autowired
	ITicketRepository ticketRepo;

	@Autowired
	UserRepository userRepo;

	@Override
	public Long getMaxNumOfTickets() {
		return ticketRepo.count();
	}
	
	private boolean verifyNewTicket(Long ticketNumber){
		System.out.println(ticketNumber);
		Ticket ticket = ticketRepo.findByTicketNumber(ticketNumber);
		System.out.println(ticket!= null);
		return ticket == null;
	}
	
	@Override
	public ResponseEntity<String> addTicket(Ticket ticket) throws RuntimeException{
		if (getMaxNumOfTickets() > 15000) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("nous avons atteint le nombre maximale des participations");
		}
		if (!verifyNewTicket(ticket.getTicketNumber())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ce numéro de ticket est déjà utilisé");
		}
		if ( ticket.getTicketNumber().toString().length() != 10){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}

		if ((getMaxNumOfTickets() < 150000) && (verifyNewTicket(ticket.getTicketNumber()))&& (ticket.getTicketNumber().toString().length() == 10)) {
			ticketRepo.save(ticket);

		}
		return ResponseEntity.ok("Ticket ajouté avec succés");
	}


	@Override
	public List<Ticket> getAllTickets(){
		return ticketRepo.findAll();
	}

	public Ticket updateTicketStatus(Long ticketNumber){
		 Ticket ticket= ticketRepo.findByTicketNumber(ticketNumber);
		 ticket.setStatus("reclame");
		 return ticketRepo.save(ticket);
	}

	public Ticket asigneTicketToUser(Long ticketNumber,Long userId){
		 Ticket ticket= ticketRepo.findByTicketNumber(ticketNumber);
		User user= userRepo.findById(userId).orElse(null);
		ticket.setUser(user);
		 return ticketRepo.save(ticket);
	}
}
