package mft.test;

import mft.model.bl.CustomerBl;
import mft.model.bl.EventBl;
import mft.model.bl.PaymentBl;
import mft.model.bl.TicketBl;
import mft.model.entity.Customer;
import mft.model.entity.Event;
import mft.model.entity.Payment;
import mft.model.entity.Ticket;
import mft.model.entity.enums.EventCategory;
import mft.model.entity.enums.PaymentType;

import java.time.LocalDateTime;

public class TicketTest {
    public static void main(String[] args) throws Exception {
        Customer customer=
                Customer.builder()
                        .name("khashayar")
                        .family("mehdizadeh")
                        .email("mzkhashayar@gmail.com")
                        .phone_number("09120185685")

                        .build();
        CustomerBl.getCustomerBl().save(customer);
        Event event=
                Event.builder()
                        .id(1)
                        .name("Entry")
                        .price(150.000)
                        .capacity(500)
                        .category(EventCategory.Jurassic)
                        .description("friday")
                        .build();
        EventBl.getEventBl().save(event);
        Payment payment=
                Payment.builder()
                        .id(1)
                        .amount(150.000)
                        .payment_type(PaymentType.Card)
                        .date_time(LocalDateTime.now())
                        .build();
        PaymentBl.getPaymentBl().save(payment);
        Ticket ticket=

                Ticket.builder()
                        .id(1)
                        .info("Entry jurassic")
                        .payment(payment)
                        .customer(customer)
                        .event(event)
                        .build();
        TicketBl.getTicketBl().save(ticket);



        System.out.println("saved");


    }
}
