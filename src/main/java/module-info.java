module com.example.tipcalculatorapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.tipcalculatorapp to javafx.fxml;
    exports com.example.tipcalculatorapp;
}