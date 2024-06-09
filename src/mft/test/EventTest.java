package mft.test;

import mft.model.bl.EventBl;
import mft.model.entity.Event;
import mft.model.entity.enums.EventCategory;

import java.time.LocalDateTime;

public class EventTest {
    public static void main(String[] args) throws Exception {
        Event event=
                Event.builder()

                        .name("spider")
                        .category(EventCategory.Spider)
                        .price(150.000)
                        .capacity(100)
                        .description("monday-saturday")
                        .date_time(LocalDateTime.now())
                        .id(18)
                        .build();

        System.out.println(EventBl.getEventBl().save(event));
        System.out.println("event find");

    }
}
