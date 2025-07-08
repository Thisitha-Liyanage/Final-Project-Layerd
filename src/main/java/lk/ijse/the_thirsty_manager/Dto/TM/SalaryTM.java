package lk.ijse.the_thirsty_manager.Dto.TM;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalaryTM {
    private String salaryID;
    private String empID;
    private Double amount;
    private String date;
}
