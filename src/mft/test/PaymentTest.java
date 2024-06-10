package mft.test;

import mft.model.bl.PaymentBl;
import mft.model.entity.Payment;
import mft.model.entity.enums.PaymentType;

import java.time.LocalDateTime;

public class PaymentTest {
    public static void main(String[] args) throws Exception {
        Payment payment=
                Payment.builder()
                        .amount(275.000)
                        .date_time(LocalDateTime.now())
                        .payment_type(PaymentType.Cash)
                        .build();
        System.out.println(PaymentBl.getPaymentBl().save(payment));
        System.out.println("saved");

    }
}
