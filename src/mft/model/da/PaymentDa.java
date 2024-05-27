package mft.model.da;

import mft.model.entity.Payment;
import mft.model.entity.enums.PaymentType;
import mft.model.tools.CRUD;
import mft.model.tools.ConnectionProvider;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
        preparedStatement = connection.prepareStatement(
                "insert into payment(id,amount,datetime,paymentType) vvalues (?,?,?,?)"
        );
        preparedStatement.setInt(1, payment.getId());
        preparedStatement.setDouble(2, payment.getAmount());
        preparedStatement.setString(3, payment.getPaymentType().name());
        preparedStatement.execute();
        return payment;
    }

    @Override
    public Payment edit(Payment payment) throws Exception {
        preparedStatement = connection.prepareStatement(
                "update payment set amount=?,date_time=? ,payment_type=? where id=?"
        );
        preparedStatement.setInt(1, payment.getId());
        preparedStatement.setDouble(2, payment.getAmount());
        preparedStatement.setString(3, String.valueOf(payment.getPaymentType()));
        preparedStatement.setTimestamp(4, Timestamp.valueOf(payment.getDateTime()));
        preparedStatement.execute();
        return payment;
    }

    @Override
    public Payment remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "delete from payment where id=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    @Override
    public List<Payment> findAll() throws Exception {
        List<Payment> paymentList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("select *from payment order by id");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Payment payment = Payment
                    .builder()
                    .id(resultSet.getInt("id"))
                    .amount(resultSet.getDouble("amount"))
                    .paymentType(PaymentType.valueOf(resultSet.getString("payment_type")))
                    .dateTime(resultSet.getDate("datetime").toLocalDate().atStartOfDay())
                    .build();
            paymentList.add(payment);
        }
        return paymentList;
    }

    @Override
    public Payment findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("select *from payment where id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Payment payment = null;
        if (resultSet.next()) {
            payment = Payment
                    .builder()
                    .id(resultSet.getInt("id"))
                    .amount(resultSet.getDouble("amount"))
                    .paymentType(PaymentType.valueOf(resultSet.getString("payment_type")))
                    .dateTime(resultSet.getDate("datetime").toLocalDate().atStartOfDay())
                    .build();

        }
        return payment;
    }


    @Override
    public void close() throws Exception {

    }
}
