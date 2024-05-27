package mft.model.da;

import lombok.extern.log4j.Log4j;
import mft.model.entity.Customer;
import mft.model.tools.CRUD;
import mft.model.tools.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class CustomerDa implements AutoCloseable, CRUD<Customer> {
    private Connection connection;
    private PreparedStatement preparedStatement;

    public void CustomerDa() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
    }

    @Override
    public Customer save(Customer customer) throws Exception {
        customer.setId(ConnectionProvider.getConnectionProvider().getNextId("customer_SEQ"));

        preparedStatement = connection.prepareStatement(
                "INSERT INTO customer (id,name,family,email,phoneNumber,) VALUES (?,?,?,?,?)"
        );
        preparedStatement.setInt(1, customer.getId());
        preparedStatement.setString(2, customer.getName());
        preparedStatement.setString(3, customer.getEmail());
        preparedStatement.setString(4, customer.getFamily());
        preparedStatement.setString(5, customer.getPhoneNumber());
        preparedStatement.execute();
        return customer;
    }

    @Override
    public Customer edit(Customer customer) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE customer SET family=?, phoneNumber=? WHERE ID=?"
        );
        preparedStatement.setInt(1, customer.getId());
        preparedStatement.setString(2, customer.getFamily());
        preparedStatement.setString(3, customer.getPhoneNumber());
        preparedStatement.execute();
        return customer;
    }

    @Override
    public Customer remove(int id) throws Exception {
        preparedStatement=connection.prepareStatement(
                "delete from customer where id=?"
        );
        preparedStatement.setInt(1,id);
        preparedStatement.execute();
        return null;
    }


    @Override
    public List<Customer> findAll() throws Exception {
        List<Customer> customerList = new ArrayList<>();

        preparedStatement = connection.prepareStatement("SELECT * FROM customer ORDER BY FAMILY");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Customer customer = Customer
                    .builder()
                    .id(resultSet.getInt("id"))
                    .family(resultSet.getString("family"))
                    .phoneNumber(resultSet.getString("phoneNumber"))
                    .build();

            customerList.add(customer);
        }

        return customerList;
    }

    @Override
    public Customer findById(int id) throws Exception {
        preparedStatement=connection.prepareStatement("select from customer where id");
        preparedStatement.setInt(1,id);
        ResultSet resultSet=preparedStatement.executeQuery();
        Customer customer=null;
        if(resultSet.next()){
            customer=Customer
                    .builder()
                    .id(resultSet.getInt("id"))
                    .family(resultSet.getString("family"))
                    .phoneNumber(resultSet.getString("phoneNumber"))
                    .build();

        }
        return customer;
    }



    public Customer findByFamily(String family) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM customer WHERE FAMILY=?");
        preparedStatement.setInt(1, Integer.parseInt(family));
        ResultSet resultSet = preparedStatement.executeQuery();
        Customer customer = null;
        if (resultSet.next()) {
            customer = Customer
                    .builder()
                    .id(resultSet.getInt("id"))
                    .family(resultSet.getString("family"))
                    .phoneNumber(resultSet.getString("phoneNumber"))
                    .build();
        }
        return customer;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
