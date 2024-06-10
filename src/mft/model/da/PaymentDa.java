package mft.model.da;

import lombok.extern.log4j.Log4j;
import mft.model.entity.Payment;
import mft.model.entity.enums.PaymentType;
import mft.model.tools.CRUD;
import mft.model.tools.ConnectionProvider;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Log4j
public class PaymentDa implements AutoCloseable, CRUD<Payment> {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public  PaymentDa() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
    }

    @Override
    public Payment save(Payment payment) throws Exception {
        payment.setId(ConnectionProvider.getConnectionProvider().getNextId("payment_SEQ"));
        payment.setDate_time(LocalDateTime.now());
        preparedStatement = connection.prepareStatement(
                "INSERT INTO PAYMENT(ID,AMOUNT,DATE_TIME,PAYMENT_TYPE) VALUES (?,?,?,?)"

        );
        preparedStatement.setInt(1, payment.getId());
        preparedStatement.setDouble(2, payment.getAmount());
        preparedStatement.setTimestamp(3,Timestamp.valueOf(payment.getDate_time()));
        preparedStatement.setString(4, payment.getPayment_type().name());
        preparedStatement.execute();
        return payment;
    }

    @Override
    public Payment edit(Payment payment) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE PAYMENT SET AMOUNT=?,DATE_TIME=? ,PAYMENT_TYPE=? WHERE ID=?"
        );
        preparedStatement.setInt(1, payment.getId());
        preparedStatement.setDouble(2, payment.getAmount());
        preparedStatement.setTimestamp(4, Timestamp.valueOf(payment.getDate_time()));
        preparedStatement.setString(3, String.valueOf(payment.getPayment_type()));
        preparedStatement.execute();
        return payment;
    }

    @Override
    public Payment remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM PAYMENT WHERE ID=?"
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
                    .payment_type(PaymentType.valueOf(resultSet.getString("payment_type")))
                    .date_time(resultSet.getDate("datetime").toLocalDate().atStartOfDay())
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
                    .payment_type(PaymentType.valueOf(resultSet.getString("payment_type")))
                    .date_time(resultSet.getDate("datetime").toLocalDate().atStartOfDay())
                    .build();

        }
        return payment;
    }


    @Override
    public void close() throws Exception {

    }
}
