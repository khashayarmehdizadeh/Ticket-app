package mft.model.bl;

import mft.model.entity.Payment;
import mft.model.tools.CRUD;

import java.util.List;

public class PaymentBl implements CRUD<Payment> {
    @Override
    public Payment save(Payment payment) throws Exception {
        try (PaymentDa paymentDa=new PaymentDa()){

        }

        return null;
    }

    @Override
    public Payment edit(Payment payment) throws Exception {
        return null;
    }

    @Override
    public Payment remove(int id) throws Exception {
        return null;
    }

    @Override
    public List<Payment> findAll() throws Exception {
        return null;
    }

    @Override
    public Payment findById(int id) throws Exception {
        return null;
    }
}
