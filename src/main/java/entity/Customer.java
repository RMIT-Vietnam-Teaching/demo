package entity;

import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

@Data
@Accessors(chain = true)
public class Customer {
    Integer id;
    String name, address, phoneNumber;

    @NonNull
    Set<Integer> orders = new HashSet<>();

    public void addOrder(int o) {
        orders.add(o);
    }
    public void rmOrder(int o) {
        orders.remove(o);
    }
}
