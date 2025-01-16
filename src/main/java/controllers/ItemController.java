package controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Item;
import lombok.Getter;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ItemController {
    @Getter
    static Map<Integer, Item> items = new HashMap<>();

    static String r = "Operation successful.";

    public static String addUpdate(Integer id, String name, double price) {
        try {
            items.put(id, new Item().setId(id)
                    .setName(name)
                    .setPrice(price));
        } catch (Exception e) {
            return e.getMessage();
        }
        return r;
    }
    public static String delete(Integer id) {
        try {
            items.remove(id);
        } catch (Exception e) {
            return e.getMessage();
        }
        return r;
    }
    public static List<Item> searchName(String name, boolean asc) {
        return items.values()
                .stream()
                .filter(e -> e.getName().equals(name))
                .sorted((a,b) -> (asc) ? a.getId() - b.getId() : b.getId()- a.getId())
                .collect(Collectors.toList());
    }
    public static List<Item> searchPrice(double price, boolean asc) {
        return items.values()
                .stream()
                .filter(e -> e.getPrice() == (price))
                .sorted((a,b) -> (asc) ? a.getId() - b.getId() : b.getId()- a.getId())
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
            items = mapper.readValue(new File("items.json"),  new TypeReference<Map<Integer, Item>>() {});
        } catch (Exception e) {
            e.printStackTrace();;
        }
    }
}
