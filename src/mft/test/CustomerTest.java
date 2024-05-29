package mft.test;

import mft.model.bl.CustomerBl;
import mft.model.bl.EventBl;
import mft.model.da.CustomerDa;
import mft.model.entity.Customer;
import mft.model.entity.Event;
import mft.model.entity.enums.EventCategory;

import java.time.LocalDateTime;

public class CustomerTest {
    public static void main(String[] args) {
        Customer customer=
                Customer.builder()
                        .id(1)
                        .name("khashayar")
                        .family("mehdizadeh")
                        .phoneNumber("09120185685")
                        .email("mzkhashayar@gmail")
                        .build();

        CustomerDa.get().save(customer);
        System.out.println(customer);



        System.out.println(customer);





    }
}
