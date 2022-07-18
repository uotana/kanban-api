package com.bdweb.kanbanapi.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class TaskGroupResponse {
    private Long id;
    private String name;
    private BoardResponse board;
    private ZonedDateTime registrationDate;
}
