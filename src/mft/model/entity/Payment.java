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
public class Payment {
    private int id;
    private Double amount;
    private LocalDateTime dateTime;
    @Override
     public String toString() {
        return new Gson().toJson(this);
    }
}
