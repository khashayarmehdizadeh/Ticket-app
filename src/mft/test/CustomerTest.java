package mft.test;

import mft.model.bl.EventBl;
import mft.model.entity.Event;
import mft.model.entity.enums.EventCategory;

import java.time.LocalDateTime;

public class CustomerTest {
    public static void main(String[] args)throws Exception {
        Event event=
                Event.builder()
                        .name("shaykh")
                        .description("megadeath")
                        .category(EventCategory.Jurassic)
                        .capacity(100)
                        .price(15.500)
                        .dateTime(LocalDateTime.of(2024,2,2,15,30))

                        .build();


        EventBl.getEventBl().save(event);

        System.out.println("event saved");
        System.out.println(event);









    }
}
