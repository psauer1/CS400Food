import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ErrorWindow {
    private Stage errorStage;
    private String message;
    private ListView<String> validTypes;
    private HBox outer;
    private TitledPane errorBox;
    private Accordion accordion;

    public ErrorWindow(){
        /*
         * Generic error response window
         *
         * Displays whenever the user interacts with program in an incorrect way
         */
        errorStage = new Stage();	// creates new stage
        errorStage.setTitle("Error");	// names the error stage
        outer = new HBox();
        accordion = new Accordion();
        outer.getChildren().add(accordion);
        Scene errorScene = new Scene(outer, 300, 300);	// error message scene
        errorScene.getStylesheets().add("fx.css");	// apply style sheet
        errorStage.setScene(errorScene);	// set the scene on the error stage
    }

    public void errorMessage(int type, String message){
        errorBox = new TitledPane(message,generateItems(type));
        accordion.getPanes().add(errorBox);
    }

    private VBox generateItems(int type){
        VBox errorRoot = new VBox();	// creates root HBox for the error
        errorRoot.setPadding(new Insets(10, 10, 10, 10));	// sets padding to root
        errorRoot.prefWidthProperty().bind(errorStage.widthProperty().multiply(1.0));	// sets size of root


        Label errorLabel02 = new Label("Valid inputs include:");	// error message
        errorLabel02.setPadding(new Insets(0, 0, 10, 0));
        errorLabel02.setFont(new Font("Arial", 18));
        errorRoot.getChildren().add(errorLabel02);

        validTypes = new ListView<String>();
        if(type==0) {
            validTypes.setItems(FXCollections.observableArrayList("Any String Value")); // displays valid types
        }else if(type==1) {
            validTypes.setItems(FXCollections.observableArrayList("Positive Integers","Positive Decimals")); // displays valid types
        }else if(type==2) {
            validTypes.setItems(FXCollections.observableArrayList("NAMES","Any String Value","OTHER INPUTS","Positive Integers","Positive Decimals")); // displays valid types
        }

        validTypes.setPadding(new Insets(0, 0, 30, 0));
        errorRoot.getChildren().add(validTypes);
        return errorRoot;
    }

    public void show(){
        errorStage.show();
    }

    public void clear(){
        accordion=new Accordion();
    }
}
