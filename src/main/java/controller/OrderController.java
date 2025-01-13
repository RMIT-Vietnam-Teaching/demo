package controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import model.Item;
import model.Order;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class OrderController {
    @Getter
    static Map<Integer, Order> orders = new HashMap<>();

    public static void addUpdate(Integer id, Date date, Integer customer, Integer deliveryman, Item... items) {
        orders.put(id, new Order().setId(id)
                .setDateOfCreation(date)
                .setCustomer(customer)
                .setDeliveryman(deliveryman)
                        .setPrice(
                                Arrays.stream(items)
                                        .map(Item::getPrice)
                                        .reduce(0.0, Double::sum)
                        )
                .setItems(
                        Arrays.stream(items)
                                .map(Item::getId)
                                .collect(Collectors.toSet())
                ));
        CustomerController.getCustomers().get(customer).addOrder(id);
        DeliverymanController.getDeliverymen().get(deliveryman).addOrder(id);
    }
    public static void deleteOrder(Integer id) {
        CustomerController.getCustomers().get(orders.get(id).getCustomer()).rmOrder(id);
        DeliverymanController.getDeliverymen().get(orders.get(id).getDeliveryman()).rmOrder(id);
        orders.remove(id);
    }
    public static List<Order> searchDate(Date date, boolean isA) {
        return orders.values()
                .stream()
                .filter(customer -> customer.getDateOfCreation().equals(date))
                .sorted((a, b) -> (isA) ? Integer.compare(a.getId(), b.getId()) : Integer.compare(b.getId(), a.getId()))
                .toList();
    }
    public static Order searchId(Integer id, boolean isA) {
        return orders.get(id);
    }
    static ObjectMapper mapper = new ObjectMapper();
    static File file = new File("orders.json");
    public static void store() {
        try {
            mapper.writeValue(file, orders);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    public static void read() {
        try {
            orders = mapper.readValue(file, new TypeReference<Map<Integer, Order>>() {
            });
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
