import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Main class creates and pops up the Graphical User Interface for the meal analysis.
 * 
 * @author Peter Sauer, Riley Ley
 */
public class Main extends Application {

	/**
	 * Starts the GUI and sets up the stage, scenes, buttons, and other internal features.
	 * 
	 * @param primaryStage
	 */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FoodList all = new FoodList();
        AtomicReference<FoodList> display = new AtomicReference<>(new FoodList());
        FoodList menu = new FoodList();

        HBox root = new HBox();	// creates root HBox
        root.setSpacing(10);
        root.setPadding(new Insets(15,20,10,10));
        root.prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.9));	// sets the height parameters of the HBox
        VBox sort = new VBox();	// creates VBox for the sorting of the program
        VBox analyze = new VBox();	// creates VBox for the main function of the program
        VBox add = new VBox();	// creates VBox for adding food items to the program

        //Sort Section
        Label sortLabel = new Label("Sort Foods");
        sortLabel.setPadding(new Insets(0,0, 30,0));
        sortLabel.setFont(new Font("Arial",24));
        sort.getChildren().add(sortLabel);	// add label to the sort VBox
        sort.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.25));

        HBox name = new HBox();	// HBox for sorting by name
        name.setPadding(new Insets(0,0, 10,0));
        name.prefWidthProperty().bind(sort.widthProperty().multiply(1.0));
        CheckBox nameBox = new CheckBox("Name");	// creates check box for sorting by name
        nameBox.prefWidthProperty().bind(name.widthProperty().multiply(0.5));
        TextField lowname = new TextField("");
        lowname.prefWidthProperty().bind(name.widthProperty().multiply(.5));
        name.getChildren().add(nameBox);	// adds check box to the HBox
        name.getChildren().add(lowname);	// adds text field for name to HBox
        sort.getChildren().add(name);	// adds the HBox to the sort VBox
        sort.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.25));

        HBox calories = new HBox();	// HBox for sorting by calories
        calories.setPadding(new Insets(0,0, 10,0));
        calories.prefWidthProperty().bind(sort.widthProperty().multiply(1.0));
        CheckBox caloriesBox = new CheckBox("Calories");	// creates check box for sorting by calories
        caloriesBox.prefWidthProperty().bind(calories.widthProperty().multiply(0.5));
        TextField lowCalories = new TextField("Low");	// creates text input for low end of calories
        lowCalories.prefWidthProperty().bind(calories.widthProperty().multiply(.250));
        TextField highCalories = new TextField("High");	// creates text input for high end of calories
        highCalories.prefWidthProperty().bind(calories.widthProperty().multiply(.250));
        calories.getChildren().add(caloriesBox);	// adds check box to the HBox
        calories.getChildren().add(lowCalories);	// adds low end text field to HBox
        calories.getChildren().add(highCalories);	// adds high end text field to HBox
        sort.getChildren().add(calories);	// adds the HBox to the sort VBox
        sort.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.25));
        
        HBox fats = new HBox(); // HBox for sorting by fats
        fats.setPadding(new Insets(0,0, 10,0));
        fats.prefWidthProperty().bind(sort.widthProperty().multiply(1.0));
        CheckBox fatsBox = new CheckBox("Fats");	// creates check box for sorting by fats
        fatsBox.prefWidthProperty().bind(fats.widthProperty().multiply(0.5));
        TextField lowfats = new TextField("Low");	// creates text input for low end of fats
        lowfats.prefWidthProperty().bind(fats.widthProperty().multiply(.250));
        TextField highfats = new TextField("High");	// creates text input for high end of fats
        highfats.prefWidthProperty().bind(fats.widthProperty().multiply(.250));
        fats.getChildren().add(fatsBox);	// adds check box to the HBox
        fats.getChildren().add(lowfats);	// adds low end text field to HBox
        fats.getChildren().add(highfats);	// adds high end text field to HBox
        sort.getChildren().add(fats);	// adds the HBox to the sort VBox
        sort.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.25));
        
        HBox carbs = new HBox();	// HBox for sorting by carbs
        carbs.setPadding(new Insets(0,0, 10,0));
        carbs.prefWidthProperty().bind(sort.widthProperty().multiply(1.0));
        CheckBox carbsBox = new CheckBox("Carbs");	// creates check box for sorting by carbs
        carbsBox.prefWidthProperty().bind(carbs.widthProperty().multiply(0.5));
        TextField lowcarbs = new TextField("Low");	// creates text input for low end of fats
        lowcarbs.prefWidthProperty().bind(carbs.widthProperty().multiply(.250));
        TextField highcarbs = new TextField("High");	// creates text input for high end of fats
        highcarbs.prefWidthProperty().bind(carbs.widthProperty().multiply(.250));
        carbs.getChildren().add(carbsBox);	// adds check box to the HBox
        carbs.getChildren().add(lowcarbs);	// adds low end text field to HBox
        carbs.getChildren().add(highcarbs);	// adds high end text field to HBox
        sort.getChildren().add(carbs);	// adds the HBox to the sort VBox
        sort.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.25));
        
        HBox fiber = new HBox();	// HBox for sorting by carbs
        fiber.setPadding(new Insets(0,0, 10,0));
        fiber.prefWidthProperty().bind(sort.widthProperty().multiply(1.0));
        CheckBox fiberBox = new CheckBox("Fiber");	// creates check box for searching by fiber
        fiberBox.prefWidthProperty().bind(fiber.widthProperty().multiply(0.5));
        TextField lowfiber = new TextField("Low");	// creates text input for low end of fats
        lowfiber.prefWidthProperty().bind(fiber.widthProperty().multiply(.250));
        TextField highfiber = new TextField("High");	// creates text input for high end of fats
        highfiber.prefWidthProperty().bind(fiber.widthProperty().multiply(.250));
        fiber.getChildren().add(fiberBox);	// adds check box to the HBox
        fiber.getChildren().add(lowfiber);	// adds low end text field to HBox
        fiber.getChildren().add(highfiber);	// adds high end text field to HBox
        sort.getChildren().add(fiber);	// adds the HBox to the sort VBox
        sort.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.25));
        
        HBox protein = new HBox();	// HBox for sorting by protein
        protein.setPadding(new Insets(0,0, 10,0));
        protein.prefWidthProperty().bind(sort.widthProperty().multiply(1.0));
        CheckBox proteinBox = new CheckBox("Protein");	// creates check box for sorting by protein
        proteinBox.prefWidthProperty().bind(protein.widthProperty().multiply(0.5));
        TextField lowprotein = new TextField("Low");	// creates text input for low end of protein
        lowprotein.prefWidthProperty().bind(protein.widthProperty().multiply(.250));
        TextField highprotein = new TextField("High");	// creates text input for the high end of protein
        highprotein.prefWidthProperty().bind(protein.widthProperty().multiply(.250));
        protein.getChildren().add(proteinBox);	// adds check box to the HBox
        protein.getChildren().add(lowprotein);	// adds low end text field to HBox
        protein.getChildren().add(highprotein);	// adds high end text field to HBox
        sort.getChildren().add(protein);	// adds the HBox to the the sort VBox
        
        Button sortFood = new Button("Sort Food");	// creates new button to Sort Food
        sortFood.prefWidthProperty().bind(sort.widthProperty().multiply(1.0));
        sort.getChildren().add(sortFood);	// adds button to the sort VBox

        //Analyze Section
        Label analyzeLabel = new Label("Analyze Meal");	// creates button for Anaylzing the meal
        analyzeLabel.setPadding(new Insets(0,0, 30,0));
        analyzeLabel.setFont(new Font("Arial",24));
        analyze.getChildren().add(analyzeLabel);	// adds the label to the analyze VBox
        analyze.prefWidthProperty().bind(root.widthProperty().multiply(0.50));
        
        HBox subAnalyze = new HBox();	// creates HBox within the HBox for analyzing the meal
        subAnalyze.setPadding(new Insets(0,0, 10,0));
        
        VBox foodItems = new VBox();	// creates VBox for all of the food items
        VBox mealItems = new VBox();	// creates VBox for the current meal to be analyzed
        VBox buttons = new VBox();	// creates VBox for buttons to add and remove from the meal list
        foodItems.prefWidthProperty().bind(subAnalyze.widthProperty().multiply(0.40));
        mealItems.prefWidthProperty().bind(subAnalyze.widthProperty().multiply(0.40));
        buttons.prefWidthProperty().bind(subAnalyze.widthProperty().multiply(0.20));
       
        // Food list label
        Label foodItemsLabel = new Label("Food Items");
        foodItemsLabel.setPadding(new Insets(0,0, 10,0));
        foodItemsLabel.setFont(new Font("Arial",16));
        foodItems.getChildren().add(foodItemsLabel);
        // Meal List label
        Label mealItemsLabel = new Label("Meal Items");
        mealItemsLabel.setPadding(new Insets(0,0, 10,0));
        mealItemsLabel.setFont(new Font("Arial",16));
        mealItems.getChildren().add(mealItemsLabel);
        
        ListView<String> foodList = new ListView<String>();	// creates list for the food
        ObservableList<String> items = FXCollections.observableArrayList (all.getNames());	// displays names from foodList
        foodList.setItems(items);	// set items into the food list
        foodList.prefWidthProperty().bind(foodItems.widthProperty().multiply(1.0));
        
        ListView<String> mealList = new ListView<String>();	// creates list for the meal
        ObservableList<String> itemsInMeal = FXCollections.observableArrayList (menu.getNames());	// displays names from menu foodlist
        mealList.setItems(itemsInMeal);	// adds meal list to the list view for meal
        mealList.prefWidthProperty().bind(mealItems.widthProperty().multiply(1.0));
        mealItems.getChildren().add(mealList);	// adds meal list to the meal items VBox
        
        foodItems.getChildren().add(foodList);	// adds food list to the food items VBox
        buttons.setPadding(new Insets(100,10, 10,10));
        
        Button addToMeal= new Button("Add");	// creates button for adding items from food to meal list
        addToMeal.prefWidthProperty().bind(buttons.widthProperty().multiply(1.0));
        
        Button removeFromMeal= new Button("Remove");	// creates button for removing item from meal list
        removeFromMeal.prefWidthProperty().bind(buttons.widthProperty().multiply(1.0));
        buttons.getChildren().add(addToMeal);	// adds add button to the button VBox
        buttons.getChildren().add(removeFromMeal);	// adds remove button to the button VBox
        subAnalyze.getChildren().add(foodItems);	// adds food VBox to the sub-analyze HBox	
        subAnalyze.getChildren().add(buttons);	// adds buttons VBox to the sub-analyze HBox
        subAnalyze.getChildren().add(mealItems);	// adds meal VBox to the sub-analyze HBox
        
        Button analyzeMeal = new Button("Analyze Meal");	// creates button to analyze the meal
        analyzeMeal.prefWidthProperty().bind(analyze.widthProperty().multiply(1.0));
        analyze.getChildren().add(subAnalyze);	// adds the sub-analyze HBox to the main analyze HBox
        analyze.getChildren().add(analyzeMeal);	// adds the analyze button HBox to the main analyze HBox

        //Add Section
        Label addLabel = new Label("Add Food");	// new label for add VBox
        addLabel.setPadding(new Insets(0,0, 30,0));
        addLabel.setFont(new Font("Arial",24));
        add.getChildren().add(addLabel);	// add the label to the add VBox
        add.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.25));
        
        HBox aname = new HBox();	// add name HBox
        aname.setPadding(new Insets(0,0, 10,0));
        aname.prefWidthProperty().bind(add.widthProperty().multiply(1.0));
        Label anameBox = new Label("Name");	// label for adding name of food
        anameBox.prefWidthProperty().bind(aname.widthProperty().multiply(0.25));
        TextField entername = new TextField("");	// text input for name of food
        entername.prefWidthProperty().bind(aname.widthProperty().multiply(.75));
        aname.getChildren().add(anameBox);	// add the name label to the add name HBox
        aname.getChildren().add(entername);	// add the text input to the add name HBox
        add.getChildren().add(aname);	// add the add name HBox to the add VBox
        
        HBox acalories = new HBox();	// add calories HBox
        acalories.setPadding(new Insets(0,0, 10,0));
        acalories.prefWidthProperty().bind(add.widthProperty().multiply(1.0));
        Label acaloriesBox = new Label("Calories");	// label for adding calories of food
        acaloriesBox.prefWidthProperty().bind(acalories.widthProperty().multiply(0.25));
        TextField entercalories = new TextField("");	// text input for calories of food
        entercalories.prefWidthProperty().bind(acalories.widthProperty().multiply(.75));
        acalories.getChildren().add(acaloriesBox);	// add calories label to the calories HBox
        acalories.getChildren().add(entercalories);	// add calories input to the calories HBox
        add.getChildren().add(acalories);	// add the calories HBox to the add VBox
        
        HBox afats = new HBox();	// add fats HBox
        afats.setPadding(new Insets(0,0, 10,0));
        afats.prefWidthProperty().bind(add.widthProperty().multiply(1.0));
        Label afatsBox = new Label("Fats");	// label for fats HBox
        afatsBox.prefWidthProperty().bind(afats.widthProperty().multiply(0.25));
        TextField enterfats = new TextField("");	// text input for fats
        enterfats.prefWidthProperty().bind(afats.widthProperty().multiply(.75));
        afats.getChildren().add(afatsBox);	// add fats label to add fats HBox
        afats.getChildren().add(enterfats);	// add fats input to add fats HBox
        add.getChildren().add(afats);	// add fats HBox to the add VBox
        
        HBox acarbs = new HBox();	// add carbs HBox
        acarbs.setPadding(new Insets(0,0, 10,0));
        acarbs.prefWidthProperty().bind(add.widthProperty().multiply(1.0));
        Label acarbsBox = new Label("Carbs");	// label for adding carbs
        acarbsBox.prefWidthProperty().bind(acarbs.widthProperty().multiply(0.25));
        TextField entercarbs = new TextField("");	// text input for adding carbs
        entercarbs.prefWidthProperty().bind(acarbs.widthProperty().multiply(.75));
        acarbs.getChildren().add(acarbsBox);	// add carbs label to carbs HBox
        acarbs.getChildren().add(entercarbs);	// add carbs input to carbs HBox
        add.getChildren().add(acarbs);	// add carbs HBox to the add VBox
        
        HBox afiber = new HBox();	// add fiber HBox
        afiber.setPadding(new Insets(0,0, 10,0));
        afiber.prefWidthProperty().bind(add.widthProperty().multiply(1.0));
        Label afiberBox = new Label("Fiber");	// label for adding fiber
        afiberBox.prefWidthProperty().bind(afiber.widthProperty().multiply(0.25));
        TextField enterfiber = new TextField("");	// text input for adding fiber
        enterfiber.prefWidthProperty().bind(afiber.widthProperty().multiply(.75));
        afiber.getChildren().add(afiberBox);	// add label to the fiber HBox
        afiber.getChildren().add(enterfiber);	// add input to the fiber HBox
        add.getChildren().add(afiber);	// add fiber HBox to the add VBox
        
        HBox aprotein = new HBox();	// add protein HBox
        aprotein.setPadding(new Insets(0,0, 10,0));
        aprotein.prefWidthProperty().bind(add.widthProperty().multiply(1.0));
        Label aproteinBox = new Label("Protein");	// label for protein add box
        aproteinBox.prefWidthProperty().bind(aprotein.widthProperty().multiply(0.25));
        TextField enterprotein = new TextField("");	// text input for loading protein
        enterprotein.prefWidthProperty().bind(aprotein.widthProperty().multiply(.75));
        aprotein.getChildren().add(aproteinBox);	// add protein label to add protein HBox
        aprotein.getChildren().add(enterprotein);	// add input to add protein HBox
        add.getChildren().add(aprotein);	// add protein HBox to add VBox
        
        HBox addFoodContain = new HBox();	// food contain HBox
        addFoodContain.setPadding(new Insets(0,0, 10,0));
        Button addFood = new Button("Add Food");	// add food button
        addFood.prefWidthProperty().bind(sort.widthProperty().multiply(1.0));
        addFoodContain.getChildren().add(addFood);	// add food button to the add food HBox
        add.getChildren().add(addFoodContain);	// add food HBox to the add VBox
        Button importFood = new Button("Import Food");	// import food button for reading in files
        importFood.prefWidthProperty().bind(sort.widthProperty().multiply(1.0));
        add.getChildren().add(importFood);	// add import to add VBox
        
        root.getChildren().add(sort);	// add sort VBox to the main HBox
        root.getChildren().add(analyze);	// add analyze VBox to the main HBox
        root.getChildren().add(add);	// add add VBox to the main HBox

        Scene scene = new Scene(root, 1000, 500);	// set scene with main HBox

        primaryStage.setTitle("ALL THE RIGHT FOOD IN ALL THE RIGHT PLACES");
        primaryStage.setScene(scene);	// set the scene on the primary stage
        primaryStage.show();	// show primary stage


        sortFood.setOnMouseClicked((event) -> {
            FoodList subList = all;
            if(nameBox.isSelected()) {
                subList = new FoodList();
                subList.insertFood(all.getFood(lowname.getText()));
            }
            if(caloriesBox.isSelected()) {
                subList= subList.getFoodRange("cal",Integer.valueOf(lowCalories.getText()),Integer.valueOf(highCalories.getText()));
            }
            if(carbsBox.isSelected()) {
                subList= subList.getFoodRange("carbs",Integer.valueOf(lowcarbs.getText()),Integer.valueOf(highcarbs.getText()));
            }
            if(fiberBox.isSelected()) {
                subList= subList.getFoodRange("fiber",Integer.valueOf(lowfiber.getText()),Integer.valueOf(highfiber.getText()));
            }
            if(fatsBox.isSelected()) {
                subList= subList.getFoodRange("fat",Integer.valueOf(lowfats.getText()),Integer.valueOf(highfats.getText()));
            }
            if(proteinBox.isSelected()) {
                subList= subList.getFoodRange("protein",Integer.valueOf(lowprotein.getText()),Integer.valueOf(highprotein.getText()));
            }

            foodList.setItems(FXCollections.observableArrayList(subList.getNames()));	// displays names from foodList
        });

        addFood.setOnMouseClicked((event) -> {
            Food newFood = new Food(entername.getText(), Integer.valueOf(entercalories.getText()),Integer.valueOf(enterfats.getText()),Integer.valueOf(enterprotein.getText()),Integer.valueOf(entercarbs.getText()), Integer.valueOf(enterfiber.getText()));
            all.insertFood(newFood);
            display.set(all);
            foodList.setItems(FXCollections.observableArrayList (display.get().getNames()));	// displays names from foodList

        });

                    // add to meal event handler
        addToMeal.setOnMouseClicked((event) -> {
        	// copy highlighted items from food list to the meal list
            itemsInMeal.add(foodList.getSelectionModel().getSelectedItem());
            mealList.setItems(itemsInMeal);
        });

        // remove from meal event handler
        removeFromMeal.setOnMouseClicked((event) -> {
        	// remove highlighted items from meal list
            itemsInMeal.remove(mealList.getSelectionModel().getSelectedItem());
            mealList.setItems(itemsInMeal);

        });

        // event handler for importing food
        importFood.setOnMouseClicked((event) -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            fileChooser.showOpenDialog(primaryStage);
            Scanner scanner = null;
            try {
                scanner = new Scanner(new File(fileChooser.getInitialFileName()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            scanner.useDelimiter(",");
            while(scanner.hasNext()){
                System.out.print(scanner.next()+"|");
            }
            scanner.close();
        });

        // event handler for analyzing meal
        analyzeMeal.setOnMouseClicked((event) -> {

        	// create new stage titled analyze
            Stage stageA = new Stage();
            stageA.setTitle("Analyze");

            // create HBox for new stage
            HBox analyzeRoot = new HBox();
            analyzeRoot.setSpacing(10);
            analyzeRoot.setPadding(new Insets(15,20, 10,10));
            analyzeRoot.prefHeightProperty().bind(stageA.heightProperty().multiply(0.9));

            // create VBoxes for new analyze HBox
            VBox mealFood = new VBox();
            VBox foodTotals = new VBox();

            mealFood.prefWidthProperty().bind(analyzeRoot.widthProperty().multiply(0.40));
            foodTotals.prefWidthProperty().bind(analyzeRoot.widthProperty().multiply(0.60));

            // set up meal label
            Label mealLabel = new Label("Meal");
            mealLabel.setPadding(new Insets(0,0, 30,0));
            mealLabel.setFont(new Font("Arial",24));
            mealFood.getChildren().add(mealLabel);

            // create meal list
            ListView<String> finalMeal = new ListView<String>();
            ObservableList<String> mealFinalItems = FXCollections.observableArrayList (
                    "Carrot","Lettuce","Granola","Ice Cream");
            finalMeal.setItems(mealFinalItems);
            finalMeal.prefWidthProperty().bind(mealFood.widthProperty().multiply(1.0));
            mealFood.getChildren().add(finalMeal);

            // nutrition summary set up
            Label nutLabel = new Label("Nutrition Facts");
            nutLabel.setPadding(new Insets(0,0, 30,0));
            nutLabel.setFont(new Font("Arial",24));
            foodTotals.getChildren().add(nutLabel);

            // calories summary set up
            HBox totCal = new HBox();
            totCal.setPadding(new Insets(0,0, 10,0));
            totCal.prefWidthProperty().bind(foodTotals.widthProperty().multiply(1.0));
            Label totalCal = new Label("Calories");
            totalCal.prefWidthProperty().bind(totCal.widthProperty().multiply(0.5));
            Label numCal = new Label("1000");
            numCal.prefWidthProperty().bind(totCal.widthProperty().multiply(.5));
            totCal.getChildren().add(totalCal);
            totCal.getChildren().add(numCal);
            foodTotals.getChildren().add(totCal);

            // fats summary set up
            HBox totFat = new HBox();
            totFat.setPadding(new Insets(0,0, 10,0));
            totFat.prefWidthProperty().bind(foodTotals.widthProperty().multiply(1.0));
            Label totalFat = new Label("Fat");
            totalFat.prefWidthProperty().bind(totFat.widthProperty().multiply(0.5));
            Label numFat = new Label("10g");
            numFat.prefWidthProperty().bind(totFat.widthProperty().multiply(.5));
            totFat.getChildren().add(totalFat);
            totFat.getChildren().add(numFat);
            foodTotals.getChildren().add(totFat);

            // carbs summary set up
            HBox totCarb = new HBox();
            totCarb.setPadding(new Insets(0,0, 10,0));
            totCarb.prefWidthProperty().bind(foodTotals.widthProperty().multiply(1.0));
            Label totalCarb = new Label("Carbs");
            totalCarb.prefWidthProperty().bind(totCarb.widthProperty().multiply(0.5));
            Label numCarb = new Label("100g");
            numCarb.prefWidthProperty().bind(totCarb.widthProperty().multiply(.5));
            totCarb.getChildren().add(totalCarb);
            totCarb.getChildren().add(numCarb);
            foodTotals.getChildren().add(totCarb);

            // fiber summary set up
            HBox totFib = new HBox();
            totFib.setPadding(new Insets(0,0, 10,0));
            totFib.prefWidthProperty().bind(foodTotals.widthProperty().multiply(1.0));
            Label totalFib = new Label("Fiber");
            totalFib.prefWidthProperty().bind(totFib.widthProperty().multiply(0.5));
            Label numFib = new Label("20g");
            numFib.prefWidthProperty().bind(totFib.widthProperty().multiply(.5));
            totFib.getChildren().add(totalFib);
            totFib.getChildren().add(numFib);
            foodTotals.getChildren().add(totFib);

            // protein summary set up
            HBox totProt = new HBox();
            totProt.setPadding(new Insets(0,0, 10,0));
            totProt.prefWidthProperty().bind(foodTotals.widthProperty().multiply(1.0));
            Label totalProt = new Label("Protein");
            totalProt.prefWidthProperty().bind(totProt.widthProperty().multiply(0.5));
            Label numProt = new Label("200g");
            numProt.prefWidthProperty().bind(totProt.widthProperty().multiply(.5));
            totProt.getChildren().add(totalProt);
            totProt.getChildren().add(numProt);
            foodTotals.getChildren().add(totProt);

            // set up new scene
            analyzeRoot.getChildren().add(mealFood);
            analyzeRoot.getChildren().add(foodTotals);
            Scene analyzeScene = new Scene(analyzeRoot, 600, 400);

            stageA.setScene(analyzeScene);
            stageA.show();
        });
    }

    /**
     * Launches the GUI
     * 
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}