package lk.ijse.the_thirsty_manager.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemEntity {
    private String itemID;
    private String itemName;
    private String availability;
    private double price;
    private String description;
}
