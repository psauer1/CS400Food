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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
		FoodList all = new FoodList(); // creating background data for GUI
		AtomicReference<FoodList> display = new AtomicReference<>(new FoodList()); // dipslays food options to user
		AtomicReference<FoodList> menu = new AtomicReference<>(new FoodList()); // displays meal to user

		/*
		 * GUI Set up
		 * 
		 * Creates a graphical user interface with three main panels set in s horizontal
		 * frame
		 */

		// Main horizontal frame
		HBox root = new HBox(); // creates root HBox
		root.setSpacing(10);
		root.setPadding(new Insets(15, 20, 10, 10));
		root.prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.9)); // sets the height parameters of
																						// the HBox
		VBox sort = new VBox(); // creates VBox for the sorting of the program
		VBox analyze = new VBox(); // creates VBox for the main function of the program
		VBox add = new VBox(); // creates VBox for adding food items to the program

		// Sort food items vertical section
		Label sortLabel = new Label("Sort Foods");
		sortLabel.setPadding(new Insets(0, 0, 30, 0));
		sortLabel.setFont(new Font("Arial", 24));
		sort.getChildren().add(sortLabel); // add label to the sort VBox
		sort.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.25));

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
		sort.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.25));

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

		Button sortFood = new Button("Sort Food"); // creates new button to Sort Food
		sortFood.prefWidthProperty().bind(sort.widthProperty().multiply(1.0));
		sort.getChildren().add(sortFood); // adds button to the sort VBox

		// Meal analysis vertical section
		Label analyzeLabel = new Label("Analyze Meal"); // creates button for Anaylzing the meal
		analyzeLabel.setPadding(new Insets(0, 0, 30, 0));
		analyzeLabel.setFont(new Font("Arial", 24));
		analyze.getChildren().add(analyzeLabel); // adds the label to the analyze VBox
		analyze.prefWidthProperty().bind(root.widthProperty().multiply(0.50));

		HBox subAnalyze = new HBox(); // creates HBox within the HBox for analyzing the meal
		subAnalyze.setPadding(new Insets(0, 0, 10, 0));

		VBox foodItems = new VBox(); // creates VBox for all of the food items
		VBox mealItems = new VBox(); // creates VBox for the current meal to be analyzed
		VBox buttons = new VBox(); // creates VBox for buttons to add and remove from the meal list
		foodItems.prefWidthProperty().bind(subAnalyze.widthProperty().multiply(0.40));
		mealItems.prefWidthProperty().bind(subAnalyze.widthProperty().multiply(0.40));
		buttons.prefWidthProperty().bind(subAnalyze.widthProperty().multiply(0.20));

		// Food list label
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

		// Add food items vertical section
		Label addLabel = new Label("Add Food"); // new label for add VBox
		addLabel.setPadding(new Insets(0, 0, 30, 0));
		addLabel.setFont(new Font("Arial", 24));
		add.getChildren().add(addLabel); // add the label to the add VBox
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
		Button addFood = new Button("Add Food"); // add food button
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

		root.getChildren().add(sort); // add sort VBox to the main HBox
		root.getChildren().add(analyze); // add analyze VBox to the main HBox
		root.getChildren().add(add); // add add VBox to the main HBox

		Scene scene = new Scene(root, 1000, 500); // set scene with main HBox

		primaryStage.setTitle("ALL THE RIGHT FOOD IN ALL THE RIGHT PLACES");
		primaryStage.setScene(scene); // set the scene on the primary stage
		primaryStage.show(); // show primary stage

		/*
		 * Event handlers
		 * 
		 * Responds to the user interactions with the GUI
		 */

		// Generic error response
		Stage errorStage = new Stage();
		errorStage.setTitle("Error");
		HBox errorRoot = new HBox();
		errorRoot.setPadding(new Insets(0, 0, 10, 0));
		errorRoot.prefWidthProperty().bind(errorStage.widthProperty().multiply(1.0));
		Label errorLabel = new Label(
				"     Invalid input\n   Valid inputs include:\n   > Zero or positive values\n   > floats or integers");
		errorLabel.setPadding(new Insets(0, 0, 30, 0));
		errorLabel.setFont(new Font("Arial", 18));
		errorRoot.getChildren().add(errorLabel);
		Scene errorScene = new Scene(errorRoot, 300, 200);
		errorStage.setScene(errorScene);

		// sort food query button
		sortFood.setOnMouseClicked((event) -> {
			// TODO: Add error response messages, error handlers to avoid crashes
			FoodList subList = all;

			if (nameBox.isSelected()) { // if name box is checked
				try {
					if (all.getFood(lowname.getText()) == null)
						throw new IllegalArgumentException();

					subList = new FoodList(); // create a new food list
					subList.insertFood(all.getFood(lowname.getText())); // if the search item contains
				} catch (Exception e) {
					errorStage.show();
				}
			}
			
			if (caloriesBox.isSelected()) { // if calories box is checked apply range criterion
				try {
					float low = Float.valueOf(lowCalories.getText());
					float high = Float.valueOf(highCalories.getText());
					
					if (low < 0)
						throw new IllegalArgumentException();
					if (low > high)
						throw new IllegalArgumentException();
					if (high < 0)
						throw new IllegalArgumentException();
					
					subList = subList.getFoodRange("cal", low, high);
				} catch (Exception e) {
					errorStage.show();
				}
			}
			if (carbsBox.isSelected()) { // if carbs box is checked apply range criterion
				try {
					float low = Float.valueOf(lowcarbs.getText());
					float high = Float.valueOf(highcarbs.getText());
					
					if (low < 0)
						throw new IllegalArgumentException();
					if (low > high)
						throw new IllegalArgumentException();
					if (high < 0)
						throw new IllegalArgumentException();
					
					subList = subList.getFoodRange("carbs", low, high);
				} catch (Exception e) {
					errorStage.show();
				}
			}
			if (fiberBox.isSelected()) { // if fiber box is checked apply range criterion
				try {
					float low = Float.valueOf(lowfiber.getText());
					float high = Float.valueOf(highfiber.getText());
					
					if (low < 0)
						throw new IllegalArgumentException();
					if (low > high)
						throw new IllegalArgumentException();
					if (high < 0)
						throw new IllegalArgumentException();
					
					subList = subList.getFoodRange("fiber", low, high);
				} catch (Exception e) {
					errorStage.show();
				}
			}
			if (fatsBox.isSelected()) { // if fats box is checked apply range criterion
				try {
					float low = Float.valueOf(lowfats.getText());
					float high = Float.valueOf(highfats.getText());
					
					if (low < 0)
						throw new IllegalArgumentException();
					if (low > high)
						throw new IllegalArgumentException();
					if (high < 0)
						throw new IllegalArgumentException();
					
					subList = subList.getFoodRange("fat", low, high);
				} catch (Exception e) {
					errorStage.show();
				}
			}
			if (proteinBox.isSelected()) { // if the protein box is checked apply range criterion
				try {
					float low = Float.valueOf(lowprotein.getText());
					float high = Float.valueOf(highprotein.getText());
					
					if (low < 0)
						throw new IllegalArgumentException();
					if (low > high)
						throw new IllegalArgumentException();
					if (high < 0)
						throw new IllegalArgumentException();
					
					subList = subList.getFoodRange("protein", low, high); 
				} catch (Exception e) {
					errorStage.show();
				}
			}
			foodList.setItems(FXCollections.observableArrayList(subList.getNames())); // displays names from foodList
		});

		// event handler for user adding an individual food
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

				Food newFood = new Food(entername.getText(), Float.valueOf(entercalories.getText()),
						Float.valueOf(enterfats.getText()), Float.valueOf(enterprotein.getText()),
						Float.valueOf(entercarbs.getText()), Float.valueOf(enterfiber.getText()));
				all.insertFood(newFood);
				display.set(all);
				foodList.setItems(FXCollections.observableArrayList(display.get().getNames())); // displays names from
																								// foodList
			} catch (Exception e) {
				errorStage.show();
			}
		});

		// add to meal event handler
		addToMeal.setOnMouseClicked((event) -> {
			// copy highlighted items from food list to the meal list
			Food addItem = all.getFood(foodList.getSelectionModel().getSelectedItem());
			menu.get().insertFood(addItem);
			mealList.setItems(FXCollections.observableArrayList(menu.get().getNames())); // displays names from foodList
		});

		// event handler for clearing from the meal list
		clear.setOnMouseClicked((event) -> {
			menu.set(new FoodList()); // create an empty list
			mealList.setItems(FXCollections.observableArrayList(menu.get().getNames())); // displays names from foodList
		});

		// remove from meal event handler
		removeFromMeal.setOnMouseClicked((event) -> {
			// remove highlighted items from meal list
			menu.get().removeFood(mealList.getSelectionModel().getSelectedItem());
			mealList.setItems(FXCollections.observableArrayList(menu.get().getNames())); // displays names from foodList

		});

		// event handler for importing food
		importFood.setOnMouseClicked((event) -> {
			FileChooser fileChooser = new FileChooser(); // generate file explorer for user
			fileChooser.setTitle("Open Resource File");
			fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv")); // only
																													// accept
																													// csv
																													// files
			File chosen = fileChooser.showOpenDialog(primaryStage);
			Scanner scanner = null;
			System.out.print(fileChooser.getInitialFileName());
			try { // parsing the user file input
				scanner = new Scanner(chosen);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			scanner.useDelimiter(",|\\n"); // parsing delimiter
			while (scanner.hasNext()) { // loop for parsing
				String[] info = new String[6];
				Boolean valid = true;
				for (int i = 0; i < 6; i++) {
					scanner.next();
					info[i] = scanner.next().replaceAll("\\s+", "");
					if (info[i] == null || info[i].equals("")) { // check for valid inputs from parsing
						valid = false;
					}
				}
				if (valid) {
					Food item = new Food(info[0], Float.valueOf(info[1]), Float.valueOf(info[2]),
							Float.valueOf(info[5]), Float.valueOf(info[3]), Float.valueOf(info[4]));
					all.insertFood(item);
				}
			}
			scanner.close();
			foodList.setItems(FXCollections.observableArrayList(all.getNames()));
		});

		// event handler for exporting the food list to a .csv file
		exportFood.setOnMouseClicked((event) -> {
			FileChooser fileChooser = new FileChooser(); // choose location to save file
			fileChooser.setTitle("Open Resource File");
			File path = fileChooser.showSaveDialog(primaryStage);
			PrintWriter pw = null;
			try {
				pw = new PrintWriter(path);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			StringBuilder sb = new StringBuilder();
			for (Food item : all.getAll()) { // print to csv in valid read in format
				sb.append(item);
				sb.append(',');
				sb.append(item.getName() + ',');
				sb.append("calories," + item.getCal() + ',');
				sb.append("fat," + item.getFat() + ',');
				sb.append("carbohydrate," + item.getCarb() + ',');
				sb.append("fiber," + item.getFiber() + ',');
				sb.append("protein," + item.getProtein());
				sb.append('\n');

			}
			pw.write(sb.toString());
			pw.close();
		});

		// event handler for analyzing meal
		analyzeMeal.setOnMouseClicked((event) -> {
			Float[] data = { new Float(0), new Float(0), new Float(0), new Float(0), new Float(0) }; // array for
																										// storing
																										// nutrition
																										// totals
			for (Food foodItem : menu.get().getAll()) {	// loop for summing all of the nutrition totals in meal list
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
