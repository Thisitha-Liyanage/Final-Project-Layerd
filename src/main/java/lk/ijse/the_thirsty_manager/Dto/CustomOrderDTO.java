package lk.ijse.the_thirsty_manager.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomOrderDTO {
    private String customerID;
    private double unitPrice;
    private String itemName;
    private int Qty;
    private String itemID;
    private String date;
    private String orderID;
    private double totalAmount;
}
