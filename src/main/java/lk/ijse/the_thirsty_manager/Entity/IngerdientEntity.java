package lk.ijse.the_thirsty_manager.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class IngerdientEntity {
    private String ingredientID;
    private String itemID;
    private String inventoryID;
    private double quantity;
}
