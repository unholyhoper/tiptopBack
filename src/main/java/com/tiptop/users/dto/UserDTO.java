package com.tiptop.users.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tiptop.users.entities.ROLES;
import com.tiptop.users.entities.Ticket;
import com.tiptop.users.entities.User;
import lombok.*;

import java.util.Collection;
import java.util.stream.Collectors;

@Builder
@Setter
@Getter
@AllArgsConstructor
public class UserDTO {
    private Boolean enabled;
    private String email;
    private String username;
    private String role;
    @JsonIgnore
    private Collection<TicketDTO> tickets;

    public UserDTO() {

    }

    public UserDTO(User user) {
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.role = String.valueOf(user.getRole());
        this.enabled = user.getEnabled();
//        this.tickets = user.getTickets().stream().map(ticket -> new TicketDTO(ticket)).collect(Collectors.toList());
    }


}
