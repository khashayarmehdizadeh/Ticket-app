package mft.model.bl;

import mft.model.da.EventDa;
import mft.model.entity.Event;
import mft.model.tools.CRUD;

import java.util.List;

public class EventBl implements CRUD<Event> {
    @Override
    public Event save(Event event) throws Exception {
        try (EventDa eventDa=new EventDa()){
            eventDa.save(event);
            return event;

        }

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
