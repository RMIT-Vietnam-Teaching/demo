package controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import model.Item;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemController {
    @Getter
    static Map<Integer, Item> items = new HashMap<>();

    public static void addUpdate(Integer id, String name, double price) {
        items.put(id, new Item().setId(id)
                .setName(name)
                .setPrice(price));
    }
    public static void deleteItem(Integer id) {
        items.remove(id);
    }
    public static List<Item> searchName(String name, boolean isA) {
        return items.values()
                .stream()
                .filter(customer -> customer.getName().equals(name))
                .sorted((a, b) -> (isA) ? Integer.compare(a.getId(), b.getId()) : Integer.compare(b.getId(), a.getId()))
                .toList();
    }
    public static List<Item> searchPrice(double price, boolean isA) {
        return items.values()
                .stream()
                .filter(customer -> customer.getPrice() == price)
                .sorted((a, b) -> (isA) ? Integer.compare(a.getId(), b.getId()) : Integer.compare(b.getId(), a.getId()))
                .toList();
    }
    static ObjectMapper mapper = new ObjectMapper();
    static File file = new File("items.json");
    public static void store() {
        try {
            mapper.writeValue(file, items);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    public static void read() {
        try {
            items = mapper.readValue(file, new TypeReference<Map<Integer, Item>>() {
            });
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
