package mft.model.da;


import mft.model.entity.Event;
import mft.model.entity.enums.EventCategory;
import mft.model.tools.CRUD;
import mft.model.tools.ConnectionProvider;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventDa implements AutoCloseable,CRUD<Event> {
    private Connection connection;
    private PreparedStatement PreparedStatement;

    public void EventDa() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
    }

    @Override
    public Event save(Event event) throws Exception {
        event.setId(ConnectionProvider.getConnectionProvider().getNextId("event_SEQ"));
        event.setDateTime(LocalDateTime.now());

        PreparedStatement = connection.prepareStatement(
                "insert into event(id,name,category,price,quantity,datetime) values (?,?,?,?,?,? )"
        );
        PreparedStatement.setInt(1, event.getId());
        PreparedStatement.setString(2, event.getName());
        PreparedStatement.setString(3, event.getCategory().name());
        PreparedStatement.setDouble(4, event.getPrice());
        PreparedStatement.setInt(5, event.getCapacity());
        PreparedStatement.setTimestamp(6, Timestamp.valueOf(event.getDateTime()));
        PreparedStatement.execute();
        return event;
    }

    @Override
    public Event edit(Event event) throws Exception {
        PreparedStatement = connection.prepareStatement(
                "update event set name=?,category=?,price=?,quantity=?, date_time=? where id=?"
        );
        PreparedStatement.setInt(1, event.getId());
        PreparedStatement.setString(2, event.getName());
        PreparedStatement.setString(3, event.getCategory().name());
        PreparedStatement.setDouble(4, event.getPrice());
        PreparedStatement.setInt(5, event.getCapacity());
        PreparedStatement.setTimestamp(6, Timestamp.valueOf(event.getDateTime()));
        PreparedStatement.execute();
        return event;
    }

    @Override
    public Event remove(int id) throws Exception {
        PreparedStatement = connection.prepareStatement(
                "delete from event where id=?"
        );
        PreparedStatement.setInt(1, id);
        PreparedStatement.execute();
        return null;
    }

    @Override
    public List<Event> findAll() throws Exception {
        List<Event> EventList = new ArrayList<>();
        PreparedStatement = connection.prepareStatement("select *from event order by id");
        ResultSet resultSet = PreparedStatement.executeQuery();
        while (resultSet.next()) {
            Event event = Event
                    .builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .category(EventCategory.valueOf(resultSet.getString("category")))
                    .price(resultSet.getDouble("price"))
                    .capacity(resultSet.getInt("quantity"))
                    .dateTime(resultSet.getDate("datetime").toLocalDate().atStartOfDay())
                    .build();
            EventList.add(event);
        }
        return EventList;
    }

    @Override
    public Event findById(int id) throws Exception {
        PreparedStatement = connection.prepareStatement("select *from event where id=?");
        PreparedStatement.setInt(1, id);
        ResultSet resultSet = PreparedStatement.executeQuery();
        Event event = null;
        if (resultSet.next()) {
            event = Event
                    .builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .category(EventCategory.valueOf(resultSet.getString("category")))
                    .price(resultSet.getDouble("price"))
                    .capacity(resultSet.getInt("quantity"))
                    .dateTime(resultSet.getDate("datetime").toLocalDate().atStartOfDay())
                    .build();
        }
        return event;
    }

//    todo : findByDateTime
//    todo : findByDateCategory

    @Override
    public void close() throws Exception {
        PreparedStatement.close();
        connection.close();
    }
}
