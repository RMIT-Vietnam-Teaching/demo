package controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Item;
import lombok.Getter;

import java.io.File;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class ItemController {
    static int count = 0;
    @Getter
    static TreeMap<Integer, Item> items = new TreeMap<>();
    static String good = "Operation successful.";
    static String bad = "Operation failed.";
    public static String add(String name, double price) {
        try {
            Item item = new Item().setId(++count).setName(name).setPrice(price);
            return (items.putIfAbsent(count, item) == null) ? good : bad;
        } catch (Exception e) {
            return bad;
        }
    }
    public static String update(Integer id, String name, double price) {
        try {
            if (!items.containsKey(id)) {
                return bad;
            } else {
                items.replace(id, new Item().setId(id).setName(name).setPrice(price));
                return good;
            }
        } catch (Exception e) {
            return bad;
        }
    }
    public static String delete(Integer id) {
        if (!items.containsKey(id)) {
            return bad;
        } else {
            items.remove(id);
            return good;
        }
    }
    public static List<Item> searchName(String name, boolean asc) {
        return items.values().stream().filter(c -> c.getName().toLowerCase().contains(name.toLowerCase()))
                .sorted((a,b) -> (asc) ? a.getId() - b.getId() : b.getId() - a.getId())
                .collect(Collectors.toList());
    }
    public static List<Item> searchPrice(double price, boolean asc) {
        return items.values().stream().filter(c -> c.getPrice() == price)
                .sorted((a,b) -> (asc) ? a.getId() - b.getId() : b.getId() - a.getId())
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
            items = mapper.readValue(new File("items.json"), new TypeReference<TreeMap<Integer, Item>>() {});
            count = items.lastKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
