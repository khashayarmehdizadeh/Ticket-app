package mft.model.da;

import lombok.extern.log4j.Log4j;
import mft.model.entity.Payment;
import mft.model.tools.CRUD;
import mft.model.tools.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
@Log4j
public class PaymentDa implements AutoCloseable, CRUD<Payment> {
    private final Connection connection;
    private PreparedStatement preparedStatement;
    public PaymentDa() throws SQLException{
        connection= ConnectionProvider.getConnectionProvider().getConnection();
    }

    @Override
    public Payment save(Payment payment) throws Exception {
        payment.setId(ConnectionProvider.getConnectionProvider().getNextId("PAYMENT_SEQ"));
        payment.setDate_time(LocalDateTime.now());
        preparedStatement=connection.prepareStatement(
                "INSERT INTO PAYMENT(ID, AMOUNT, PAYMENT_TYPE, DATE_TIME) VALUES (?,?,?,?)"
        );
        preparedStatement.setInt(1,payment.getId());
        preparedStatement.setDouble(2,payment.getAmount());
        preparedStatement.setString(3,payment.getPayment_type().name());
        preparedStatement.setTimestamp(4, Timestamp.valueOf(payment.getDate_time()));
        preparedStatement.execute();
        return payment;
    }

    @Override
    public Payment edit(Payment payment) throws Exception {
        preparedStatement=connection.prepareStatement(
                "UPDATE PAYMENT SET AMOUNT=?,PAYMENT_TYPE=?,DATE_TIME=? WHERE ID=?"
        );
        preparedStatement.setDouble(1,payment.getAmount());
        preparedStatement.setString(2,payment.getPayment_type().name());
        preparedStatement.setTimestamp(3,Timestamp.valueOf(payment.getDate_time()));
        preparedStatement.setInt(4,payment.getId());
        preparedStatement.execute();
        return payment;
    }

    @Override
    public Payment remove(int id) throws Exception {
        preparedStatement=connection.prepareStatement(
                "DELETE FROM PAYMENT WHERE ID=?"
        );
        preparedStatement.setInt(1,id);
        preparedStatement.execute();
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
        preparedStatement.close();
        connection.close();

    }
}
