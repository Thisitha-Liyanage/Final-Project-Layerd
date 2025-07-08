package lk.ijse.the_thirsty_manager.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TableDto {
    private String tableID;
    private int noOfSeat;
    private String status;
}
