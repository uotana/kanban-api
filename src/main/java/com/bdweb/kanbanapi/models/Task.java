package com.bdweb.kanbanapi.models;

import com.bdweb.kanbanapi.dtos.responses.TaskResponse;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="TASK_GROUP_ID")
    private TaskGroup taskGroup;

    @Column(name="DESCRIPTION")
    private String description;

    @Column(name = "REGISTRATION_DATE")
    private ZonedDateTime registrationDate;

    public TaskResponse toResponse(){
        TaskResponse response = new TaskResponse();
        response.setId(this.id);
        response.setTitle(this.title);
        response.setDescription(this.description);
        response.setTaskGroup(this.taskGroup.toResponse());
        response.setRegistrationDate(this.registrationDate);
        return response;
    }
}
