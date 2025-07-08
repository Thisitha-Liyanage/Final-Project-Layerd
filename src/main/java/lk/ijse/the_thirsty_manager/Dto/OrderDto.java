package lk.ijse.the_thirsty_manager.Dto;

import lk.ijse.the_thirsty_manager.Dto.TM.OrderTM;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private String orderID;
    private String customerID;
    private String ItemID;
    private String itemName;
    private double totalAmount;
    private String tableID;
    private String date;
    private int quantity;
    private double unitPrice;


    public OrderDto(String customerID, double unitPrice, String itemName, int qty, String itemID){
        this.customerID = customerID;
        this.ItemID = itemID;
        this.itemName = itemName;
        this.unitPrice = unitPrice;
        this.quantity = qty;
    }

}
