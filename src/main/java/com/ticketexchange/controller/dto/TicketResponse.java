package com.ticketexchange.controller.dto;

import java.time.LocalDate;

import com.ticketexchange.service.dto.TicketDto;

public class TicketResponse {

    private String howToAcquire;
    private LocalDate acquireDate;
    private LocalDate expireDate;

    protected TicketResponse() {
    }

    public TicketResponse(String howToAcquire, LocalDate acquireDate, LocalDate expireDate) {
        this.howToAcquire = howToAcquire;
        this.acquireDate = acquireDate;
        this.expireDate = expireDate;
    }

    public static TicketResponse of(TicketDto ticketDto) {
        return new TicketResponse(ticketDto.getHowToAcquire(), ticketDto.getAcquireDate(), ticketDto.getExpireDate());
    }

    public String getHowToAcquire() {
        return howToAcquire;
    }

    public LocalDate getAcquireDate() {
        return acquireDate;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }
}
