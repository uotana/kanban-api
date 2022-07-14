package com.bdweb.kanbanapi.models;

import com.bdweb.kanbanapi.dtos.responses.BoardResponse;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity(name="BOARD")
@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private Customer customer;

    @Column(name = "REGISTRATION_DATE")
    private ZonedDateTime registrationDate;

    public BoardResponse toResponse(){
        BoardResponse response = new BoardResponse();
        response.setId(this.getId());
        response.setName(this.getName());
        response.setRegistrationDate(this.getRegistrationDate());
        response.setCustomer(this.getCustomer().toResponse());
        return response;
    }
}
