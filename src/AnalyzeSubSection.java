import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class AnalyzeSubSection {
    public Button addToMeal;
    public Button removeFromMeal;
    public Button clear;
    public Button analyzeMeal;
    public ListView<String> foodList;
    public ListView<String> mealList;
    public VBox analyze;

    public AnalyzeSubSection(HBox root){
        /*
         * Meal analysis vertical section
         */
        analyze = new VBox(); // creates VBox for the main function of the program
        analyze.prefWidthProperty().bind(root.widthProperty().multiply(0.67));

        HBox subAnalyze = new HBox(); // creates HBox within the HBox for analyzing the meal
        subAnalyze.setPadding(new Insets(0, 0, 10, 0));

        VBox foodItems = new VBox(); // creates VBox for all of the food items
        VBox mealItems = new VBox(); // creates VBox for the current meal to be analyzed
        VBox buttons = new VBox(); // creates VBox for buttons to add and remove from the meal list
        foodItems.prefWidthProperty().bind(subAnalyze.widthProperty().multiply(0.40));
        mealItems.prefWidthProperty().bind(subAnalyze.widthProperty().multiply(0.40));
        buttons.prefWidthProperty().bind(subAnalyze.widthProperty().multiply(0.20));

        // FoodItem list label
        Label foodItemsLabel = new Label("Food Items");
        foodItemsLabel.setPadding(new Insets(0, 0, 10, 0));
        foodItemsLabel.setFont(new Font("Arial", 16));
        foodItems.getChildren().add(foodItemsLabel);

        // Meal List label
        Label mealItemsLabel = new Label("Meal Items");
        mealItemsLabel.setPadding(new Insets(0, 0, 10, 0));
        mealItemsLabel.setFont(new Font("Arial", 16));
        mealItems.getChildren().add(mealItemsLabel);

        foodList = new ListView<String>(); // creates list for the food
        ObservableList<String> items = FXCollections.observableArrayList(); // displays names from
        // foodList
        foodList.setItems(items); // set items into the food list
        foodList.prefWidthProperty().bind(foodItems.widthProperty().multiply(1.0));

        mealList = new ListView<String>(); // creates list for the meal
        ObservableList<String> itemsInMeal = FXCollections.observableArrayList(); // displays names
        // from menu
        // foodlist
        mealList.setItems(itemsInMeal); // adds meal list to the list view for meal
        mealList.prefWidthProperty().bind(mealItems.widthProperty().multiply(1.0));
        mealItems.getChildren().add(mealList); // adds meal list to the meal items VBox

        foodItems.getChildren().add(foodList); // adds food list to the food items VBox
        buttons.setPadding(new Insets(100, 10, 10, 10));

        HBox addCon = new HBox();
        addCon.setPadding(new Insets(0, 0, 10, 0));
        addToMeal = new Button("Add"); // creates button for adding items from food to meal list
        addToMeal.prefWidthProperty().bind(buttons.widthProperty().multiply(1.0));

        HBox removeCon = new HBox();
        removeCon.setPadding(new Insets(0, 0, 10, 0));
        removeFromMeal = new Button("Remove"); // creates button for removing item from meal list
        removeFromMeal.prefWidthProperty().bind(buttons.widthProperty().multiply(1.0));
        clear = new Button("Clear"); // creates button for removing item from meal list
        clear.prefWidthProperty().bind(buttons.widthProperty().multiply(1.0));
        buttons.getChildren().add(clear); // adds add button to the button VBox
        addCon.getChildren().add(addToMeal); // adds add button to the button VBox
        removeCon.getChildren().add(addToMeal); // adds add button to the button VBox
        buttons.getChildren().add(addCon);
        buttons.getChildren().add(removeCon);
        buttons.getChildren().add(removeFromMeal); // adds remove button to the button VBox
        subAnalyze.getChildren().add(foodItems); // adds food VBox to the sub-analyze HBox
        subAnalyze.getChildren().add(buttons); // adds buttons VBox to the sub-analyze HBox
        subAnalyze.getChildren().add(mealItems); // adds meal VBox to the sub-analyze HBox

        analyzeMeal = new Button("Analyze Meal"); // creates button to analyze the meal
        analyzeMeal.prefWidthProperty().bind(analyze.widthProperty().multiply(1.0));
        analyze.getChildren().add(subAnalyze); // adds the sub-analyze HBox to the main analyze HBox
        analyze.getChildren().add(analyzeMeal); // adds the analyze button HBox to the main analyze HBox

    }

}
