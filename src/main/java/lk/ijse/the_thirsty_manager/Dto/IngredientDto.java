package lk.ijse.the_thirsty_manager.Dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IngredientDto {
    private String ingredientID;
    private String itemID;
    private String inventoryID;
    private double quantity;
}
