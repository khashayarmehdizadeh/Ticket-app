package mft.test;

import mft.model.bl.PaymentBl;

public class PaymentTest {
    public static void main(String[] args) throws Exception {
//        Payment payment=
//                Payment.builder()
//
//                        .amount(150.0)
//                        .payment_type(PaymentType.Cash)
//                       // .date_time(LocalDateTime.now())
//
//                        .build();
        System.out.println(PaymentBl.getPaymentBl().remove(1));
        System.out.println("saved");

    }
}
