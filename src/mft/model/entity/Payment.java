package mft.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import mft.model.entity.enums.PaymentType;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Setter
@SuperBuilder
public class Payment {
    private int id;
    private Double amount;
    private LocalDateTime date_time;
    private PaymentType payment_type;

    @Override
     public String toString() {
        return new Gson().toJson(this);
    }
}
