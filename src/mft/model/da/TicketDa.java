package mft.model.da;

import mft.model.entity.Ticket;
import mft.model.tools.CRUD;
import mft.model.tools.ConnectionProvider;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TicketDa implements AutoCloseable, CRUD<Ticket> {
    private Connection connection;
    private PreparedStatement preparedStatement;

    public void TicketDa() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
    }

    @Override
    public Ticket save(Ticket ticket) throws Exception {
        ticket.setId(ConnectionProvider.getConnectionProvider().getNextId("ticket_SEQ"));
        ticket.setDateTime(LocalDateTime.now());
        preparedStatement = connection.prepareStatement(
                "insert into ticket(id,event,customer,payment,info,datetime)  values (?,?,?,?,?,timestamp );"
        );
        preparedStatement.setInt(1, ticket.getId());
        preparedStatement.setString(2, ticket.getEvent().getName());
        preparedStatement.setString(3, ticket.getCustomer().getPhoneNumber());
        preparedStatement.setString(4, String.valueOf(ticket.getPayment().getId()));
        preparedStatement.setString(5, ticket.getInfo());
        preparedStatement.setTimestamp(6, Timestamp.valueOf(ticket.getDateTime()));
        preparedStatement.execute();
        return ticket;
    }

    @Override
    public Ticket edit(Ticket ticket) throws Exception {
        preparedStatement=connection.prepareStatement(
                "update ticket set event=?,customer=?,payment=?,info=?,date_time=? where id=?"
        );
        preparedStatement.setInt(1,ticket.getId());
        preparedStatement.setString(2,ticket.getEvent().getName());
        preparedStatement.setString(3,ticket.getCustomer().getPhoneNumber());
        preparedStatement.setString(4,String.valueOf(ticket.getPayment().getId()));
        preparedStatement.setString(5,ticket.getInfo());
        preparedStatement.setTimestamp(6,Timestamp.valueOf(ticket.getDateTime()));
        preparedStatement.execute();
        return ticket;

    }

    @Override
    public Ticket remove(int id) throws Exception {
        preparedStatement=connection.prepareStatement(
                "delete from ticket where id=?"
        );
        preparedStatement.setInt(1,id);
        preparedStatement.execute();
        return null;
    }

    @Override
    public List<Ticket> findAll() throws Exception {
        List<Ticket> ticketList=new ArrayList<>();
        preparedStatement=connection.prepareStatement("select *from ticket order by id");
        ResultSet resultSet=preparedStatement.executeQuery();
        while (resultSet.next()){
            Ticket ticket=Ticket
                    .builder()
                    .build()
        }
        return null;
    }

    @Override
    public Ticket findById(int id) throws Exception {
        return null;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();

    }
}
