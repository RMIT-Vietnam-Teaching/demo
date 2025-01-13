package controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Customer;
import lombok.Getter;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomerController {
    @Getter
    static Map<Integer, Customer> customers = new HashMap<>();

    public static void addUpdate(int id, String name, String address, String phone) {
        customers.put(id, new Customer().setId(id)
                .setAddress(address)
                .setName(name)
                .setPhoneNumber(phone));
    }

    public static List<Customer> searchName(String name, boolean isAsc) {
        return customers.values()
                .parallelStream()
                .filter(e -> e.getName().equals(name))
                .sorted((a, b) -> (isAsc) ? a.getId() - b.getId() : b.getId() - a.getId())
                .collect(Collectors.toList());
    }

    public static List<Customer> searchPhone(String phone, boolean isAsc) {
        return customers.values()
                .parallelStream()
                .filter(e -> e.getPhoneNumber().equals(phone))
                .sorted((a, b) -> (isAsc) ? a.getId() - b.getId() : b.getId() - a.getId())
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
            customers = mapper.readValue(new File("customers.json"), new TypeReference<Map<Integer, Customer>>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
