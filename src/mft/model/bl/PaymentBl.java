package mft.model.bl;

import mft.controller.exceptions.NoPaymentFoundException;
import mft.model.da.PaymentDa;
import mft.model.entity.Payment;
import mft.model.tools.CRUD;

import java.util.List;

public class PaymentBl implements CRUD<Payment> {
    @Override
    public Payment save(Payment payment) throws Exception {
        try (PaymentDa paymentDa = new PaymentDa()) {
            paymentDa.save(payment);
            return payment;

        }


    }

    @Override
    public Payment edit(Payment payment) throws Exception {
        try (PaymentDa paymentDa = new PaymentDa()) {
            if (paymentDa.findById(payment.getId()) != null) {
                paymentDa.edit(payment);
                return payment;
            } else {
                throw new NoPaymentFoundException();
            }

        }

    }

    @Override
    public Payment remove(int id) throws Exception {
        try (PaymentDa paymentDa=new PaymentDa()){
            Payment payment=paymentDa.findById(id);
            if (payment!=null){
                paymentDa.remove(id);
                return payment;
            }else {
                throw new NoPaymentFoundException();
            }

        }

    }

    @Override
    public List<Payment> findAll() throws Exception {
        try (PaymentDa paymentDa=new PaymentDa()){
            List<Payment> paymentList=paymentDa.findAll();
            if (!paymentList.isEmpty()){
                return paymentList;
            }else {
                throw new NoPaymentFoundException();
            }

        }

    }

    @Override
    public Payment findById(int id) throws Exception {
        try (PaymentDa paymentDa=new PaymentDa()){
            Payment payment=paymentDa.findById(id);
            if (payment!=null){
                return payment;
            }else {
                throw new NoPaymentFoundException();
            }

        }

    }
}
