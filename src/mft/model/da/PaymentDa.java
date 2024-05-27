package mft.model.da;

import mft.model.entity.Payment;
import mft.model.tools.CRUD;
import mft.model.tools.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class PaymentDa implements AutoCloseable, CRUD<Payment> {
    private Connection connection;
    private PreparedStatement preparedStatement;

    public void PaymendDa() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
    }

    @Override
    public Payment save(Payment payment) throws Exception {
        payment.setId(ConnectionProvider.getConnectionProvider().getNextId("payment_SEQ"));
        payment.setDateTime(LocalDateTime.now());
        preparedStatement=connection.prepareStatement(
                "insert into payment(id,amount,datetime,paymentType) vvalues (?,?,?,?)"
        );
        preparedStatement.setInt(1,payment.getId());
        preparedStatement.setDouble(2,payment.getAmount());
        preparedStatement.setString(3,payment.getPaymentType().name());
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


    @Override
    public void close() throws Exception {

    }
}
