package mft.model.da;

import mft.model.entity.Event;
import mft.model.tools.CRUD;
import mft.model.tools.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class EventDa implements CRUD<Event> {
    private Connection connection;
    private PreparedStatement PreparedStatement;

    public void EventDa() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
    }

    @Override
    public Event save(Event event) throws Exception {
        event.setId(ConnectionProvider.getConnectionProvider().getNextId("event_SEQ"));
        PreparedStatement = connection.prepareStatement(
                "insert into event(id,name,category,price,quantity,datetime) values (?,?,?,?,?,timestamp )"
        );
        PreparedStatement.setInt(1, event.getId());
        PreparedStatement.setString(2, event.getName());
        PreparedStatement.setString(3, event.getCategory());
        PreparedStatement.setDouble(4, event.getPrice());
        PreparedStatement.setInt(5, event.getQuantity());
        PreparedStatement.setDate(6,event.getDateTime();
        PreparedStatement.execute();
        return event;
    }

    @Override
    public Event edit(Event event) throws Exception {
        return null;
    }

    @Override
    public Event remove(int id) throws Exception {
        return null;
    }

    @Override
    public List<Event> findAll() throws Exception {
        return null;
    }

    @Override
    public Event findById(int id) throws Exception {
        return null;
    }
}
