package lk.ijse.the_thirsty_manager.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto  {
    private String customerID;
    private String customerName;
    private String address;
    private String contact;
    private int age;
}
