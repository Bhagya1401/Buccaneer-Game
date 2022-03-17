module uk.ac.aber {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    opens uk.ac.aber.App to javafx.graphics;
    opens uk.ac.aber.Controllers to javafx.fxml;
}
