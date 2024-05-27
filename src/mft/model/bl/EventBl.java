package mft.model.bl;

import mft.model.da.EventDa;
import mft.model.entity.Event;
import mft.model.tools.CRUD;

import java.util.List;

public class EventBl implements CRUD<Event> {
    @Override
    public Event save(Event event) throws Exception {
        try (EventDa eventDa = new EventDa()) {
            eventDa.save(event);
            return event;

        }

    }

    @Override
    public Event edit(Event event) throws Exception {
        try (EventDa eventDa = new EventDa()) {
            if (eventDa.findById(event.getId()) != null) {
                eventDa.edit(event);
                return event;
            } else {
                throw new Exception("Not event found");
            }

        }

    }

    @Override
    public Event remove(int id) throws Exception {
        try (EventDa eventDa = new EventDa()) {
            Event event = eventDa.findById(id);
            if (event != null) {
                eventDa.remove(id);
                return event;

            } else
                throw new Exception("Not event found");

        }

    }

    @Override
    public List<Event> findAll() throws Exception {
        try (EventDa eventDa = new EventDa()) {
            List<Event> eventList = eventDa.findAll();
            if (!eventList.isEmpty()) {
                return eventList;
            } else {
                throw new Exception("No event found");
            }

        }

    }

    @Override
    public Event findById(int id) throws Exception {
        try (EventDa eventDa = new EventDa()) {
            Event event = eventDa.findById(id);
            if (event != null) {
                return event;

            } else {
                throw new Exception("No event found");
            }
        }

    }
}
