package lk.ijse.the_thirsty_manager.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SupplierEntity {
    private String supID;
    private String name;
    private String contact;
    private String address;
}
