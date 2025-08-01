/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CustomerDAO;
import java.util.ArrayList;
import java.util.List;
import dto.Customer;

/**
 *
 * @author legiang300304
 */
public class test {
    public static void main(String[] args) {
        List<Customer> list = new ArrayList<>();
        CustomerDAO d = new CustomerDAO();
        list = d.showAllCustomer();
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }
}
