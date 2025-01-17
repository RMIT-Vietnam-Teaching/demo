package entities;

import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Accessors(chain = true)
public class Order {
    Integer id;
    double totalPrice;
    Date dateOfCreation;

    Integer customer, deliveryman;
    @NonNull
    Set<Integer> items = new HashSet<>();
}
