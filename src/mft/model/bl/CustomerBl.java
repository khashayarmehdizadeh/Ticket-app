package mft.model.bl;

import mft.controller.exceptions.NoCustomerFoundException;
import mft.model.da.CustomerDa;
import mft.model.entity.Customer;
import mft.model.tools.CRUD;

import java.util.List;

public class CustomerBl implements CRUD<Customer> {
    @Override
    public Customer save(Customer customer) throws Exception {
        try(CustomerDa customerDa = new CustomerDa()){
            customerDa.save(customer);
            return customer;
        }
    }

    @Override
    public Customer edit(Customer customer) throws Exception {
        try (CustomerDa customerDa=new CustomerDa()){
            if (customerDa.findByFamily(customer.getFamily())!=null){
                customerDa.edit(customer);
                return customer;

            }else {
                throw new NoCustomerFoundException();
            }
        }
    }

    @Override
    public Customer remove(int id) throws Exception {
        return null;
    }

    @Override
    public List<Customer> findAll() throws Exception {
        return null;
    }

    @Override
    public Customer findById(int id) throws Exception {
        return null;
    }
}
