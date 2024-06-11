package mft.model.da;

import lombok.extern.log4j.Log4j;
import mft.model.entity.Customer;
import mft.model.entity.Event;
import mft.model.entity.Payment;
import mft.model.entity.Ticket;
import mft.model.tools.CRUD;
import mft.model.tools.ConnectionProvider;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Log4j
public class TicketDa implements AutoCloseable, CRUD<Ticket> {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public  TicketDa() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
    }

    @Override
    public Ticket save(Ticket ticket) throws Exception {
        ticket.setId(ConnectionProvider.getConnectionProvider().getNextId("ticket_SEQ"));
        ticket.setDate_Time(LocalDateTime.now());
        preparedStatement = connection.prepareStatement(
                "INSERT INTO TICKET(ID, EVENT_ID, INFO, PAYMENT_ID, CUSTOMER_ID, DATE_TIME) VALUES (?,?,?,?,?,? )"
        );
        preparedStatement.setInt(1, ticket.getId());
        preparedStatement.setInt(2, ticket.getEvent().getId());
        preparedStatement.setInt(3, ticket.getCustomer().getId());
        preparedStatement.setInt(4, ticket.getPayment().getId());
        preparedStatement.setString(5, ticket.getInfo());
        preparedStatement.setTimestamp(6, Timestamp.valueOf(ticket.getDate_Time()));
        preparedStatement.execute();
        return ticket;
    }

    @Override
    public Ticket edit(Ticket ticket) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE TICKET SET EVENT_ID=?,CUSTOMER_ID=?,PAYMENT_ID=?,INFO=?,DATE_TIME=? where ID=?"
        );
        preparedStatement.setInt(1, ticket.getId());
        preparedStatement.setInt(2, ticket.getEvent().getId());
        preparedStatement.setInt(3, ticket.getCustomer().getId());
        preparedStatement.setInt(4, ticket.getPayment().getId());
        preparedStatement.setString(5, ticket.getInfo());
        preparedStatement.setTimestamp(6, Timestamp.valueOf(ticket.getDate_Time()));
        preparedStatement.execute();
        return ticket;

    }

    @Override
    public Ticket remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM TICKET WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    @Override
    public List<Ticket> findAll() throws Exception {
        List<Ticket> ticketList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT *FROM TICKET ORDER BY ID");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Ticket ticket = Ticket
                    .builder()
                    .id(resultSet.getInt("id"))
                    .info(resultSet.getString("info"))
                    .customer(Customer.builder().id(resultSet.getInt("customer")).build())
                    .event(Event.builder().id(resultSet.getInt("event")).build())
                    .date_Time(resultSet.getDate("datetime").toLocalDate().atStartOfDay())
                    .payment(Payment.builder().id(resultSet.getInt("payment")).build())
                    .build();
            ticketList.add(ticket);
        }
        return ticketList;
    }

    @Override
    public Ticket findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM TICKET WHERE ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Ticket ticket = null;
        if (resultSet.next()) {
            ticket = Ticket
                    .builder()
                    .id(resultSet.getInt("id"))
                    .info(resultSet.getString("info"))
                    .customer(Customer.builder().id(resultSet.getInt("customer")).build())
                    .event(Event.builder().id(resultSet.getInt("event")).build())
                    .date_Time(resultSet.getDate("datetime").toLocalDate().atStartOfDay())
                    .payment(Payment.builder().id(resultSet.getInt("payment")).build())
                    .build();
        }
        return ticket;

    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();

    }
}
