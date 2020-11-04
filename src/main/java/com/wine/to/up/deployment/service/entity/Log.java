package com.wine.to.up.deployment.service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Log {
    private LocalDateTime createdDate;
    private String log;
}
