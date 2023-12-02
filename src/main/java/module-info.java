module com.example.demo3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;
    requires java.sql;

    opens com.example.demo3 to javafx.fxml;
    opens com.example.demo3.Controllers to javafx.fxml;
    exports com.example.demo3;
    exports com.example.demo3.Entities;
    exports com.example.demo3.DAOs;
    exports com.example.demo3.Controllers;
    opens com.example.demo3.Entities to javafx.fxml;

}
