package lk.ijse.the_thirsty_manager.Dto.TM;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderTM{
    private String customerID;
    private double unitPrice;
    private String itemName;
    private int Qty;
    private String itemID;
    private String date;
    private String orderID;
    private double totalAmount;


    public OrderTM(String customerID, double unitPrice, String itemName, int qty, String itemID){
        this.customerID = customerID;
        this.itemID = itemID;
        this.itemName = itemName;
        this.unitPrice = unitPrice;
        this.Qty = qty;
    }

    public OrderTM(String orderID , String customerID , double totalAmount , String date){
        this.customerID = customerID;
        this.orderID = orderID;
        this.totalAmount = totalAmount;
        this.date = date;
    }
}
