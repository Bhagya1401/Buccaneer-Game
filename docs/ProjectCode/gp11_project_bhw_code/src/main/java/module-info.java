module uk.ac.aber {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    opens uk.ac.aber.App to javafx.graphics;
    opens uk.ac.aber.Controllers to javafx.fxml;
    opens uk.ac.aber.Game to com.google.gson;
    opens uk.ac.aber.Game.Player to com.google.gson;
    opens uk.ac.aber.Game.Tile to com.google.gson;
    opens uk.ac.aber.Game.Treasure to com.google.gson;
}
