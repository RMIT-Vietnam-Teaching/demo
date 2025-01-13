package model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Item {
    Integer id;
    double price;
    String name;
}
