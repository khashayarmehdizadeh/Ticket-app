package mft.test;

import mft.model.bl.EventBl;
import mft.model.entity.Event;
import mft.model.entity.enums.EventCategory;

public class EventTest {
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

        EventBl.getEventBl().save(event);
        Event.builder()
                 .name("vorodi")
                .category(EventCategory.Jurassic)
                .price(150.000)
                .capacity(1000)
                .description("monday")
                //.dateTime(LocalDateTime.now())

                .build();
        EventBl.getEventBl().save(event);
        System.out.println("event created");
        System.out.println(event);

    }
}
