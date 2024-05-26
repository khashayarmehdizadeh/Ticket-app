package mft.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)

public class Customer {
    private int id;
    private String name;
    private String family;
    private String phoneNumber;
    private String email;



    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
