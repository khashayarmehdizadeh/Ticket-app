package mft.test;

import mft.model.bl.EventBl;

public class EventTest {
    public static void main(String[] args) throws Exception {
//        Event event=
//                Event.builder()
//
//                        .name("jurassic")
//                        .category(EventCategory.Jurassic)
//                        .price(150.000)
//                        .capacity(100)
//                        .description("monday-saturday")
//                        //.date_time(LocalDateTime.now())
//                        .id(17)
//                        .build();

        System.out.println(EventBl.getEventBl().findAll());
        System.out.println("event find");

    }
}
