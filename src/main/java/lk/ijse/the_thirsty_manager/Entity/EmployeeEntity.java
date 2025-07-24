package lk.ijse.the_thirsty_manager.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeEntity {
    private String employeeID;
    private String name;
    private String role;
    private String contact;
    private double SPD;
}
