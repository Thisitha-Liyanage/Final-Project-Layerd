package lk.ijse.the_thirsty_manager.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InventoryEntity {
    private String inventoryID;
    private String Invname;
    private String unit;
    private double currentStock;
    private String supplierID;
    private double reOrderLevel;
    private String expDate;
}
