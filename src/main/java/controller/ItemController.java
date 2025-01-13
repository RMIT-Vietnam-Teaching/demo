package controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Item;
import lombok.Getter;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ItemController {
    @Getter
    static Map<Integer, Item> items = new HashMap<>();

    public static void addUpdate(int id, String name, double price) {
        items.put(id, new Item().setId(id)
                .setName(name)
                .setPrice(price));
    }

    public static void delete(int id) {
        items.remove(id);
    }

    public static List<Item> searchName(String name, boolean isAsc) {
        return items.values()
                .parallelStream()
                .filter(e -> e.getName().equals(name))
                .sorted((a, b) -> (isAsc) ? a.getId() - b.getId() : b.getId() - a.getId())
                .collect(Collectors.toList());
    }

    public static List<Item> searchPrice(double price, boolean isAsc) {
        return items.values()
                .parallelStream()
                .filter(e -> e.getPrice() == price)
                .sorted((a, b) -> (isAsc) ? a.getId() - b.getId() : b.getId() - a.getId())
                .collect(Collectors.toList());
    }

    static ObjectMapper mapper = new ObjectMapper();
    public static void store() {
        try {
            mapper.writeValue(new File("items.json"), items);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void read() {
        try {
            items = mapper.readValue(new File("items.json"), new TypeReference<Map<Integer, Item>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
