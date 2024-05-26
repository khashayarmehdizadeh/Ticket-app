package mft.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import mft.model.entity.enums.EventCategory;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Setter
@SuperBuilder
public class Event {
    private int id;
    private String name;
    private EventCategory category;
    private Double price;
    private int capacity;
    private LocalDateTime dateTime;
    private String description;

    @Override
     public String toString() {
        return new Gson().toJson(this);
    }
}
