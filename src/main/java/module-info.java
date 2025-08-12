module lk.ijse.the_thirsty_manager.finalproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;
    requires javafx.graphics;
    requires java.desktop;
    requires mysql.connector.j;
    requires java.naming;
    requires modelmapper;

    opens lk.ijse.the_thirsty_manager to javafx.fxml;
    opens lk.ijse.the_thirsty_manager.Dto to javafx.fxml;
    opens lk.ijse.the_thirsty_manager.Controller.InventoryManageController to javafx.fxml;
    opens lk.ijse.the_thirsty_manager.Controller.CustomerManageController to javafx.fxml;
    opens lk.ijse.the_thirsty_manager.Controller.EmployeeManageController to javafx.fxml;

    opens lk.ijse.the_thirsty_manager.Controller.ManageSupplierController to javafx.fxml;
    opens lk.ijse.the_thirsty_manager.Controller to javafx.fxml;
    opens lk.ijse.the_thirsty_manager.Controller.PaymentManageController to javafx.fxml;

    opens lk.ijse.the_thirsty_manager.Controller.ItemManageController to javafx.fxml;
    opens lk.ijse.the_thirsty_manager.Controller.SalaryManageController to javafx.fxml;

    opens lk.ijse.the_thirsty_manager.Dto.TM to javafx.fxml, javafx.base;
    opens lk.ijse.the_thirsty_manager.Controller.AttendanceManage to javafx.fxml;

    opens lk.ijse.the_thirsty_manager.Controller.TableManageController to javafx.fxml;

    opens lk.ijse.the_thirsty_manager.Controller.ManageIngerdientController to javafx.fxml;
    opens lk.ijse.the_thirsty_manager.BO;
    opens lk.ijse.the_thirsty_manager.BO.Custom;
    opens lk.ijse.the_thirsty_manager.DAO;
    opens lk.ijse.the_thirsty_manager.DAO.Custom;
    opens lk.ijse.the_thirsty_manager.DAO.Custom.IMPL;
    opens lk.ijse.the_thirsty_manager.Entity;
    exports lk.ijse.the_thirsty_manager;
    exports lk.ijse.the_thirsty_manager.Dto;


}