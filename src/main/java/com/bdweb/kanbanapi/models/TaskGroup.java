package com.bdweb.kanbanapi.models;

import com.bdweb.kanbanapi.dtos.responses.TaskGroupResponse;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Set;

@Entity(name="TASK_GROUP")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@Setter
public class TaskGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="NAME")
    private String name;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="BOARD_ID")
    private Board board;

    @OneToMany(cascade = {CascadeType.DETACH})
    @JoinColumn(name="TASK")
    private Set<Task> task;

    @Column(name = "REGISTRATION_DATE")
    private ZonedDateTime registrationDate;

    public TaskGroupResponse toResponse() {
        TaskGroupResponse response = new TaskGroupResponse();
        response.setId(this.getId());
        response.setName(this.getName());
        response.setBoard(this.board.toResponse());
        response.setRegistrationDate(this.registrationDate);
        return response;
    }
}
