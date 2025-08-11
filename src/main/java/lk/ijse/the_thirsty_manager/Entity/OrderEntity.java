package lk.ijse.the_thirsty_manager.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderEntity {
    private String orderID;
    private String customerID;
    private double totalAmount;
    private String tableID;
    private String date;

}
