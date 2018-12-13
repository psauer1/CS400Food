/**
 * Filename:   SortSubSection.java
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

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SortSubSection {

	/* fields */
	public VBox sort;
	public TextField lowname;
	public TextField lowCalories;
	public TextField highCalories;
	public TextField lowfats;
	public TextField highfats;
	public TextField lowcarbs;
	public TextField highcarbs;
	public TextField lowprotein;
	public TextField highprotein;
	public TextField lowfiber;
	public TextField highfiber;
	public CheckBox nameBox;
	public CheckBox fiberBox;
	public CheckBox proteinBox;
	public CheckBox carbsBox;
	public CheckBox caloriesBox;
	public CheckBox fatsBox;
	public Button sortFood;

	/**
	 * Sets up the portion of the GUI that allows the user to sort food for a given
	 * food list
	 * 
	 * @param root
	 */
	public SortSubSection(HBox root) {
		sort = new VBox(); // creates VBox for sorting food items from food list
		sort.prefWidthProperty().bind(root.widthProperty().multiply(0.33));

		HBox name = new HBox(); // HBox for sorting by name
		name.setPadding(new Insets(0, 0, 10, 0));
		name.prefWidthProperty().bind(sort.widthProperty().multiply(1.0));
		nameBox = new CheckBox("Name"); // creates check box for sorting by name
		nameBox.prefWidthProperty().bind(name.widthProperty().multiply(0.5));
		lowname = new TextField("");
		lowname.prefWidthProperty().bind(name.widthProperty().multiply(.5));
		name.getChildren().add(nameBox); // adds check box to the HBox
		name.getChildren().add(lowname); // adds text field for name to HBox
		sort.getChildren().add(name); // adds the HBox to the sort VBox
		sort.prefWidthProperty().bind(sort.widthProperty().multiply(1));

		HBox calories = new HBox(); // HBox for sorting by calories
		calories.setPadding(new Insets(0, 0, 10, 0));
		calories.prefWidthProperty().bind(sort.widthProperty().multiply(1.0));
		caloriesBox = new CheckBox("Calories"); // creates check box for sorting by calories
		caloriesBox.prefWidthProperty().bind(calories.widthProperty().multiply(0.5));
		lowCalories = new TextField("Low"); // creates text input for low end of calories
		lowCalories.prefWidthProperty().bind(calories.widthProperty().multiply(.250));
		highCalories = new TextField("High"); // creates text input for high end of calories
		highCalories.prefWidthProperty().bind(calories.widthProperty().multiply(.250));
		calories.getChildren().add(caloriesBox); // adds check box to the HBox
		calories.getChildren().add(lowCalories); // adds low end text field to HBox
		calories.getChildren().add(highCalories); // adds high end text field to HBox
		sort.getChildren().add(calories); // adds the HBox to the sort VBox
		sort.prefWidthProperty().bind(root.widthProperty().multiply(0.25));

		HBox fats = new HBox(); // HBox for sorting by fats
		fats.setPadding(new Insets(0, 0, 10, 0));
		fats.prefWidthProperty().bind(sort.widthProperty().multiply(1.0));
		fatsBox = new CheckBox("Fats"); // creates check box for sorting by fats
		fatsBox.prefWidthProperty().bind(fats.widthProperty().multiply(0.5));
		lowfats = new TextField("Low"); // creates text input for low end of fats
		lowfats.prefWidthProperty().bind(fats.widthProperty().multiply(.250));
		highfats = new TextField("High"); // creates text input for high end of fats
		highfats.prefWidthProperty().bind(fats.widthProperty().multiply(.250));
		fats.getChildren().add(fatsBox); // adds check box to the HBox
		fats.getChildren().add(lowfats); // adds low end text field to HBox
		fats.getChildren().add(highfats); // adds high end text field to HBox
		sort.getChildren().add(fats); // adds the HBox to the sort VBox
		sort.prefWidthProperty().bind(root.widthProperty().multiply(0.25));

		HBox carbs = new HBox(); // HBox for sorting by carbs
		carbs.setPadding(new Insets(0, 0, 10, 0));
		carbs.prefWidthProperty().bind(sort.widthProperty().multiply(1.0));
		carbsBox = new CheckBox("Carbs"); // creates check box for sorting by carbs
		carbsBox.prefWidthProperty().bind(carbs.widthProperty().multiply(0.5));
		lowcarbs = new TextField("Low"); // creates text input for low end of fats
		lowcarbs.prefWidthProperty().bind(carbs.widthProperty().multiply(.250));
		highcarbs = new TextField("High"); // creates text input for high end of fats
		highcarbs.prefWidthProperty().bind(carbs.widthProperty().multiply(.250));
		carbs.getChildren().add(carbsBox); // adds check box to the HBox
		carbs.getChildren().add(lowcarbs); // adds low end text field to HBox
		carbs.getChildren().add(highcarbs); // adds high end text field to HBox
		sort.getChildren().add(carbs); // adds the HBox to the sort VBox
		sort.prefWidthProperty().bind(root.widthProperty().multiply(0.25));

		HBox fiber = new HBox(); // HBox for sorting by carbs
		fiber.setPadding(new Insets(0, 0, 10, 0));
		fiber.prefWidthProperty().bind(sort.widthProperty().multiply(1.0));
		fiberBox = new CheckBox("Fiber"); // creates check box for searching by fiber
		fiberBox.prefWidthProperty().bind(fiber.widthProperty().multiply(0.5));
		lowfiber = new TextField("Low"); // creates text input for low end of fats
		lowfiber.prefWidthProperty().bind(fiber.widthProperty().multiply(.250));
		highfiber = new TextField("High"); // creates text input for high end of fats
		highfiber.prefWidthProperty().bind(fiber.widthProperty().multiply(.250));
		fiber.getChildren().add(fiberBox); // adds check box to the HBox
		fiber.getChildren().add(lowfiber); // adds low end text field to HBox
		fiber.getChildren().add(highfiber); // adds high end text field to HBox
		sort.getChildren().add(fiber); // adds the HBox to the sort VBox
		sort.prefWidthProperty().bind(root.widthProperty().multiply(0.25));

		HBox protein = new HBox(); // HBox for sorting by protein
		protein.setPadding(new Insets(0, 0, 10, 0));
		protein.prefWidthProperty().bind(sort.widthProperty().multiply(1.0));
		proteinBox = new CheckBox("Protein"); // creates check box for sorting by protein
		proteinBox.prefWidthProperty().bind(protein.widthProperty().multiply(0.5));
		lowprotein = new TextField("Low"); // creates text input for low end of protein
		lowprotein.prefWidthProperty().bind(protein.widthProperty().multiply(.250));
		highprotein = new TextField("High"); // creates text input for the high end of protein
		highprotein.prefWidthProperty().bind(protein.widthProperty().multiply(.250));
		protein.getChildren().add(proteinBox); // adds check box to the HBox
		protein.getChildren().add(lowprotein); // adds low end text field to HBox
		protein.getChildren().add(highprotein); // adds high end text field to HBox
		sort.getChildren().add(protein); // adds the HBox to the the sort VBox

		sortFood = new Button("Sort Food"); // creates new button to Sort FoodItem
		sortFood.prefWidthProperty().bind(sort.widthProperty().multiply(1.0));
		sort.getChildren().add(sortFood); // adds button to the sort VBox
	}
}
