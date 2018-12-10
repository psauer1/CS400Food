package application;

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


import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;


/**
 * Main class creates and pops up the Graphical User Interface for the meal
 * analysis.
 * 
 * @author Peter Sauer, Riley Ley
 */
public class Main extends Application {

	/**
	 * Starts the GUI and sets up the stage, scenes, buttons, and other internal
	 * features.
	 * 
	 * @param primaryStage
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {

		FoodData all = new FoodData(); // creating background data for GUI
		AtomicReference<FoodData> display = new AtomicReference<>(new FoodData()); // dipslays food options to user
		AtomicReference<FoodData> menu = new AtomicReference<>(new FoodData()); // displays meal to user

		/*
		 * GUI Set up
		 * 
		 * Creates a graphical user interface with three main panels set in s horizontal
		 * frame
		 */


		/*
		 * Main horizontal frame
		 */
		HBox root = new HBox(); // creates root HBox
		root.setSpacing(10);
		root.setPadding(new Insets(15, 20, 10, 10));
		root.prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.9)); // sets the height parameters of

		VBox options3 = new VBox();																				// the HBox
		HBox options = new HBox(); // creates VBox for the sorting of the program
		options.prefWidthProperty().bind(options3.widthProperty().multiply(1));
		HBox options2 = new HBox(); // creates VBox for the sorting of the program
		options2.prefWidthProperty().bind(options3.widthProperty().multiply(1));
		options3.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.33));
		options3.getChildren().add(options);
		options3.getChildren().add(options2);

		/*
		 * Sort food items vertical section
		 */
		VBox sort = new VBox();	// creates VBox for sorting food items from food list
		sort.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.33));

		HBox name = new HBox(); // HBox for sorting by name
		name.setPadding(new Insets(0, 0, 10, 0));
		name.prefWidthProperty().bind(sort.widthProperty().multiply(1.0));
		CheckBox nameBox = new CheckBox("Name"); // creates check box for sorting by name
		nameBox.prefWidthProperty().bind(name.widthProperty().multiply(0.5));
		TextField lowname = new TextField("");
		lowname.prefWidthProperty().bind(name.widthProperty().multiply(.5));
		name.getChildren().add(nameBox); // adds check box to the HBox
		name.getChildren().add(lowname); // adds text field for name to HBox
		sort.getChildren().add(name); // adds the HBox to the sort VBox
		sort.prefWidthProperty().bind(options.widthProperty().multiply(1));

		HBox calories = new HBox(); // HBox for sorting by calories
		calories.setPadding(new Insets(0, 0, 10, 0));
		calories.prefWidthProperty().bind(sort.widthProperty().multiply(1.0));
		CheckBox caloriesBox = new CheckBox("Calories"); // creates check box for sorting by calories
		caloriesBox.prefWidthProperty().bind(calories.widthProperty().multiply(0.5));
		TextField lowCalories = new TextField("Low"); // creates text input for low end of calories
		lowCalories.prefWidthProperty().bind(calories.widthProperty().multiply(.250));
		TextField highCalories = new TextField("High"); // creates text input for high end of calories
		highCalories.prefWidthProperty().bind(calories.widthProperty().multiply(.250));
		calories.getChildren().add(caloriesBox); // adds check box to the HBox
		calories.getChildren().add(lowCalories); // adds low end text field to HBox
		calories.getChildren().add(highCalories); // adds high end text field to HBox
		sort.getChildren().add(calories); // adds the HBox to the sort VBox
		sort.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.25));

		HBox fats = new HBox(); // HBox for sorting by fats
		fats.setPadding(new Insets(0, 0, 10, 0));
		fats.prefWidthProperty().bind(sort.widthProperty().multiply(1.0));
		CheckBox fatsBox = new CheckBox("Fats"); // creates check box for sorting by fats
		fatsBox.prefWidthProperty().bind(fats.widthProperty().multiply(0.5));
		TextField lowfats = new TextField("Low"); // creates text input for low end of fats
		lowfats.prefWidthProperty().bind(fats.widthProperty().multiply(.250));
		TextField highfats = new TextField("High"); // creates text input for high end of fats
		highfats.prefWidthProperty().bind(fats.widthProperty().multiply(.250));
		fats.getChildren().add(fatsBox); // adds check box to the HBox
		fats.getChildren().add(lowfats); // adds low end text field to HBox
		fats.getChildren().add(highfats); // adds high end text field to HBox
		sort.getChildren().add(fats); // adds the HBox to the sort VBox
		sort.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.25));

		HBox carbs = new HBox(); // HBox for sorting by carbs
		carbs.setPadding(new Insets(0, 0, 10, 0));
		carbs.prefWidthProperty().bind(sort.widthProperty().multiply(1.0));
		CheckBox carbsBox = new CheckBox("Carbs"); // creates check box for sorting by carbs
		carbsBox.prefWidthProperty().bind(carbs.widthProperty().multiply(0.5));
		TextField lowcarbs = new TextField("Low"); // creates text input for low end of fats
		lowcarbs.prefWidthProperty().bind(carbs.widthProperty().multiply(.250));
		TextField highcarbs = new TextField("High"); // creates text input for high end of fats
		highcarbs.prefWidthProperty().bind(carbs.widthProperty().multiply(.250));
		carbs.getChildren().add(carbsBox); // adds check box to the HBox
		carbs.getChildren().add(lowcarbs); // adds low end text field to HBox
		carbs.getChildren().add(highcarbs); // adds high end text field to HBox
		sort.getChildren().add(carbs); // adds the HBox to the sort VBox
		sort.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.25));

		HBox fiber = new HBox(); // HBox for sorting by carbs
		fiber.setPadding(new Insets(0, 0, 10, 0));
		fiber.prefWidthProperty().bind(sort.widthProperty().multiply(1.0));
		CheckBox fiberBox = new CheckBox("Fiber"); // creates check box for searching by fiber
		fiberBox.prefWidthProperty().bind(fiber.widthProperty().multiply(0.5));
		TextField lowfiber = new TextField("Low"); // creates text input for low end of fats
		lowfiber.prefWidthProperty().bind(fiber.widthProperty().multiply(.250));
		TextField highfiber = new TextField("High"); // creates text input for high end of fats
		highfiber.prefWidthProperty().bind(fiber.widthProperty().multiply(.250));
		fiber.getChildren().add(fiberBox); // adds check box to the HBox
		fiber.getChildren().add(lowfiber); // adds low end text field to HBox
		fiber.getChildren().add(highfiber); // adds high end text field to HBox
		sort.getChildren().add(fiber); // adds the HBox to the sort VBox
		sort.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.25));

		HBox protein = new HBox(); // HBox for sorting by protein
		protein.setPadding(new Insets(0, 0, 10, 0));
		protein.prefWidthProperty().bind(sort.widthProperty().multiply(1.0));
		CheckBox proteinBox = new CheckBox("Protein"); // creates check box for sorting by protein
		proteinBox.prefWidthProperty().bind(protein.widthProperty().multiply(0.5));
		TextField lowprotein = new TextField("Low"); // creates text input for low end of protein
		lowprotein.prefWidthProperty().bind(protein.widthProperty().multiply(.250));
		TextField highprotein = new TextField("High"); // creates text input for the high end of protein
		highprotein.prefWidthProperty().bind(protein.widthProperty().multiply(.250));
		protein.getChildren().add(proteinBox); // adds check box to the HBox
		protein.getChildren().add(lowprotein); // adds low end text field to HBox
		protein.getChildren().add(highprotein); // adds high end text field to HBox
		sort.getChildren().add(protein); // adds the HBox to the the sort VBox

		Button sortFood = new Button("Sort Food"); // creates new button to Sort FoodItem
		sortFood.prefWidthProperty().bind(sort.widthProperty().multiply(1.0));
		sort.getChildren().add(sortFood); // adds button to the sort VBox

		/*
		 * Meal analysis vertical section
		 */
		VBox analyze = new VBox(); // creates VBox for the main function of the program
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

		ListView<String> foodList = new ListView<String>(); // creates list for the food
		ObservableList<String> items = FXCollections.observableArrayList(all.getNames()); // displays names from
																							// foodList
		foodList.setItems(items); // set items into the food list
		foodList.prefWidthProperty().bind(foodItems.widthProperty().multiply(1.0));

		ListView<String> mealList = new ListView<String>(); // creates list for the meal
		ObservableList<String> itemsInMeal = FXCollections.observableArrayList(menu.get().getNames()); // displays names
																										// from menu
																										// foodlist
		mealList.setItems(itemsInMeal); // adds meal list to the list view for meal
		mealList.prefWidthProperty().bind(mealItems.widthProperty().multiply(1.0));
		mealItems.getChildren().add(mealList); // adds meal list to the meal items VBox

		foodItems.getChildren().add(foodList); // adds food list to the food items VBox
		buttons.setPadding(new Insets(100, 10, 10, 10));

		HBox addCon = new HBox();
		addCon.setPadding(new Insets(0, 0, 10, 0));
		Button addToMeal = new Button("Add"); // creates button for adding items from food to meal list
		addToMeal.prefWidthProperty().bind(buttons.widthProperty().multiply(1.0));

		HBox removeCon = new HBox();
		removeCon.setPadding(new Insets(0, 0, 10, 0));
		Button removeFromMeal = new Button("Remove"); // creates button for removing item from meal list
		removeFromMeal.prefWidthProperty().bind(buttons.widthProperty().multiply(1.0));
		Button clear = new Button("Clear"); // creates button for removing item from meal list
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

		Button analyzeMeal = new Button("Analyze Meal"); // creates button to analyze the meal
		analyzeMeal.prefWidthProperty().bind(analyze.widthProperty().multiply(1.0));
		analyze.getChildren().add(subAnalyze); // adds the sub-analyze HBox to the main analyze HBox
		analyze.getChildren().add(analyzeMeal); // adds the analyze button HBox to the main analyze HBox

		/*
		 * Add food items vertical section
		 */
		VBox add = new VBox(); // creates VBox for adding food items to the program
		add.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.25));

		HBox aname = new HBox(); // add name HBox
		aname.setPadding(new Insets(0, 0, 10, 0));
		aname.prefWidthProperty().bind(add.widthProperty().multiply(1.0));
		Label anameBox = new Label("Name"); // label for adding name of food
		anameBox.prefWidthProperty().bind(aname.widthProperty().multiply(0.25));
		TextField entername = new TextField(""); // text input for name of food
		entername.prefWidthProperty().bind(aname.widthProperty().multiply(.75));
		aname.getChildren().add(anameBox); // add the name label to the add name HBox
		aname.getChildren().add(entername); // add the text input to the add name HBox
		add.getChildren().add(aname); // add the add name HBox to the add VBox

		HBox acalories = new HBox(); // add calories HBox
		acalories.setPadding(new Insets(0, 0, 10, 0));
		acalories.prefWidthProperty().bind(add.widthProperty().multiply(1.0));
		Label acaloriesBox = new Label("Calories"); // label for adding calories of food
		acaloriesBox.prefWidthProperty().bind(acalories.widthProperty().multiply(0.25));
		TextField entercalories = new TextField(""); // text input for calories of food
		entercalories.prefWidthProperty().bind(acalories.widthProperty().multiply(.75));
		acalories.getChildren().add(acaloriesBox); // add calories label to the calories HBox
		acalories.getChildren().add(entercalories); // add calories input to the calories HBox
		add.getChildren().add(acalories); // add the calories HBox to the add VBox

		HBox afats = new HBox(); // add fats HBox
		afats.setPadding(new Insets(0, 0, 10, 0));
		afats.prefWidthProperty().bind(add.widthProperty().multiply(1.0));
		Label afatsBox = new Label("Fats"); // label for fats HBox
		afatsBox.prefWidthProperty().bind(afats.widthProperty().multiply(0.25));
		TextField enterfats = new TextField(""); // text input for fats
		enterfats.prefWidthProperty().bind(afats.widthProperty().multiply(.75));
		afats.getChildren().add(afatsBox); // add fats label to add fats HBox
		afats.getChildren().add(enterfats); // add fats input to add fats HBox
		add.getChildren().add(afats); // add fats HBox to the add VBox

		HBox acarbs = new HBox(); // add carbs HBox
		acarbs.setPadding(new Insets(0, 0, 10, 0));
		acarbs.prefWidthProperty().bind(add.widthProperty().multiply(1.0));
		Label acarbsBox = new Label("Carbs"); // label for adding carbs
		acarbsBox.prefWidthProperty().bind(acarbs.widthProperty().multiply(0.25));
		TextField entercarbs = new TextField(""); // text input for adding carbs
		entercarbs.prefWidthProperty().bind(acarbs.widthProperty().multiply(.75));
		acarbs.getChildren().add(acarbsBox); // add carbs label to carbs HBox
		acarbs.getChildren().add(entercarbs); // add carbs input to carbs HBox
		add.getChildren().add(acarbs); // add carbs HBox to the add VBox

		HBox afiber = new HBox(); // add fiber HBox
		afiber.setPadding(new Insets(0, 0, 10, 0));
		afiber.prefWidthProperty().bind(add.widthProperty().multiply(1.0));
		Label afiberBox = new Label("Fiber"); // label for adding fiber
		afiberBox.prefWidthProperty().bind(afiber.widthProperty().multiply(0.25));
		TextField enterfiber = new TextField(""); // text input for adding fiber
		enterfiber.prefWidthProperty().bind(afiber.widthProperty().multiply(.75));
		afiber.getChildren().add(afiberBox); // add label to the fiber HBox
		afiber.getChildren().add(enterfiber); // add input to the fiber HBox
		add.getChildren().add(afiber); // add fiber HBox to the add VBox

		HBox aprotein = new HBox(); // add protein HBox
		aprotein.setPadding(new Insets(0, 0, 10, 0));
		aprotein.prefWidthProperty().bind(add.widthProperty().multiply(1.0));
		Label aproteinBox = new Label("Protein"); // label for protein add box
		aproteinBox.prefWidthProperty().bind(aprotein.widthProperty().multiply(0.25));
		TextField enterprotein = new TextField(""); // text input for loading protein
		enterprotein.prefWidthProperty().bind(aprotein.widthProperty().multiply(.75));
		aprotein.getChildren().add(aproteinBox); // add protein label to add protein HBox
		aprotein.getChildren().add(enterprotein); // add input to add protein HBox
		add.getChildren().add(aprotein); // add protein HBox to add VBox

		HBox addFoodContain = new HBox(); // food contain HBox
		addFoodContain.setPadding(new Insets(0, 0, 10, 0));
		Button addFood = new Button("Add FoodItem"); // add food button
		addFood.prefWidthProperty().bind(sort.widthProperty().multiply(1.0));
		addFoodContain.getChildren().add(addFood); // add food button to the add food HBox
		add.getChildren().add(addFoodContain); // add food HBox to the add VBox
		HBox exportFoodContain = new HBox(); // food contain HBox
		exportFoodContain.setPadding(new Insets(0, 0, 10, 0));
		Button exportFood = new Button("Export Food"); // add food button
		exportFood.prefWidthProperty().bind(sort.widthProperty().multiply(1.0));
		exportFoodContain.getChildren().add(exportFood); // add food button to the add food HBox
		add.getChildren().add(exportFoodContain); // add food HBox to the add VBox
		Button importFood = new Button("Import Food"); // import food button for reading in files
		importFood.prefWidthProperty().bind(sort.widthProperty().multiply(1.0));
		add.getChildren().add(importFood); // add import to add VBox

		TitledPane sortPane = new TitledPane("Sort Food Items",sort);	// creates drop down pane for sorting food
		TitledPane addPane = new TitledPane("Add Food Items",add);	// creates drop down pane for adding food
		sortPane.getStyleClass().add("danger");	// styles the pane
		addPane.getStyleClass().add("success");	// styles the pane
		sortPane.setCollapsible(true);	// creates collapsible aspect of the pane
		Accordion accordion = new Accordion();	// creates accordion to contain the drop down panes
		accordion.getPanes().addAll(sortPane,addPane);	// adds both drop down panes
		options3.getChildren().add(accordion);	// adds accordion to the HBox

		TitledPane analyzePane = new TitledPane("Analyze",analyze);	// creates pane for analyzing meal
		analyzePane.getStyleClass().add("info");	// add style to the pane
		analyzePane.setCollapsible(false);	// does not allow for collapsibility
		root.getChildren().add(options3); // add sort VBox to the main HBox
		root.getChildren().add(analyzePane); // add analyze VBox to the main HBox

		Scene scene = new Scene(root, 800, 500); // set scene with main HBox
		scene.getStylesheets().add("fx.css");	// apply style sheet
		primaryStage.setTitle("ALL THE RIGHT FOOD IN ALL THE RIGHT PLACES");	// set title for program
		primaryStage.setScene(scene); // set the scene on the primary stage
		primaryStage.show(); // show primary stage

		
		/*
		 * Event handlers
		 * 
		 * Responds to the user interactions with the GUI
		 */

		
		/*
		 * Generic error response window
		 * 
		 * Displays whenever the user interacts with program in an incorrect way
		 */
		Stage errorStage = new Stage();	// creates new stage
		errorStage.setTitle("Error");	// names the error stage
		
		VBox errorRoot = new VBox();	// creates root HBox for the error
		errorRoot.setPadding(new Insets(0, 0, 10, 0));	// sets padding to root
		errorRoot.prefWidthProperty().bind(errorStage.widthProperty().multiply(1.0));	// sets size of root
		
		Label errorLabel01 = new Label("   Invalid input");	// error message
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

		/*
		 * Sort button event handler
		 * 
		 * Applies the criteria specified for each field, displays the error message if the user inputted invalid arguments
		 */
		sortFood.setOnMouseClicked((event) -> {
			// TODO: Add error response messages, error handlers to avoid crashes
			FoodData subList = all;
			ArrayList<String> searchList = new ArrayList<String>();

			if (nameBox.isSelected()) { // if name box is checked
				try {
					subList = new FoodData(); // create a new food list
					for(FoodItem nameFiltered:all.filterByName(lowname.getText())) { // if the search item contains
						subList.addFoodItem(nameFiltered);
					}
				} catch (Exception e) {	// error thrown from name input
					errorStage.show();
				}
			}

			if (caloriesBox.isSelected()) { // if calories box is checked apply range criterion
				try {
					// Input safety checks
					double low = Double.valueOf(lowCalories.getText());
					double high = Double.valueOf(highCalories.getText());

					if (low < 0)
						throw new IllegalArgumentException();
					if (low > high)
						throw new IllegalArgumentException();
					if (high < 0)
						throw new IllegalArgumentException();

					String lowString = String.valueOf(lowCalories.getText());
					String highString = String.valueOf(highCalories.getText());

					searchList.add("calories >= "+lowString);
					searchList.add("calories <= "+highString);
				} catch (Exception e) {	// error thrown from number input
					errorStage.show();
				}
			}
			if (carbsBox.isSelected()) { // if carbs box is checked apply range criterion
				try {
					// Input safety checks
					double low = Double.valueOf(lowcarbs.getText());
					double high = Double.valueOf(highcarbs.getText());

					if (low < 0)
						throw new IllegalArgumentException();
					if (low > high)
						throw new IllegalArgumentException();
					if (high < 0)
						throw new IllegalArgumentException();

					String lowString = String.valueOf(lowcarbs.getText());
					String highString = String.valueOf(highcarbs.getText());

					searchList.add("carbs >= "+lowString);
					searchList.add("carbs <= "+highString);
				} catch (Exception e) {	// error thrown from number input
					errorStage.show();
				}
			}
			if (fiberBox.isSelected()) { // if fiber box is checked apply range criterion
				try {
					// Input safety checks
					double low = Double.valueOf(lowfiber.getText());
					double high = Double.valueOf(highfiber.getText());

					if (low < 0)
						throw new IllegalArgumentException();
					if (low > high)
						throw new IllegalArgumentException();
					if (high < 0)
						throw new IllegalArgumentException();

					String lowString = String.valueOf(lowfiber.getText());
					String highString = String.valueOf(highfiber.getText());

					searchList.add("fiber >= "+lowString);
					searchList.add("fiber <= "+highString);
				} catch (Exception e) {	// error thrown from number input
					errorStage.show();
				}
			}
			if (fatsBox.isSelected()) { // if fats box is checked apply range criterion
				try {
					// Input safety checks
					double low = Double.valueOf(lowfats.getText());
					double high = Double.valueOf(highfats.getText());

					if (low < 0)
						throw new IllegalArgumentException();
					if (low > high)
						throw new IllegalArgumentException();
					if (high < 0)
						throw new IllegalArgumentException();

					String lowString = String.valueOf(lowfats.getText());
					String highString = String.valueOf(highfats.getText());

					searchList.add("fats >= "+lowString);
					searchList.add("fats <= "+highString);
				} catch (Exception e) {	// error thrown from number input
					errorStage.show();
				}
			}
			if (proteinBox.isSelected()) { // if the protein box is checked apply range criterion
				try {
					// Input safety checks
					double low = Double.valueOf(lowprotein.getText());
					double high = Double.valueOf(highprotein.getText());

					if (low < 0)
						throw new IllegalArgumentException();
					if (low > high)
						throw new IllegalArgumentException();
					if (high < 0)
						throw new IllegalArgumentException();

					String lowString = String.valueOf(lowprotein.getText());
					String highString = String.valueOf(highprotein.getText());

					searchList.add("protein >= "+lowString);
					searchList.add("protein <= "+highString);
				} catch (Exception e) {	// error thrown from number input
					errorStage.show();
				}
			}
			ArrayList<String> nameList = new ArrayList<>();
			for(FoodItem temp : subList.filterByNutrients(searchList)){
				nameList.add(temp.getName());
			}
			foodList.setItems(FXCollections.observableArrayList(nameList)); // displays names from foodList
		});

		/*
		 * Event handler for user adding an individual food
		 * 
		 * Reads in the user inputs and creates a food item with the specified arguments, displays an error message if the user inputs an illegal argument
		 */
		addFood.setOnMouseClicked((event) -> {
			try {
				// valid inputs check for user input
				if (entername.getText().length() == 0)
					throw new IllegalArgumentException();
				if (Float.valueOf(entercalories.getText()) < 0)
					throw new IllegalArgumentException();
				if (Float.valueOf(enterfats.getText()) < 0)
					throw new IllegalArgumentException();
				if (Float.valueOf(entercarbs.getText()) < 0)
					throw new IllegalArgumentException();
				if (Float.valueOf(enterfiber.getText()) < 0)
					throw new IllegalArgumentException();
				if (Float.valueOf(enterprotein.getText()) < 0)
					throw new IllegalArgumentException();

				// Generate a unique key for sorting the user inputed food
				KeyGenerator keyGen = KeyGenerator.getInstance("AES");
				keyGen.init(256); // for example
				String secretKey = keyGen.generateKey().toString();

				// Create a new food item with inputs
				FoodItem newFood = new FoodItem(secretKey,entername.getText(), Double.valueOf(entercalories.getText()),
						Double.valueOf(enterfats.getText()), Double.valueOf(enterprotein.getText()),
						Double.valueOf(entercarbs.getText()), Double.valueOf(enterfiber.getText()));
				all.addFoodItem(newFood);
				display.set(all);
				foodList.setItems(FXCollections.observableArrayList(display.get().getNames())); // displays names from
																								// foodList
			} catch (Exception e) {	// error message for invalid inputs
				errorStage.show();
			}
		});

		/*
		 * Add to meal button event handler
		 * 
		 * Moves the highlighted food items within the food list to the meal list
		 */
		addToMeal.setOnMouseClicked((event) -> {
			FoodItem addItem = all.getFood(foodList.getSelectionModel().getSelectedItem());	// copy highlighted items from food list to the meal list
			menu.get().addFoodItem(addItem);
			mealList.setItems(FXCollections.observableArrayList(menu.get().getNames())); // displays names from foodList
		});

		/*
		 * Event handler for clear button
		 * 
		 * Removes all of the items from the meal list and displays an emtpy list
		 */
		clear.setOnMouseClicked((event) -> {
			menu.set(new FoodData()); // create an empty list
			mealList.setItems(FXCollections.observableArrayList(menu.get().getNames())); // displays names from foodList
		});

		/*
		 * Remove button event handler
		 * 
		 * Removes highlighted items from the meal list, does not affect unselected items
		 */
		removeFromMeal.setOnMouseClicked((event) -> {
			menu.get().removeFoodItem(mealList.getSelectionModel().getSelectedItem());	// remove highlighted items from meal list
			mealList.setItems(FXCollections.observableArrayList(menu.get().getNames())); // displays names from foodList
		});

		/*
		 * Event handler for import food button
		 * 
		 * Displays a file chooser for user to select a .csv file to import from
		 */
		importFood.setOnMouseClicked((event) -> {
			FileChooser fileChooser = new FileChooser(); // generate file explorer for user
			fileChooser.setTitle("Open Resource File");	// title file explorer
			fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv"));	// only accepts .csv files
			File chosen = fileChooser.showOpenDialog(primaryStage);	// creates file from chosen file
			all.loadFoodItems(chosen.getAbsolutePath());	// Loads all of the items from file
			foodList.setItems(FXCollections.observableArrayList(all.getNames()));	// display imported items in the food list
		});

		/*
		 * Event handler for export food button
		 * 
		 * Takes all of the items in the food list and prints them into a .csv with the same format as input files
		 */
		exportFood.setOnMouseClicked((event) -> {
			FileChooser fileChooser = new FileChooser(); // choose location to save file
			fileChooser.setTitle("Choose location to export file");	// title of file chooser
			File path = fileChooser.showSaveDialog(primaryStage);	// create file for the given path
			all.saveFoodItems(path.getAbsolutePath());	// save items with given file path
		});

		/*
		 * Event handler for analyze button
		 * 
		 * Sums all of the food items nutritional values from the meal list and displays a new window with a summary
		 */
		analyzeMeal.setOnMouseClicked((event) -> {
			Double[] data = {(double) 0, (double) 0, (double) 0, (double) 0, (double) 0}; // array for
																										// storing
																										// nutrition
																										// totals
			for (FoodItem foodItem : menu.get().getAllFoodItems()) {	// loop for summing all of the nutrition totals in meal list
				data[0] += foodItem.getCal();
				data[1] += foodItem.getCarb();
				data[2] += foodItem.getProtein();
				data[3] += foodItem.getFat();
				data[4] += foodItem.getFiber();
			}

			// create new stage titled analyze
			Stage stageA = new Stage();
			stageA.setTitle("Analyze");

			// create HBox for new stage
			HBox analyzeRoot = new HBox();
			analyzeRoot.setSpacing(10);
			analyzeRoot.setPadding(new Insets(15, 20, 10, 10));
			analyzeRoot.prefHeightProperty().bind(stageA.heightProperty().multiply(0.9));

			// create VBoxes for new analyze HBox
			VBox mealFood = new VBox();
			VBox foodTotals = new VBox();

			mealFood.prefWidthProperty().bind(analyzeRoot.widthProperty().multiply(0.40));
			foodTotals.prefWidthProperty().bind(analyzeRoot.widthProperty().multiply(0.60));

			// set up meal label
			Label mealLabel = new Label("Meal");
			mealLabel.setPadding(new Insets(0, 0, 30, 0));
			mealLabel.setFont(new Font("Arial", 24));
			mealFood.getChildren().add(mealLabel);

			// create meal list
			ListView<String> finalMeal = new ListView<String>();
			finalMeal.setItems(FXCollections.observableArrayList(menu.get().getNames())); // displays names from
																							// foodList
			finalMeal.prefWidthProperty().bind(mealFood.widthProperty().multiply(1.0));
			mealFood.getChildren().add(finalMeal);

			// nutrition summary set up
			Label nutLabel = new Label("Nutrition Facts");
			nutLabel.setPadding(new Insets(0, 0, 30, 0));
			nutLabel.setFont(new Font("Arial", 24));
			foodTotals.getChildren().add(nutLabel);

			// calories summary set up
			HBox totCal = new HBox();
			totCal.setPadding(new Insets(0, 0, 10, 0));
			totCal.prefWidthProperty().bind(foodTotals.widthProperty().multiply(1.0));
			Label totalCal = new Label("Calories");
			totalCal.prefWidthProperty().bind(totCal.widthProperty().multiply(0.5));
			Label numCal = new Label(data[0].toString());
			numCal.prefWidthProperty().bind(totCal.widthProperty().multiply(.5));
			totCal.getChildren().add(totalCal);
			totCal.getChildren().add(numCal);
			foodTotals.getChildren().add(totCal);

			// fats summary set up
			HBox totFat = new HBox();
			totFat.setPadding(new Insets(0, 0, 10, 0));
			totFat.prefWidthProperty().bind(foodTotals.widthProperty().multiply(1.0));
			Label totalFat = new Label("Fat");
			totalFat.prefWidthProperty().bind(totFat.widthProperty().multiply(0.5));
			Label numFat = new Label(data[3].toString());
			numFat.prefWidthProperty().bind(totFat.widthProperty().multiply(.5));
			totFat.getChildren().add(totalFat);
			totFat.getChildren().add(numFat);
			foodTotals.getChildren().add(totFat);

			// carbs summary set up
			HBox totCarb = new HBox();
			totCarb.setPadding(new Insets(0, 0, 10, 0));
			totCarb.prefWidthProperty().bind(foodTotals.widthProperty().multiply(1.0));
			Label totalCarb = new Label("Carbs");
			totalCarb.prefWidthProperty().bind(totCarb.widthProperty().multiply(0.5));
			Label numCarb = new Label(data[1].toString());
			numCarb.prefWidthProperty().bind(totCarb.widthProperty().multiply(.5));
			totCarb.getChildren().add(totalCarb);
			totCarb.getChildren().add(numCarb);
			foodTotals.getChildren().add(totCarb);

			// fiber summary set up
			HBox totFib = new HBox();
			totFib.setPadding(new Insets(0, 0, 10, 0));
			totFib.prefWidthProperty().bind(foodTotals.widthProperty().multiply(1.0));
			Label totalFib = new Label("Fiber");
			totalFib.prefWidthProperty().bind(totFib.widthProperty().multiply(0.5));
			Label numFib = new Label(data[4].toString());
			numFib.prefWidthProperty().bind(totFib.widthProperty().multiply(.5));
			totFib.getChildren().add(totalFib);
			totFib.getChildren().add(numFib);
			foodTotals.getChildren().add(totFib);

			// protein summary set up
			HBox totProt = new HBox();
			totProt.setPadding(new Insets(0, 0, 10, 0));
			totProt.prefWidthProperty().bind(foodTotals.widthProperty().multiply(1.0));
			Label totalProt = new Label("Protein");
			totalProt.prefWidthProperty().bind(totProt.widthProperty().multiply(0.5));
			Label numProt = new Label(data[2].toString());
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
