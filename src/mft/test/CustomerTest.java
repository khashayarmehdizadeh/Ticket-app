package mft.test;

import mft.model.da.CustomerDa;
import mft.model.entity.Customer;

public class CustomerTest {
    public static void main(String[] args)throws Exception {
        Customer customer=
                Customer.builder()
                        .id(14)
                        .name("khashayar")
                        .family("mehdizadeh")
                        .phoneNumber("09120185685")
                        .email("mzkhashayar@gmail")
                        .build();
        CustomerDa customerDa=new CustomerDa();
        customerDa.save(customer);



        System.out.println("customer saved");









    }
}
