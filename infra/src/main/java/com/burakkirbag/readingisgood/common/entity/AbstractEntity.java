package com.burakkirbag.readingisgood.common.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Setter
public class AbstractEntity {

    @Id
    private String id;

    @CreatedDate
    private LocalDateTime createdAt;
}
