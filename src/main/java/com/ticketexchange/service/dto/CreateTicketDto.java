package com.ticketexchange.service.dto;

import java.time.LocalDate;

import com.ticketexchange.domain.Member;
import com.ticketexchange.domain.Ticket;

public class CreateTicketDto {

    private String howToAcquire;
    private int count;

    protected CreateTicketDto() {
    }

    public CreateTicketDto(String howToAcquire, int count) {
        this.howToAcquire = howToAcquire;
        this.count = count;
    }

    public Ticket toEntity(Member member) {
        return new Ticket(member, howToAcquire);
    }

    public Ticket toEntity(Member member, LocalDate now) {
        return new Ticket(member, howToAcquire, now);
    }

    public String getHowToAcquire() {
        return howToAcquire;
    }

    public int getCount() {
        return count;
    }
}
