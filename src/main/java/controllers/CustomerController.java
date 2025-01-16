package controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Customer;
import lombok.Getter;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomerController {
    @Getter
    static Map<Integer, Customer> customers = new HashMap<>();

    static String r = "Operation successful.";

    public static String addUpdate(Integer id, String name, String address, String phone) {
        try {
            customers.put(id, new Customer().setId(id)
                    .setName(name)
                    .setAddress(address)
                    .setPhoneNumber(phone));
        } catch (Exception e) {
            return e.getMessage();
        }
        return r;
    }
    public static List<Customer> searchName(String name, boolean asc) {
        return customers.values()
                .stream()
                .filter(customer -> customer.getName().equals(name))
                .sorted((a,b) -> (asc) ? a.getId() - b.getId() : b.getId()- a.getId())
                .collect(Collectors.toList());
    }
    public static List<Customer> searchPhone(String phone, boolean asc) {
        return customers.values()
                .stream()
                .filter(customer -> customer.getPhoneNumber().equals(phone))
                .sorted((a,b) -> (asc) ? a.getId() - b.getId() : b.getId()- a.getId())
                .collect(Collectors.toList());
    }

    static ObjectMapper mapper = new ObjectMapper();

    public static void store() {
        try {
            mapper.writeValue(new File("customers.json"), customers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void read() {
        try {
            customers = mapper.readValue(new File("customers.json"),  new TypeReference<Map<Integer, Customer>>() {});
        } catch (Exception e) {
            e.printStackTrace();;
        }
    }
}
