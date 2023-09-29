package com.projects.jexxatutorials;

import io.jexxa.core.JexxaMain;
import io.jexxa.drivingadapter.rest.RESTfulRPCAdapter;
import com.projects.jexxatutorials.applicationservice.BookStoreService;
import com.projects.jexxatutorials.domainservice.DomainEventService;
import com.projects.jexxatutorials.domainservice.ReferenceLibrary;

public final class BookStore
{
    public static void main(String[] args)
    {
        var jexxaMain = new JexxaMain(BookStore.class);

        jexxaMain
                .bootstrap(ReferenceLibrary.class).and()       // Bootstrap latest book via ReferenceLibrary
                .bootstrap(DomainEventService.class).and()     // DomainEventService to forward DomainEvents to a message bus

                .bind(RESTfulRPCAdapter.class).to(BookStoreService.class)        // Provide REST access to BookStoreService
                .bind(RESTfulRPCAdapter.class).to(jexxaMain.getBoundedContext()) // Provide REST access to BoundedContext

                .run(); // Finally run the application
    }
}
