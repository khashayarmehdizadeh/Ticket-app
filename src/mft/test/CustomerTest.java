package mft.test;

import mft.model.bl.PaymentBl;
import mft.model.entity.Payment;
import mft.model.entity.enums.PaymentType;

import java.time.LocalDateTime;

public class CustomerTest {
    public static void main(String[] args) throws Exception {
        Payment payment =
                Payment
                        .builder()

                        .amount(1.200)
                        .paymentType(PaymentType.Cash)
                        .dateTime(LocalDateTime.now())
                        .build();


        PaymentBl.getPaymentBl().save(payment);

        System.out.println("event saved");
        System.out.println(payment);


    }
}
