package lk.ijse.the_thirsty_manager.Dto.TM;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IngredientTM {
    private String ingredientID;
    private String itemID;
    private String inventoryID;
    private double quantity;
}
