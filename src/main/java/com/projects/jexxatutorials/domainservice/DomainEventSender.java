package com.projects.jexxatutorials.domainservice;


import io.jexxa.addend.applicationcore.InfrastructureService;
import com.projects.jexxatutorials.domain.book.BookSoldOut;

@InfrastructureService
public interface DomainEventSender
{
    void publish(BookSoldOut domainEvent);
}
