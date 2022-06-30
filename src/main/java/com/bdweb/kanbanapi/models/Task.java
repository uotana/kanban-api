package com.bdweb.kanbanapi.models;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity(name="TASK")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="TITLE")
    private String title;

    @ManyToOne
    @JoinColumn(name="BOARD_ID")
    private Board board;

    @Column(name="DESCRIPTION")
    private String description;

    @Column(name = "REGISTRATION_DATE")
    private ZonedDateTime registrationDate;
}
