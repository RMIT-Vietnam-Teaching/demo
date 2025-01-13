package controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import model.Customer;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerController {
    @Getter
    static Map<Integer, Customer> customers = new HashMap<>();


    public static void addUpdate(Integer id, String name, String address, String phone) {
        customers.put(id, new Customer().setId(id)
                .setAddress(address)
                .setName(name)
                .setPhoneNumber(phone));
    }
    public static List<Customer> searchName(String name, boolean isA) {
        return customers.values()
                .stream()
                .filter(customer -> customer.getName().equals(name))
                .sorted((a, b) -> (isA) ? Integer.compare(a.getId(), b.getId()) : Integer.compare(b.getId(), a.getId()))
                .toList();
    }
    public static List<Customer> searchPhone(String name, boolean isA) {
        return customers.values()
                .stream()
                .filter(customer -> customer.getPhoneNumber().equals(name))
                .sorted((a, b) -> (isA) ? Integer.compare(a.getId(), b.getId()) : Integer.compare(b.getId(), a.getId()))
                .toList();
    }
    static ObjectMapper mapper = new ObjectMapper();
    static File file = new File("customers.json");
    public static void store() {
        try {
            mapper.writeValue(file, customers);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    public static void read() {
        try {
            customers = mapper.readValue(file, new TypeReference<Map<Integer, Customer>>() {
            });
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
