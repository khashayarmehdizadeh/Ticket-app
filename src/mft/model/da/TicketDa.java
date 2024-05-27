package mft.model.da;

import mft.model.entity.Ticket;
import mft.model.tools.CRUD;
import mft.model.tools.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class TicketDa implements AutoCloseable, CRUD<Ticket> {
    private Connection connection;
    private PreparedStatement preparedStatement;
    public void TicketDa()throws SQLException{
        connection= ConnectionProvider.getConnectionProvider().getConnection();
    }
    @Override
    public Ticket save(Ticket ticket) throws Exception {
        ticket.setId(ConnectionProvider.getConnectionProvider().getNextId("ticket_SEQ"));
        ticket.setDateTime(LocalDateTime.now());
        preparedStatement=connection.prepareStatement(
                "insert into ticket(id,event,customer,payment,info,datetime)  values (?,?,?,?,?,timestamp );"
        );
        preparedStatement.setInt(1,ticket.getId());
        preparedStatement.setString(2,ticket.getEvent().getName());
        preparedStatement.setString(3,ticket.getCustomer().getPhoneNumber());
        preparedStatement.setString(4, String.valueOf(ticket.getPayment().getPaymentType()));
        preparedStatement.setString(5,ticket.getInfo());
        preparedStatement.setTimestamp(6, Timestamp.valueOf(ticket.getDateTime()));
        preparedStatement.execute();
        return ticket;
    }

    @Override
    public Ticket edit(Ticket ticket) throws Exception {
        return null;
    }

    @Override
    public Ticket remove(int id) throws Exception {
        return null;
    }

    @Override
    public List<Ticket> findAll() throws Exception {
        return null;
    }

    @Override
    public Ticket findById(int id) throws Exception {
        return null;
    }

    @Override
    public void close() throws Exception {
        
    }
}
