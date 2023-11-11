package com.tiptop.users.repos;

import com.tiptop.users.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITicketRepository extends JpaRepository<Ticket, Long> {

	Ticket findByTicketNumber(Long ticketNumber);

	//Iterable<Ticket> findAll(Long userId);
}
