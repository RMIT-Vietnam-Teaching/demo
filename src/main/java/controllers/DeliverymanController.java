package controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Deliveryman;
import lombok.Getter;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DeliverymanController {
    @Getter
    static Map<Integer, Deliveryman> deliverymen = new HashMap<>();

    static String r = "Operation successful.";

    public static String addUpdate(Integer id, String name, String phone) {
        try {
            deliverymen.put(id, new Deliveryman().setId(id)
                    .setName(name)
                    .setPhoneNumber(phone));
        } catch (Exception e) {
            return e.getMessage();
        }
        return r;
    }
    public static String delete(Integer id) {
        try {
            deliverymen.remove(id);
        } catch (Exception e) {
            return e.getMessage();
        }
        return r;
    }
    public static List<Deliveryman> searchName(String name, boolean asc) {
        return deliverymen.values()
                .stream()
                .filter(e -> e.getName().equals(name))
                .sorted((a,b) -> (asc) ? a.getId() - b.getId() : b.getId()- a.getId())
                .collect(Collectors.toList());
    }
    public static List<Deliveryman> searchPhone(String phone, boolean asc) {
        return deliverymen.values()
                .stream()
                .filter(e -> e.getPhoneNumber().equals(phone))
                .sorted((a,b) -> (asc) ? a.getId() - b.getId() : b.getId()- a.getId())
                .collect(Collectors.toList());
    }

    static ObjectMapper mapper = new ObjectMapper();

    public static void store() {
        try {
            mapper.writeValue(new File("deliverymen.json"), deliverymen);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void read() {
        try {
            deliverymen = mapper.readValue(new File("deliverymen.json"),  new TypeReference<Map<Integer, Deliveryman>>() {});
        } catch (Exception e) {
            e.printStackTrace();;
        }
    }
}
