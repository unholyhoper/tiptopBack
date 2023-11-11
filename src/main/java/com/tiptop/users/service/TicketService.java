package com.tiptop.users.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

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
	
	public boolean verifyNewTicket(Long ticketNumber){
		Ticket ticket = ticketRepo.findByTicketNumber(ticketNumber);
		return ticket == null;
	}
	
	@Override
	public ResponseEntity<String> addTicket(Long ticketNumber) throws RuntimeException{
		if (getMaxNumOfTickets() > 15000) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("nous avons atteint le nombre maximale des participations");
		}
		if (!verifyNewTicket(ticketNumber)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ce numéro de ticket est déjà utilisé");
		}
		if ( ticketNumber.toString().length() != 10){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}

		if ((getMaxNumOfTickets() < 150000) && (verifyNewTicket(ticketNumber))&& (ticketNumber.toString().length() == 10)) {
			Ticket ticket= new Ticket();
			ticket.setTicketNumber(ticketNumber);
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
		//test if its not taken
		Ticket ticket = ticketRepo.findByTicketNumber(ticketNumber);
		User user = userRepo.findById(userId).orElse(null);
		if (ticket.getPrize()== null) {
			ticket.setUser(user);
			ticket.setPrize(assignPrizeToTicket(ticketNumber));
			ticketRepo.save(ticket);
		}
		else
			ticket = null;
		return ticket;
	}

	public List<Ticket> getTicketByUserId(Long userId){
		return ticketRepo.findTicketsByUserId(userId);
	}

	public List<Object[]> findAllTicketsWithUsers(){
		return ticketRepo.findAllTicketsWithUsers();
	}

	public String assignPrizeToTicket(Long ticketNumber){
		Random random = new Random();
		int randomNumber = random.nextInt(100) + 1; // Generate a random number between 1 and 100

		if (randomNumber <= 60) {
			return "Infuseur";
		} else if (randomNumber <= 80) {
			return "Boite de 100g d’un thé détox ou d’infusion";
		} else if (randomNumber <= 90) {
			return "Boite de 100g d’un thé signature";
		} else if (randomNumber <= 96) {
			return "Coffret découverte d’une valeur de 39€";
		} else {
			return "Coffret découverte d’une valeur de 69€";
		}
	}
}
