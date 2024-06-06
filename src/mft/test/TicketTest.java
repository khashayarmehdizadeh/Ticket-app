package mft.test;

import mft.model.bl.EventBl;
import mft.model.bl.TicketBl;
import mft.model.entity.Event;
import mft.model.entity.Ticket;
import mft.model.entity.enums.EventCategory;

import java.time.LocalDateTime;

public class TicketTest {
    public static void main(String[] args) throws Exception {
        Event event=
                Event.builder()
                        .name("vorodi")
                        .category(EventCategory.Jurassic)
                        .price(150.000)
                        .capacity(1000)
                        .description("sunday")
                        //.dateTime(LocalDateTime.now())

                        .build();


        Ticket ticket=
                Ticket
                        .builder()
                        .info("jurassic")


                        .dateTime(LocalDateTime.now())

                        .build();
        TicketBl.getTicketBl().save(ticket);
        EventBl.getEventBl().save(event);

    }
}
