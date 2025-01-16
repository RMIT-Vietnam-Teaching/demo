package entities;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Item {
    Integer id;
    String name;
    double price;
}
