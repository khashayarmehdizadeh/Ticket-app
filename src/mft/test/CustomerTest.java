package mft.test;

import mft.model.bl.EventBl;
import mft.model.entity.Event;
import mft.model.entity.enums.EventCategory;

import java.time.LocalDateTime;

public class CustomerTest {
    public static void main(String[] args) throws Exception {
        Event event =
                Event.builder()
                        .name("park jurassic")
                        .category(EventCategory.Spider)
                        .price(75.000)
                        .capacity(100)
                        .description("1022")
                        .dateTime(LocalDateTime.now())
                        .build();


        EventBl.getEventBl().save(event);

        System.out.println("event saved");
        System.out.println(event);


    }
}
