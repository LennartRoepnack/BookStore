package com.projects.jexxatutorials.domainservice;


import io.jexxa.addend.applicationcore.DomainEventHandler;
import io.jexxa.addend.applicationcore.DomainService;
import com.projects.jexxatutorials.domain.DomainEventPublisher;
import com.projects.jexxatutorials.domain.book.BookSoldOut;

@SuppressWarnings("unused")
@DomainService
public class DomainEventService
{
    private final DomainEventSender domainEventSender;
    @SuppressWarnings("unused")
    public DomainEventService(DomainEventSender domainEventSender)
    {
        this.domainEventSender = domainEventSender;
        DomainEventPublisher.subscribe(BookSoldOut.class, this::handleEvent);
    }

    @DomainEventHandler
    public void handleEvent(BookSoldOut domainEvent)
    {
        domainEventSender.publish(domainEvent);
    }
}
