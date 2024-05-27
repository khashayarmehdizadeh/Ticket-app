package mft.model.da;

import mft.model.entity.Event;
import mft.model.entity.Payment;
import mft.model.tools.CRUD;
import mft.model.tools.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class PaymentDa implements AutoCloseable, CRUD<Payment> {
    private Connection connection;
    private PreparedStatement preparedStatement;
    public void PaymendDa() throws SQLException{
        connection= ConnectionProvider.getConnectionProvider().getConnection();
    }
    @Override
    public Payment save(Payment payment) throws Exception {
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
    public Event findByDateTime(Timestamp datetime) throws Exception {
        return null;
    }
}
