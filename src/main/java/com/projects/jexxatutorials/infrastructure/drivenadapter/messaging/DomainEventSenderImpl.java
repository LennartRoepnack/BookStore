package com.projects.jexxatutorials.infrastructure.drivenadapter.messaging;

import io.jexxa.addend.infrastructure.DrivenAdapter;
import io.jexxa.infrastructure.messaging.MessageSender;
import com.projects.jexxatutorials.domain.book.BookSoldOut;
import com.projects.jexxatutorials.domainservice.DomainEventSender;

import java.util.Objects;
import java.util.Properties;

import static io.jexxa.infrastructure.MessageSenderManager.getMessageSender;


@SuppressWarnings("unused")
@DrivenAdapter
public class DomainEventSenderImpl implements DomainEventSender
{
    private final MessageSender messageSender;

    public DomainEventSenderImpl(Properties properties)
    {
        messageSender = getMessageSender(DomainEventSender.class, properties);
    }

    @Override
    public void publish(BookSoldOut domainEvent)
    {
        Objects.requireNonNull(domainEvent);
        messageSender
                .send(domainEvent)
                .toTopic("BookStoreTopic")
                .addHeader("Type", domainEvent.getClass().getSimpleName())
                .asJson();
    }
}
