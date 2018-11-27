package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox root = new HBox();

        root.setSpacing(10);
        root.setPadding(new Insets(15,20, 10,10));

        VBox sort = new VBox();
        VBox analyze = new VBox();
        VBox add = new VBox();


        //Sort Section
        sort.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.25));


        //Analyze Section
        analyze.prefWidthProperty().bind(root.widthProperty().multiply(0.50));
        HBox subAnalyze = new HBox();
        VBox foodItems = new VBox();
        VBox mealItems = new VBox();
        VBox buttons = new VBox();

        foodItems.prefWidthProperty().bind(subAnalyze.widthProperty().multiply(0.40));
        mealItems.prefWidthProperty().bind(subAnalyze.widthProperty().multiply(0.40));
        buttons.prefWidthProperty().bind(subAnalyze.widthProperty().multiply(0.20));

        ListView<String> foodList = new ListView<String>();
        ObservableList<String> items = FXCollections.observableArrayList (
                "Carrot","Lettuce","Granola","Hamburger");
        foodList.setItems(items);
        foodList.prefWidthProperty().bind(foodItems.widthProperty().multiply(1.0));

        ListView<String> mealList = new ListView<String>();
        mealList.setItems(items);
        mealList.prefWidthProperty().bind(mealItems.widthProperty().multiply(1.0));

        mealItems.getChildren().add(mealList);
        foodItems.getChildren().add(foodList);

        buttons.setPadding(new Insets(100,10, 10,10));
        Button addToMeal= new Button("Add");
        addToMeal.prefWidthProperty().bind(buttons.widthProperty().multiply(1.0));

        Button removeFromMeal= new Button("Remove");
        removeFromMeal.prefWidthProperty().bind(buttons.widthProperty().multiply(1.0));
        buttons.getChildren().add(addToMeal);
        buttons.getChildren().add(removeFromMeal);



        subAnalyze.getChildren().add(foodItems);
        subAnalyze.getChildren().add(buttons);
        subAnalyze.getChildren().add(mealItems);

        analyze.getChildren().add(subAnalyze);





        //Add Section
        add.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.25));


        // Button 1
        Button button1= new Button("Button1");
        sort.getChildren().add(button1);


        // Button 2
        Button button2 = new Button("Button2");
        button2.setPrefSize(100, 100);
        add.getChildren().add(button2);

        // CheckBox
        CheckBox checkBox = new CheckBox("Check Box");

        root.getChildren().add(sort);
        root.getChildren().add(analyze);
        root.getChildren().add(add);



        // RadioButton
        RadioButton radioButton = new RadioButton("Radio Button");

        Scene scene = new Scene(root, 1000, 750);

        primaryStage.setTitle("ALL THE RIGHT FOOD IN ALL THE RIGHT PLACES");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
