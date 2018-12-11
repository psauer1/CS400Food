import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ErrorWindow {
    private Stage errorStage;
    private String message;
    private Label errorLabel01;

    public ErrorWindow(){
        /*
         * Generic error response window
         *
         * Displays whenever the user interacts with program in an incorrect way
         */
        errorStage = new Stage();	// creates new stage
        errorStage.setTitle("Error");	// names the error stage

        VBox errorRoot = new VBox();	// creates root HBox for the error
        errorRoot.setPadding(new Insets(0, 0, 10, 0));	// sets padding to root
        errorRoot.prefWidthProperty().bind(errorStage.widthProperty().multiply(1.0));	// sets size of root
        errorLabel01 = new Label("   Invalid input");	// error message
        errorLabel01.setPadding(new Insets(0, 0, 30, 0));
        errorLabel01.setFont(new Font("Arial", 18));
        errorRoot.getChildren().add(errorLabel01);

        Label errorLabel02 = new Label("   Valid inputs include:");	// error message
        errorLabel02.setPadding(new Insets(0, 0, 30, 0));
        errorLabel02.setFont(new Font("Arial", 18));
        errorRoot.getChildren().add(errorLabel02);

        Label errorLabel03 = new Label("   - Zero or positive values");	// error message
        errorLabel03.setPadding(new Insets(0, 0, 30, 0));
        errorLabel03.setFont(new Font("Arial", 18));
        errorRoot.getChildren().add(errorLabel03);

        Label errorLabel04 = new Label("   - Float, double, or integer");	// error message
        errorLabel04.setPadding(new Insets(0, 0, 30, 0));
        errorLabel04.setFont(new Font("Arial", 18));
        errorRoot.getChildren().add(errorLabel04);

        Scene errorScene = new Scene(errorRoot, 300, 200);	// error message scene
        errorStage.setScene(errorScene);	// set the scene on the error stage
    }

    public void errorMessage(String message){
        this.message=message;
        errorLabel01.setText(message);
    }

    public void show(){
        errorStage.show();
    }
}
