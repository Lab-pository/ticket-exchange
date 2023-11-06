package com.ticketexchange.controller.dto;

import com.ticketexchange.service.dto.CreateTicketDto;

public class TicketRequest {

    private String howToAcquire;
    private int count;

    protected TicketRequest() {
    }

    public TicketRequest(String howToAcquire, int count) {
        this.howToAcquire = howToAcquire;
        this.count = count;
    }

    public CreateTicketDto toCreateTicketDto() {
        return new CreateTicketDto(howToAcquire, count);
    }

    public String getHowToAcquire() {
        return howToAcquire;
    }

    public int getCount() {
        return count;
    }
}
