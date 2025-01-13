package controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Deliveryman;
import lombok.Getter;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DeliverymanController {
    @Getter
    static Map<Integer, Deliveryman> deliverymen = new HashMap<>();

    public static void addUpdate(int id, String name, String phone) {
        deliverymen.put(id, new Deliveryman().setId(id)
                .setName(name)
                .setPhoneNumber(phone));
    }

    public static void delete(int id) {
        deliverymen.remove(id);
    }

    public static List<Deliveryman> searchName(String name, boolean isAsc) {
        return deliverymen.values()
                .parallelStream()
                .filter(e -> e.getName().equals(name))
                .sorted((a, b) -> (isAsc) ? a.getId() - b.getId() : b.getId() - a.getId())
                .collect(Collectors.toList());
    }

    public static List<Deliveryman> searchPhone(String phone, boolean isAsc) {
        return deliverymen.values()
                .parallelStream()
                .filter(e -> e.getPhoneNumber().equals(phone))
                .sorted((a, b) -> (isAsc) ? a.getId() - b.getId() : b.getId() - a.getId())
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
            deliverymen = mapper.readValue(new File("deliverymen.json"), new TypeReference<Map<Integer, Deliveryman>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
