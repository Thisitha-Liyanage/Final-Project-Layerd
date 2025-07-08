package lk.ijse.the_thirsty_manager.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDto {
    private String inventoryID;
    private String Invname;
    private String unit;
    private double currentStock;
    private String supplierID;
    private double reOrderLevel;
    private String expDate;


}
