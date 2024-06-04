package mft.model.da;

import lombok.extern.log4j.Log4j;
import mft.model.entity.Customer;
import mft.model.tools.CRUD;
import mft.model.tools.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class CustomerDa implements AutoCloseable, CRUD<Customer> {
    private  final Connection connection;
    private PreparedStatement preparedStatement;

    public CustomerDa() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
    }

    @Override
    public Customer save(Customer customer) throws Exception {
        customer.setId(ConnectionProvider.getConnectionProvider().getNextId("customer_SEQ"));

        preparedStatement = connection.prepareStatement(
                "insert into CUSTOMER(ID,NAME,FAMILY,EMAIL,PHONE_NUMBER) VALUES (?,?,?,?,?)"
        );
        preparedStatement.setInt(1, customer.getId());
        preparedStatement.setString(2, customer.getName());
        preparedStatement.setString(3, customer.getFamily());
        preparedStatement.setString(4, customer.getEmail());
        preparedStatement.setString(5, customer.getPhoneNumber());
        preparedStatement.execute();
        return customer;
    }

    @Override
    public Customer edit(Customer customer) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE CUSTOMER SET family=?, name=?,email=? ,PHONE_NUMBER=? WHERE ID=?"
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
    public Customer remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "delete from CUSTOMER where id=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }


    @Override
    public List<Customer> findAll() throws Exception {
        List<Customer> customerList = new ArrayList<>();

        preparedStatement = connection.prepareStatement("SELECT * FROM CUSTOMER ORDER BY FAMILY");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Customer customer = Customer
                    .builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .family(resultSet.getString("family"))
                    .phoneNumber(resultSet.getString("phoneNumber"))
                    .email(resultSet.getString("email"))
                    .build();

            customerList.add(customer);
        }

        return customerList;
    }

    @Override
    public Customer findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("select *from customer where id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Customer customer = null;
        if (resultSet.next()) {
            customer = Customer
                    .builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .family(resultSet.getString("family"))
                    .phoneNumber(resultSet.getString("phoneNumber"))
                    .email(resultSet.getString("email"))
                    .build();

        }
        return customer;
    }


    public Customer findByFamily(String family) throws Exception {
        preparedStatement = connection.prepareStatement("select * from CUSTOMER where FAMILY=?");
        preparedStatement.setInt(1, Integer.parseInt(family));
        ResultSet resultSet = preparedStatement.executeQuery();
        Customer customer = null;
        if (resultSet.next()) {
            customer = Customer
                    .builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .family(resultSet.getString("family"))
                    .phoneNumber(resultSet.getString("phoneNumber"))
                    .email(resultSet.getString("email"))
                    .build();
        }
        return customer;
    }

    public Customer findByPhoneNUmber(String phoneNumber) throws Exception {
        preparedStatement = connection.prepareStatement("select *from CUSTOMER where  PHONENUMBER=?");
        preparedStatement.setString(1, phoneNumber);
        ResultSet resultSet = preparedStatement.executeQuery();
        Customer customer = null;
        if (resultSet.next()) {
            customer = Customer
                    .builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .family(resultSet.getString("family"))
                    .phoneNumber(resultSet.getString("phoneNumber"))
                    .email(resultSet.getString("email"))
                    .build();
        }
        return customer;
    }

    public Customer findByEmail(String Email) throws Exception {
        preparedStatement = connection.prepareStatement("select *from CUSTOMER where  EMAIL=?");
        preparedStatement.setString(1, Email);
        ResultSet resultSet = preparedStatement.executeQuery();
        Customer customer = null;
        if (resultSet.next()) {
            customer = Customer
                    .builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .family(resultSet.getString("family"))
                    .phoneNumber(resultSet.getString("phoneNumber"))
                    .email(resultSet.getString("email"))
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
