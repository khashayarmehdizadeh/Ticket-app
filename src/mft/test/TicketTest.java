package mft.test;

import mft.model.bl.TicketBl;
import mft.model.entity.Ticket;

import java.time.LocalDateTime;

public class TicketTest {
    public static void main(String[] args) throws Exception {

        Ticket ticket=
                Ticket
                        .builder()
                        .info("jurassic")





                        .date_Time(LocalDateTime.now())

                        .build();
        TicketBl.getTicketBl().save(ticket);


    }
}
