package com.hemnath.task.dto;

public record ErrorResponce(
        int status,
        String message,
        String details
) {
}
