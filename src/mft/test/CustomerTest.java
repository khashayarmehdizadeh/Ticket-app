package mft.test;

import mft.model.bl.CustomerBl;

public class CustomerTest {
    public static void main(String[] args) throws Exception {
        System.out.println(CustomerBl.getCustomerBl().findAll());
        System.out.println("remove");


    }
}
