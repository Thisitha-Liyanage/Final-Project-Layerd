module lk.ijse.the_thirsty_manager.finalproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;
    requires javafx.graphics;
    requires java.desktop;
    requires mysql.connector.j;
    requires java.naming;

    opens lk.ijse.the_thirsty_manager to javafx.fxml;
    opens lk.ijse.the_thirsty_manager.Model to javafx.fxml;
    opens lk.ijse.the_thirsty_manager.Dto to javafx.fxml;
    opens lk.ijse.the_thirsty_manager.Controller.InventoryManageController to javafx.fxml;
    opens lk.ijse.the_thirsty_manager.Controller.CustomerManageController to javafx.fxml;
    opens lk.ijse.the_thirsty_manager.Controller.EmployeeManageController to javafx.fxml;
    opens lk.ijse.the_thirsty_manager.Model.EmployeeManageModel to javafx.fxml;
    opens lk.ijse.the_thirsty_manager.Model.SupplierManageModel to javafx.fxml;
    opens lk.ijse.the_thirsty_manager.Controller.ManageSupplierController to javafx.fxml;
    opens lk.ijse.the_thirsty_manager.Controller to javafx.fxml;
    opens lk.ijse.the_thirsty_manager.Controller.PaymentManageController to javafx.fxml;
    opens lk.ijse.the_thirsty_manager.Model.PaymentManageModel to javafx.fxml;
    opens lk.ijse.the_thirsty_manager.Controller.ItemManageController to javafx.fxml;
    opens lk.ijse.the_thirsty_manager.Controller.SalaryManageController to javafx.fxml;
    opens lk.ijse.the_thirsty_manager.Model.CustomerManageModel to javafx.fxml;
    opens lk.ijse.the_thirsty_manager.Model.SalaryManageModel to javafx.fxml;
    opens lk.ijse.the_thirsty_manager.Dto.TM to javafx.fxml, javafx.base;
    opens lk.ijse.the_thirsty_manager.Controller.AttendanceManage to javafx.fxml;
    opens lk.ijse.the_thirsty_manager.Model.AttendanceManage to javafx.fxml;
    opens lk.ijse.the_thirsty_manager.Controller.TableManageController to javafx.fxml;
    opens lk.ijse.the_thirsty_manager.Model.TableManageMoled to javafx.fxml;
    exports lk.ijse.the_thirsty_manager;


}