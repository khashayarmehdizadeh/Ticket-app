package mft.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Setter
@SuperBuilder
public class Ticket {
    private int id;
    private Event event;
    private Customer customer;
    private Payment payment;
    private String info;
    private LocalDateTime localDateTime;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
