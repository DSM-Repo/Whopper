package com.example.whopper.domain.document.dto.request;

public record UpdateActivityElementRequest(String elementId, String name, String date, String endDate, boolean isPeriod, String description) {
}
