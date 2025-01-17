package controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Deliveryman;
import lombok.Getter;

import java.io.File;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class DeliverymanController {
    static int count = 0;
    @Getter
    static TreeMap<Integer, Deliveryman> deliverymen = new TreeMap<>();
    static String good = "Operation successful.";
    static String bad = "Operation unsuccessful.";

    public static String add(String name, String phone) {
        Deliveryman deliveryman = new Deliveryman().setId(++count).setName(name).setPhoneNumber(phone);
        return (deliverymen.putIfAbsent(count, deliveryman) == null) ? good : bad;
    }
    public static String update(Integer id, String name, String phone) {
        if (!deliverymen.containsKey(id)) {
            return bad;
        } else {
            deliverymen.replace(id, new Deliveryman().setId(id).setName(name).setPhoneNumber(phone));
            return good;
        }
    }
    public static String delete(Integer id) {
        if (!deliverymen.containsKey(id)) {
            return bad;
        } else {
            deliverymen.remove(id);
            return good;
        }
    }
    public static List<Deliveryman> searchName(String name, boolean asc) {
        return deliverymen.values().stream().filter(c -> c.getName().toLowerCase().contains(name.toLowerCase()))
                .sorted((a,b) -> (asc) ? a.getId() - b.getId() : b.getId() - a.getId())
                .collect(Collectors.toList());
    }
    public static List<Deliveryman> searchPhone(String phone, boolean asc) {
        return deliverymen.values().stream().filter(c -> c.getPhoneNumber().toLowerCase().contains(phone.toLowerCase()))
                .sorted((a,b) -> (asc) ? a.getId() - b.getId() : b.getId() - a.getId())
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
            deliverymen = mapper.readValue(new File("deliverymen.json"), new TypeReference<TreeMap<Integer, Deliveryman>>() {});
            count = deliverymen.lastKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
