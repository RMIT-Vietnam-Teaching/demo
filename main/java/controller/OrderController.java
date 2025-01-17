package controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Customer;
import entities.Deliveryman;
import entities.Item;
import entities.Order;
import lombok.Getter;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class OrderController {
    static int count = 0;
    @Getter
    static TreeMap<Integer, Order> orders = new TreeMap<>();
    static String good = "Operation successful.";
    static String bad = "Operation unsuccessful.";

    public static String add(Date date, Customer customer, Deliveryman deliveryman, Item... items) {
        Order order = new Order().setId(++count).setDateOfCreation(date).setCustomer(customer.getId()).setDeliveryman(deliveryman.getId()).setItems(
                Arrays.stream(items)
                        .map(Item::getId)
                        .collect(Collectors.toSet())
        ).setTotalPrice(Arrays.stream(items)
                .map(Item::getPrice)
                .reduce(0.0, Double::sum));
        CustomerController.getCustomers().get(customer.getId()).addOrder(count);
        DeliverymanController.getDeliverymen().get(deliveryman.getId()).addOrder(count);
        orders.put(count, order);
        return good;
    }
    public static String update(Integer id, Date date, Customer customer, Deliveryman deliveryman, Item... items) {
        if (!orders.containsKey(id)) {
            return bad;
        } else {
            orders.replace(id, new Order().setId(id).setDateOfCreation(date).setCustomer(customer.getId()).setDeliveryman(deliveryman.getId()).setItems(
                    Arrays.stream(items)
                            .map(Item::getId)
                            .collect(Collectors.toSet())
            ).setTotalPrice(Arrays.stream(items)
                    .map(Item::getPrice)
                    .reduce(0.0, Double::sum)));
            return good;
        }
    }
    public static String delete(Integer id) {
        if (!orders.containsKey(id)) {
            return bad;
        } else {
            orders.remove(id);
            return good;
        }
    }
    public static Order searchId(int id) {
            return orders.get(id);
    }
    public static List<Order> searchDate(Date date, boolean asc) {
        return orders.values().stream().filter(c -> c.getDateOfCreation().equals(date))
                .sorted((a,b) -> (asc) ? a.getId() - b.getId() : b.getId() - a.getId())
                .collect(Collectors.toList());
    }
    static ObjectMapper mapper = new ObjectMapper();

    public static void store() {
        try {
            mapper.writeValue(new File("orders.json"), orders);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void read() {
        try {
            orders = mapper.readValue(new File("orders.json"), new TypeReference<TreeMap<Integer, Order>>() {});
            count = orders.lastKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
