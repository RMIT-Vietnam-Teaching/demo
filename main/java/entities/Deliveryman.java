package entities;

import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

@Data
@Accessors(chain = true)
public class Deliveryman {
    Integer id;
    String name, phoneNumber;

    @NonNull
    Set<Integer> orders = new HashSet<>();

    public boolean addOrder(Integer order) {
        return orders.add(order);
    }
    public boolean rmOrder(Integer order) {
        return orders.remove(order);
    }
}
