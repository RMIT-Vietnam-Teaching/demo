package model;

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

    public void addOrder(Integer o) {
        orders.add(o);
    }
    public void rmOrder(Integer o) {
        orders.remove(o);
    }
}
