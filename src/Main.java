/**
 * Filename:   Main.java
 * Project:    p5 - Meal Analysis
 * Authors:    Peter Sauer, Riley Ley, Taylor Chappell, Andy Boho, Nathan Husemoller
 *
 * Semester:   Fall 2018
 * Course:     CS400
 * Lecture:    002 & 003
 * 
 * 
 * Due Date:   Before 10pm on December 12, 2018
 * Version:    1.0
 * 
 * Credits:    
 *
 * Bugs:       
 */

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

	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

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

		VBox options3 = new VBox(); // creates VBox for adding to the program
		HBox options = new HBox(); // creates VBox for the sorting of the program
		options.prefWidthProperty().bind(options3.widthProperty().multiply(1));
		HBox options2 = new HBox(); // creates VBox for the sorting of the program
		options2.prefWidthProperty().bind(options3.widthProperty().multiply(1));
		options3.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.33));
		options3.getChildren().add(options);
		options3.getChildren().add(options2);

		AnalyzeSubSection analyzeSection = new AnalyzeSubSection(root); // Analyzing food portion of the GUI handled in
																		// driver class
		SortSubSection sortSection = new SortSubSection(root); // Sorting food portion of the GUI handled in driver
																// class
		AddSubSection addSection = new AddSubSection(root); // Adding food portion of the GUI handled in driver class

		TitledPane sortPane = new TitledPane("Sort Food Items", sortSection.sort); // creates drop down pane for sorting
																					// food
		TitledPane addPane = new TitledPane("Add Food Items", addSection.add); // creates drop down pane for adding food
		sortPane.getStyleClass().add("danger"); // styles the pane
		addPane.getStyleClass().add("success"); // styles the pane
		sortPane.setCollapsible(true); // creates collapsible aspect of the pane
		Accordion accordion = new Accordion(); // creates accordion to contain the drop down panes
		accordion.getPanes().addAll(sortPane, addPane); // adds both drop down panes
		options3.getChildren().add(accordion); // adds accordion to the HBox

		TitledPane analyzePane = new TitledPane("Analyze", analyzeSection.analyze); // creates pane for analyzing meal
		analyzePane.getStyleClass().add("info"); // add style to the pane
		analyzePane.setCollapsible(false); // does not allow for collapsibility
		root.getChildren().add(options3); // add sort VBox to the main HBox
		root.getChildren().add(analyzePane); // add analyze VBox to the main HBox

		Scene scene = new Scene(root, 800, 500); // set scene with main HBox
		scene.getStylesheets().add("fx.css"); // apply style sheet
		primaryStage.setTitle("ALL THE RIGHT FOOD IN ALL THE RIGHT PLACES"); // set title for program
		primaryStage.setScene(scene); // set the scene on the primary stage
		primaryStage.show(); // show primary stage

		ErrorWindow errorWindow = new ErrorWindow(); // error window that is displayed for users when they input invalid
														// arguments

		/*
		 * Event handlers
		 * 
		 * Responds to the user interactions with the GUI
		 */

		/*
		 * Sort button event handler
		 * 
		 * Applies the criteria specified for each field, displays the error message if
		 * the user inputed invalid arguments
		 */
		sortSection.sortFood.setOnMouseClicked((event) -> {
			FoodData subList = all; // sublist built throughout the sorting event
			ArrayList<String> searchList = new ArrayList<String>();
			ErrorWindow sortErrorWindow = new ErrorWindow(); // creates a sort error window

			if (sortSection.nameBox.isSelected()) { // if name box is checked
				try {
					subList = new FoodData(); // create a new food list
					for (FoodItem nameFiltered : all.filterByName(sortSection.lowname.getText())) { // if the search
																									// item contains
						subList.addFoodItem(nameFiltered);
					}
				} catch (Exception e) { // error thrown from name input
					sortErrorWindow.errorMessage(1, "Invalid Name Search Parameters");
					sortErrorWindow.show();
				}
			}

			if (sortSection.caloriesBox.isSelected()) { // if calories box is checked apply range criterion
				try {
					// Input safety checks
					double low = Double.valueOf(sortSection.lowCalories.getText());
					double high = Double.valueOf(sortSection.highCalories.getText());

					if (low < 0)
						throw new IllegalArgumentException();
					if (low > high)
						throw new IllegalArgumentException();
					if (high < 0)
						throw new IllegalArgumentException();

					String lowString = String.valueOf(sortSection.lowCalories.getText());
					String highString = String.valueOf(sortSection.highCalories.getText());

					searchList.add("calories >= " + lowString);
					searchList.add("calories <= " + highString);
				} catch (Exception e) { // error thrown from number input
					sortErrorWindow.errorMessage(1, "Invalid Calories Search Parameters");
					sortErrorWindow.show();
				}
			}
			if (sortSection.carbsBox.isSelected()) { // if carbs box is checked apply range criterion
				try {
					// Input safety checks
					double low = Double.valueOf(sortSection.lowcarbs.getText());
					double high = Double.valueOf(sortSection.highcarbs.getText());

					if (low < 0)
						throw new IllegalArgumentException();
					if (low > high)
						throw new IllegalArgumentException();
					if (high < 0)
						throw new IllegalArgumentException();

					String lowString = String.valueOf(sortSection.lowcarbs.getText());
					String highString = String.valueOf(sortSection.highcarbs.getText());

					searchList.add("carbs >= " + lowString);
					searchList.add("carbs <= " + highString);
				} catch (Exception e) { // error thrown from number input
					sortErrorWindow.errorMessage(1, "Invalid Carbs Search Parameters");
					sortErrorWindow.show();
				}
			}
			if (sortSection.fiberBox.isSelected()) { // if fiber box is checked apply range criterion
				try {
					// Input safety checks
					double low = Double.valueOf(sortSection.lowfiber.getText());
					double high = Double.valueOf(sortSection.highfiber.getText());

					if (low < 0)
						throw new IllegalArgumentException();
					if (low > high)
						throw new IllegalArgumentException();
					if (high < 0)
						throw new IllegalArgumentException();

					String lowString = String.valueOf(sortSection.lowfiber.getText());
					String highString = String.valueOf(sortSection.highfiber.getText());

					searchList.add("fiber >= " + lowString);
					searchList.add("fiber <= " + highString);
				} catch (Exception e) { // error thrown from number input
					sortErrorWindow.errorMessage(1, "Invalid Fiber Search Parameters");
					sortErrorWindow.show();
				}
			}
			if (sortSection.fatsBox.isSelected()) { // if fats box is checked apply range criterion
				try {
					// Input safety checks
					double low = Double.valueOf(sortSection.lowfats.getText());
					double high = Double.valueOf(sortSection.highfats.getText());

					if (low < 0)
						throw new IllegalArgumentException();
					if (low > high)
						throw new IllegalArgumentException();
					if (high < 0)
						throw new IllegalArgumentException();

					String lowString = String.valueOf(sortSection.lowfats.getText());
					String highString = String.valueOf(sortSection.highfats.getText());

					searchList.add("fats >= " + lowString);
					searchList.add("fats <= " + highString);
				} catch (Exception e) { // error thrown from number input
					sortErrorWindow.errorMessage(1, "Invalid Fats Search Parameters");
					sortErrorWindow.show();
				}
			}
			if (sortSection.proteinBox.isSelected()) { // if the protein box is checked apply range criterion
				try {
					// Input safety checks
					double low = Double.valueOf(sortSection.lowprotein.getText());
					double high = Double.valueOf(sortSection.highprotein.getText());

					if (low < 0)
						throw new IllegalArgumentException();
					if (low > high)
						throw new IllegalArgumentException();
					if (high < 0)
						throw new IllegalArgumentException();

					String lowString = String.valueOf(sortSection.lowprotein.getText());
					String highString = String.valueOf(sortSection.highprotein.getText());

					searchList.add("protein >= " + lowString);
					searchList.add("protein <= " + highString);
				} catch (Exception e) { // error thrown from number input
					sortErrorWindow.errorMessage(1, "Invalid Protein Search Parameters");
					sortErrorWindow.show();
				}
			}
			ArrayList<String> nameList = new ArrayList<>();
			for (FoodItem temp : subList.filterByNutrients(searchList)) {
				nameList.add(temp.getName());
			}
			analyzeSection.foodList.setItems(FXCollections.observableArrayList(nameList)); // displays names from
																							// foodList
		});

		/*
		 * Event handler for user adding an individual food
		 * 
		 * Reads in the user inputs and creates a food item with the specified
		 * arguments, displays an error message if the user inputs an illegal argument
		 */
		addSection.addFood.setOnMouseClicked((event) -> {
			ErrorWindow addErrorWindow = new ErrorWindow();
			String message = "";
			int type = -1;
			try {
				if (addSection.entercalories.getText() == null || addSection.entercalories.getText().trim().isEmpty()
						|| addSection.entername.getText() == null || addSection.entername.getText().trim().isEmpty()
						|| addSection.enterfats.getText() == null || addSection.enterfats.getText().trim().isEmpty()
						|| addSection.entercarbs.getText() == null || addSection.entercarbs.getText().trim().isEmpty()
						|| addSection.enterfiber.getText() == null || addSection.enterfiber.getText().trim().isEmpty()
						|| addSection.enterprotein.getText() == null
						|| addSection.enterprotein.getText().trim().isEmpty()) {
					message = "Missing Field";
					type = 2;
					addErrorWindow.errorMessage(type, message);
					throw new IllegalArgumentException();
				}
				if (Float.valueOf(addSection.entercalories.getText()) < 0) {
					message = "Invalid Calories ";
					type = 1;
					addErrorWindow.errorMessage(type, message);

				}
				if (Float.valueOf(addSection.enterfats.getText()) < 0) {
					message = "Invalid Fats ";
					type = 1;
					addErrorWindow.errorMessage(type, message);

				}
				if (Float.valueOf(addSection.entercarbs.getText()) < 0) {
					message = "Invalid Carbs";
					type = 1;
					addErrorWindow.errorMessage(type, message);
				}
				if (Float.valueOf(addSection.enterfiber.getText()) < 0) {
					message = "Invalid Fiber ";
					type = 1;
					addErrorWindow.errorMessage(type, message);

				}
				if (Float.valueOf(addSection.enterprotein.getText()) < 0) {
					message = "Invalid Protein ";
					type = 1;
					addErrorWindow.errorMessage(type, message);
				}

				String secretKey = randomAlphaNumeric(all);

				if (type != -1) {
					throw new IllegalArgumentException();
				}

				// Create a new food item with inputs
				FoodItem newFood = new FoodItem(secretKey, addSection.entername.getText(),
						Double.valueOf(addSection.entercalories.getText()),
						Double.valueOf(addSection.enterfats.getText()),
						Double.valueOf(addSection.enterprotein.getText()),
						Double.valueOf(addSection.entercarbs.getText()),
						Double.valueOf(addSection.enterfiber.getText()));
				all.addFoodItem(newFood);
				display.set(all);
				analyzeSection.foodList.setItems(FXCollections.observableArrayList(display.get().getNames())); // displays
																												// names
																												// from
			} catch (NumberFormatException e) {
				addErrorWindow.errorMessage(1, "String Entered For Nutrient Value");
				addErrorWindow.show();
			} // foodList
			catch (IllegalArgumentException e) { // error message for invalid inputs
				addErrorWindow.show();
			}
		});

		/*
		 * Add to meal button event handler
		 * 
		 * Moves the highlighted food items within the food list to the meal list
		 */
		analyzeSection.addToMeal.setOnMouseClicked((event) -> {
			FoodItem addItem = all.getFood(analyzeSection.foodList.getSelectionModel().getSelectedItem()); // copy
																											// highlighted
																											// items
																											// from food
																											// list to
																											// the meal
																											// list
			menu.get().addFoodItem(addItem);
			analyzeSection.mealList.setItems(FXCollections.observableArrayList(menu.get().getNames())); // displays
																										// names from
																										// foodList
		});

		/*
		 * Event handler for clear button
		 * 
		 * Removes all of the items from the meal list and displays an emtpy list
		 */
		analyzeSection.clear.setOnMouseClicked((event) -> {
			menu.set(new FoodData()); // create an empty list
			analyzeSection.mealList.setItems(FXCollections.observableArrayList(menu.get().getNames())); // displays
																										// names from
																										// foodList
		});

		/*
		 * Remove button event handler
		 * 
		 * Removes highlighted items from the meal list, does not affect unselected
		 * items
		 */
		analyzeSection.removeFromMeal.setOnMouseClicked((event) -> {
			menu.get().removeFoodItem(analyzeSection.mealList.getSelectionModel().getSelectedItem()); // remove
																										// highlighted
																										// items from
																										// meal list
			analyzeSection.mealList.setItems(FXCollections.observableArrayList(menu.get().getNames())); // displays
																										// names from
																										// foodList
		});

		/*
		 * Event handler for import food button
		 * 
		 * Displays a file chooser for user to select a .csv file to import from
		 */
		addSection.importFood.setOnMouseClicked((event) -> {
			FileChooser fileChooser = new FileChooser(); // generate file explorer for user
			fileChooser.setTitle("Open Resource File"); // title file explorer
			fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv")); // only
																													// accepts
																													// .csv
																													// files
			File chosen = fileChooser.showOpenDialog(primaryStage); // creates file from chosen file
			all.loadFoodItems(chosen.getAbsolutePath()); // Loads all of the items from file
			analyzeSection.foodList.setItems(FXCollections.observableArrayList(all.getNames())); // display imported
																									// items in the food
																									// list
		});

		/*
		 * Event handler for export food button
		 * 
		 * Takes all of the items in the food list and prints them into a .csv with the
		 * same format as input files
		 */
		addSection.exportFood.setOnMouseClicked((event) -> {
			FileChooser fileChooser = new FileChooser(); // choose location to save file
			fileChooser.setTitle("Choose location to export file"); // title of file chooser
			File path = fileChooser.showSaveDialog(primaryStage); // create file for the given path
			all.saveFoodItems(path.getAbsolutePath()); // save items with given file path
		});

		analyzeSection.infoButton.setOnMouseClicked((event) -> {
			InfoWindow info = new InfoWindow();
			info.show();
		});

		/*
		 * Event handler for analyze button
		 * 
		 * Sums all of the food items nutritional values from the meal list and displays
		 * a new window with a summary
		 */
		analyzeSection.analyzeMeal.setOnMouseClicked((event) -> {
			AnalyzeWindow analyzeWindow = new AnalyzeWindow(menu.get());
		});
	}

	/**
	 * Generates a string that is used for user inputed foods to allow for
	 * duplicates item names
	 * 
	 * @param FoodData
	 * @return String
	 */
	private String randomAlphaNumeric(FoodData b) {
		StringBuilder builder = new StringBuilder(); // food id builder
		int count = 30; // length of key
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length()); // generate random char
			builder.append(ALPHA_NUMERIC_STRING.charAt(character)); // add random char to string
		}
		String string = builder.toString(); // food id
		for (FoodItem a : b.getAllFoodItems()) { // looks for duplicates keys within data structure
			if (a.getID().equals(string)) {
				string = randomAlphaNumeric(b);
			}
		}
		return string;
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
