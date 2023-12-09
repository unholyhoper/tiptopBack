package com.tiptop.users.dto;

import com.tiptop.users.entities.PRIZE;
import com.tiptop.users.entities.User;

public class PrizeDTO {
    private String description;

    UserDTO userDTO;
    public PrizeDTO(PRIZE prize){
        this.description = prize.prize;
    }
    public PrizeDTO(PRIZE prize, User user){
        this.userDTO = new UserDTO(user);
        this.description = prize.prize;
    }

    public PrizeDTO(){

    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
