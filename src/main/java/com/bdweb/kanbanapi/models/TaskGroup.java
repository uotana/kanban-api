package com.bdweb.kanbanapi.models;

import com.bdweb.kanbanapi.dtos.responses.CustomerResponse;
import com.bdweb.kanbanapi.dtos.responses.TaskGroupResponse;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
    @JoinColumn(name="BOARD_ID")
    private Board board;

    public TaskGroupResponse toResponse() {
        TaskGroupResponse response = new TaskGroupResponse();
        response.setId(this.getId());
        response.setName(this.getName());
        response.setBoard(this.board.toResponse());
        return response;
    }
}
