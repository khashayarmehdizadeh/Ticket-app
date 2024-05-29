package mft.test;

import mft.model.entity.Customer;

public class CustomerTest {
    public static void main(String[] args) {
        Customer.builder()
                .id(1)
                .name("khashayar")
                .family("mehdizadeh")
                .email("mzkhashayar@gmail.com")
                .phoneNumber("09120185685")
                .build();

    }
}
