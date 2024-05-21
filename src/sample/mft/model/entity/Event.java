package sample.mft.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@Setter
@SuperBuilder
public class Event {
    private int id;
    private String name;
    private String category;
    private Double price;
    private Double quantity;
    private int dateTime;
    @Override
     public String toString() {
        return new Gson().toJson(this);
    }
}
