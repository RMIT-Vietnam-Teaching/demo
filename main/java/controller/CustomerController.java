package controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Customer;
import lombok.Getter;

import java.io.File;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class CustomerController {
    static int count = 0;
    @Getter
    static TreeMap<Integer, Customer> customers = new TreeMap<>();
    static String good = "Operation successful.";
    static String bad = "Operation unsuccessful.";

    public static String add(String name, String address, String phone) {
        ++count;
        customers.put(count, new Customer().setId(count).setName(name).setAddress(address).setPhoneNumber(phone));
        return good;
    }
    public static String update(Integer id, String name, String address, String phone) {
        if (!customers.containsKey(id)) {
            return bad;
        } else {
            customers.replace(id, new Customer().setId(id).setName(name).setAddress(address).setPhoneNumber(phone));
            return good;
        }
    }
    public static List<Customer> searchName(String name, boolean asc) {
        return customers.values().stream().filter(c -> c.getName().toLowerCase().contains(name.toLowerCase()))
                .sorted((a,b) -> (asc) ? a.getId() - b.getId() : b.getId() - a.getId())
                .collect(Collectors.toList());
    }
    public static List<Customer> searchPhone(String phone, boolean asc) {
        return customers.values().stream().filter(c -> c.getPhoneNumber().toLowerCase().contains(phone.toLowerCase()))
                .sorted((a,b) -> (asc) ? a.getId() - b.getId() : b.getId() - a.getId())
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
            customers = mapper.readValue(new File("customers.json"), new TypeReference<TreeMap<Integer, Customer>>() {});
            count = customers.lastKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
