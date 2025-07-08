package lk.ijse.the_thirsty_manager.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
    private String paymentID;
    private String paymentMethod;
    private Double totalAmount;
    private String orderID;
    private String date;
}
