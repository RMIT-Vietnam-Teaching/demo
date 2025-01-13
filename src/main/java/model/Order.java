package model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Accessors(chain = true)
public class Order {
    Integer id;
    double price;
    Date dateOfCreation;

    Integer customer;
    Set<Integer> items = new HashSet<>();
    Integer deliveryman;
}
