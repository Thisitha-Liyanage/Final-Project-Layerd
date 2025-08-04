package lk.ijse.the_thirsty_manager.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentEntity {
    private String paymentID;
    private String paymentMethod;
    private Double totalAmount;
    private String orderID;
    private String date;
}
