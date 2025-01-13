package controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import model.Deliveryman;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeliverymanController {
    @Getter
    static Map<Integer, Deliveryman> deliverymen = new HashMap<>();

    public static void addUpdate(Integer id, String name, String phone) {
        deliverymen.put(id, new Deliveryman().setId(id)
                .setName(name)
                .setPhoneNumber(phone));
    }
    public static void deleteDeliveryman(Integer id) {
        deliverymen.remove(id);
    }
    public static List<Deliveryman> searchName(String name, boolean isA) {
        return deliverymen.values()
                .stream()
                .filter(customer -> customer.getName().equals(name))
                .sorted((a, b) -> (isA) ? Integer.compare(a.getId(), b.getId()) : Integer.compare(b.getId(), a.getId()))
                .toList();
    }
    public static List<Deliveryman> searchPhone(String name, boolean isA) {
        return deliverymen.values()
                .stream()
                .filter(customer -> customer.getPhoneNumber().equals(name))
                .sorted((a, b) -> (isA) ? Integer.compare(a.getId(), b.getId()) : Integer.compare(b.getId(), a.getId()))
                .toList();
    }
    static ObjectMapper mapper = new ObjectMapper();
    static File file = new File("deliverymen.json");
    public static void store() {
        try {
            mapper.writeValue(file, deliverymen);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    public static void read() {
        try {
            deliverymen = mapper.readValue(file, new TypeReference<Map<Integer, Deliveryman>>() {
            });
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
