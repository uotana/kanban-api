package com.bdweb.kanbanapi.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskRequest {
    private String title;
    private String description;
}
