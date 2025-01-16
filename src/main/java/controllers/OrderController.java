package controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Customer;
import entities.Deliveryman;
import entities.Item;
import entities.Order;
import lombok.Getter;

import java.io.File;
import java.util.Date;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderController {
    @Getter
    static Map<Integer, Order> orders = new HashMap<>();

    static String r = "Operation successful.";

    public static String addUpdate(Integer id, Date date, Customer customer, Deliveryman deliveryman, Item... items) {
        try {
            orders.put(id, new Order().setId(id)
                    .setDateOfCreation(date)
                    .setCustomer(customer.getId())
                    .setDeliveryman(deliveryman.getId())
                    .setTotalPrice(Arrays.stream(items)
                            .map(Item::getPrice)
                            .reduce(0.0, Double::sum))
                    .setItems(
                            Arrays.stream(items)
                                    .map(Item::getId)
                                    .collect(Collectors.toSet())
                    ));
            CustomerController.getCustomers().get(customer.getId()).addOrder(id);
            DeliverymanController.getDeliverymen().get(deliveryman.getId()).addOrder(id);
        } catch (Exception e) {
            return e.getMessage();
        }
        return r;
    }
    public static String delete(Integer id) {
        try {
            Order order = orders.get(id);
            Integer deliveryman = order.getDeliveryman();
            Integer customer = order.getCustomer();
            CustomerController.getCustomers().get(customer).rmOrder(id);
            DeliverymanController.getDeliverymen().get(deliveryman).rmOrder(id);
            orders.remove(id);
        } catch (Exception e) {
            return e.getMessage();
        }
        return r;
    }
    public static Order searchId(int id, boolean asc) {
        return orders.get(id);
    }
    public static List<Order> searchDate(Date date, boolean asc) {
        return orders.values()
                .stream()
                .filter(e -> e.getDateOfCreation().equals(date))
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
            orders = mapper.readValue(new File("orders.json"),  new TypeReference<Map<Integer, Order>>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
