package com.example.whopper.domain.document.dto.request;

public record UpdateActivityElementRequest(String elementId, String name, String startDate, String endDate, boolean isPeriod, String description) {
}
