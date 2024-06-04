package mft.test;

import mft.model.bl.CustomerBl;
import mft.model.entity.Customer;

public class CustomerTest {
    public static void main(String[] args) throws Exception {
        Customer customer =
                Customer
                        .builder()
                        .name("khashsyar")
                        .family("mehidzeh")
                        .phoneNumber("09191057433")
                        .email("mzkhashayar@yahoo.com")

                        .build();

        CustomerBl.getCustomerBl().save(customer);

        System.out.println("customer saved");
        System.out.println(customer);


    }
}
