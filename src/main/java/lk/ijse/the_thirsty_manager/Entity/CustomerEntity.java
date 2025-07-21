package lk.ijse.the_thirsty_manager.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerEntity {
    private String customerID;
    private String customerName;
    private String address;
    private String contact;
    private int age;
}
