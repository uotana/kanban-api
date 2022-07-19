package com.bdweb.kanbanapi.dtos.responses;

import com.bdweb.kanbanapi.models.TaskGroup;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TaskResponse {
    private Long id;
    private String title;
    private TaskGroupResponse taskGroup;
    private String description;
    private ZonedDateTime registrationDate;
}
