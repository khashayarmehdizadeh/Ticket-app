package mft.model.bl;

import mft.controller.exceptions.NoCustomerFoundException;
import mft.model.da.CustomerDa;
import mft.model.entity.Customer;
import mft.model.tools.CRUD;

import java.util.List;

public class CustomerBl implements CRUD<Customer> {
    private static CustomerBl customerBl=new CustomerBl();
    private CustomerBl(){

    }



    @Override
    public Customer save(Customer customer) throws Exception {
        try (CustomerDa customerDa = new CustomerDa()) {
            if(customerDa.findByPhoneNUmber(customer.getPhoneNumber()) == null){
                customerDa.save(customer);
            }else{
//                throw new DuplicatPhoneNumberException();
            }
            return customer;
        }
    }

    @Override
    public Customer edit(Customer customer) throws Exception {
        try (CustomerDa customerDa = new CustomerDa()) {
            if (customerDa.findById(customer.getId()) != null) {
                customerDa.edit(customer);
                return customer;

            } else {
                throw new NoCustomerFoundException();
            }
        }
    }

    @Override
    public Customer remove(int id) throws Exception {
        try (CustomerDa customerDa = new CustomerDa()) {
            Customer customer = customerDa.findById(id);
            if (customer != null) {
                customerDa.remove(id);
                return customer;

            } else {
                throw new NoCustomerFoundException();
            }
        }
    }

    @Override
    public List<Customer> findAll() throws Exception {
        try (CustomerDa customerDa = new CustomerDa()) {
            List<Customer> customerList = customerDa.findAll();
            if (!customerList.isEmpty()) {
                return customerList;
            } else {
                throw new NoCustomerFoundException();
            }
        }
    }

    @Override
    public Customer findById(int id) throws Exception {
        try (CustomerDa customerDa = new CustomerDa()) {
            Customer customer = customerDa.findById(id);
            if (customer != null) {
                return customer;
            } else {
                throw new NoCustomerFoundException();


            }
        }

    }


    public Customer findByFamily(String family) throws Exception {
        try (CustomerDa customerDa = new CustomerDa()) {
            Customer customer = customerDa.findByFamily(family);
            if (customer != null) {
                return customer;
            } else {
                throw new NoCustomerFoundException();

            }
        }
    }

    public Customer findByPhoneNumber(String phoneNumber) throws Exception {
        try (CustomerDa customerDa = new CustomerDa()) {
            Customer customer = customerDa.findByPhoneNUmber(phoneNumber);
            if (customer != null) {
                return customer;
            } else {
                throw new NoCustomerFoundException();
            }
        }
    }
    public Customer findByEmail(String Email) throws Exception {
        try (CustomerDa customerDa = new CustomerDa()) {
            Customer customer = customerDa.findByEmail(Email);
            if (customer != null) {
                return customer;
            } else {
                throw new NoCustomerFoundException();
            }
        }
    }
}