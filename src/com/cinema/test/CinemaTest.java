package com.cinema.test;

import com.cinema.dao.CustomerDAO;
import com.cinema.model.Customer;

import java.io.*;
import java.security.KeyStore;
import java.sql.SQLException;
import java.util.List;

public class CinemaTest {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static CustomerDAO customerDAO = new CustomerDAO();
    public static void customerAction() {
        System.out.println("Choose an action: ");
        System.out.println("1: Find Customer by ID");
        System.out.println("2: Get All Customers");
        System.out.println("3: Create a New Customer");
        System.out.println("4: Delet a Customer");
        System.out.println("5: Exit");
    }

    public static void main(String[] args) throws SQLException, IOException {
        boolean exit = true;
        do {
            customerAction();
            int choice = Integer.parseInt(br.readLine());

            switch (choice) {
                case 1:
                    findCustomer();
                    break;
                case 2:
                    getAllCustomers();
                    break;
                case 3:
                    registerCustomer();
                    break;
                case 4:
                    destroyCustomer();
                    break;
                case 5:
                    exit = false;
                    System.out.println("Exiting the application");
                    break;
            }


        }while (exit);

    }

    private static void findCustomer() throws IOException, SQLException {
        System.out.println("Enter customer id: ");
        int id = Integer.parseInt(br.readLine());
        Customer customer = customerDAO.findById(id);
        System.out.println(customer);

    }

    private static void getAllCustomers() throws SQLException {
        System.out.println("***All customers***");
        List<Customer> customers = customerDAO.getAll();
        for (Customer c : customers) {
            System.out.println(c);
        }
    }

    private static void registerCustomer() throws IOException, SQLException {
        System.out.println("Enter customer name: ");
        String name = br.readLine();
        Customer customer = new Customer();
        customer.setName(name);
        customerDAO.create(customer);
    }

    private static void destroyCustomer() throws IOException, SQLException {
        System.out.println("Enter customer id: ");
        int id = Integer.parseInt(br.readLine());
        Customer customer = new Customer();
        customer.setId(id);
        customerDAO.delete(customer);
    }

}
