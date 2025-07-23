package lk.ijse.the_thirsty_manager.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AttendanceEntity {
    private String attenID;
    private String date;
    private String empID;
    private String status;
}
