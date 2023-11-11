package com.tiptop.users.entities;


import javax.persistence.*;

@Entity
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ticketId;
	private Long ticketNumber;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private String prize;



	private String status = "nonreclame";
	@ManyToOne()
	@JoinColumn(name = "user_id")
	private User user;


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPrize() {
		return prize;
	}

	public void setPrize(String prize) {
		this.prize = prize;
	}
	
	public Long getTicketId() {
		return ticketId;
	}
	
	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}
	
	public Long getTicketNumber() {
		return ticketNumber;
	}
	
	public void setTicketNumber(Long ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	@Override
	public String toString() {
		return "ticket [TicketId=" + ticketId + ", TicketNumber=" + ticketNumber
				+ "]";
	}
}
