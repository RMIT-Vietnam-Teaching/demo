package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Item;
import entity.Order;
import lombok.Getter;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderController {
    @Getter
    static Map<Integer, Order> orders = new HashMap<>();

    public static void addUpdate(int id, Date date, int customer, int deliveryman, Item... items) {
        CustomerController.getCustomers().get(customer).addOrder(id);
        DeliverymanController.getDeliverymen().get(deliveryman).addOrder(id);
        orders.put(id, new Order().setId(id)
                .setDateOfCreation(date)
                .setCustomer(customer)
                .setDeliveryman(deliveryman)
                        .setTotalPrice(Arrays.stream(items)
                                .parallel()
                                .map(Item::getPrice)
                                .reduce(0.0, Double::sum))
                .setItems(Arrays.stream(items)
                        .parallel()
                        .map(Item::getId)
                        .collect(Collectors.toSet())));
    }

    public static void delete(int id) {
        CustomerController.getCustomers().get(orders.get(id).getCustomer()).rmOrder(id);
        DeliverymanController.getDeliverymen().get(orders.get(id).getDeliveryman()).rmOrder(id);
        orders.remove(id);
    }

    public static Order searchId(int id) {
        return orders.get(id);
    }

    public static List<Order> searchDate(Date date, boolean isAsc) {
        return orders.values()
                .parallelStream()
                .filter(e -> e.getDateOfCreation().equals(date))
                .sorted((a, b) -> (isAsc) ? a.getId() - b.getId() : b.getId() - a.getId())
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
            orders = mapper.readValue(new File("orders.json"), Map.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
