package lk.ijse.the_thirsty_manager.Dto.TM;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class EmployeeTM {
    private String employeeID;
    private String name;
    private String role;
    private String contact;
    private double SPD;

}
