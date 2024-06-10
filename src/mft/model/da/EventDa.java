package mft.model.da;

import lombok.extern.log4j.Log4j;
import mft.model.entity.Event;
import mft.model.entity.enums.EventCategory;
import mft.model.tools.CRUD;
import mft.model.tools.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class EventDa implements AutoCloseable, CRUD<Event> {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public EventDa() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
    }


    @Override
    public Event save(Event event) throws Exception {

        event.setId(ConnectionProvider.getConnectionProvider().getNextId("event_SEQ"));
        ;
        preparedStatement = connection.prepareStatement(
                "INSERT INTO EVENT(ID, NAME, CATEGORY, PRICE, CAPACITY, DESCRIPTION)VALUES (?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, event.getId());
        preparedStatement.setString(2, event.getName());
        preparedStatement.setString(3, event.getCategory().name());
        preparedStatement.setDouble(4, event.getPrice());
        preparedStatement.setInt(5, event.getCapacity());
        preparedStatement.setString(6, event.getDescription());

        preparedStatement.execute();
        return event;
    }

    @Override
    public Event edit(Event event) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE EVENT SET NAME=?,CATEGORY=?,PRICE=?,CAPACITY=?,DESCRIPTION=? WHERE ID=?"
        );
        preparedStatement.setString(1, event.getName());
        preparedStatement.setString(2, event.getCategory().name());
        preparedStatement.setDouble(3, event.getPrice());
        preparedStatement.setInt(4, event.getCapacity());
        preparedStatement.setString(5, event.getDescription());

        preparedStatement.setInt(6, event.getId());
        return event;
    }

    @Override
    public Event remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM EVENT WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    @Override
    public List<Event> findAll() throws Exception {
        List<Event> eventList = new ArrayList<>();
        preparedStatement = connection.prepareStatement("SELECT * FROM EVENT ORDER BY ID");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Event event = Event
                    .builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .category(EventCategory.valueOf(resultSet.getString("category")))
                    .price(resultSet.getDouble("price"))
                    .capacity(resultSet.getInt("capacity"))
                    .description(resultSet.getString("description"))
                    .build();
            eventList.add(event);
        }

        return eventList;
    }

    @Override
    public Event findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM EVENT WHERE ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Event event = null;
        if (resultSet.next()) {
            event = Event
                    .builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .category(EventCategory.valueOf(resultSet.getString("category")))
                    .price(resultSet.getDouble("price"))
                    .capacity(resultSet.getInt("capacity"))
                    .description(resultSet.getString("description"))
                    .build();
        }
        return event;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
