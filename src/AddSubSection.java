/**
 * Filename:   AddSubSection.java
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
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.swing.*;

public class AddSubSection {

	/* fields */
	public Button addFood;
	public Button exportFood;
	public Button importFood;
	public TextField entername;
	public TextField entercalories;
	public TextField enterfats;
	public TextField entercarbs;
	public TextField enterfiber;
	public TextField enterprotein;
	public VBox add;

	/**
	 * Creates sub section for adding food items for the user, allows for single
	 * item input, file import, and file export
	 * 
	 * @param root
	 */
	public AddSubSection(HBox root) {
		add = new VBox(); // creates VBox for adding food items to the program
		add.prefWidthProperty().bind(root.widthProperty().multiply(0.25));

		HBox aname = new HBox(); // add name HBox
		aname.setPadding(new Insets(0, 0, 10, 0));
		aname.prefWidthProperty().bind(add.widthProperty().multiply(1.0));
		Label anameBox = new Label("Name"); // label for adding name of food
		anameBox.prefWidthProperty().bind(aname.widthProperty().multiply(0.25));
		entername = new TextField(""); // text input for name of food
		entername.prefWidthProperty().bind(aname.widthProperty().multiply(.75));
		aname.getChildren().add(anameBox); // add the name label to the add name HBox
		aname.getChildren().add(entername); // add the text input to the add name HBox
		add.getChildren().add(aname); // add the add name HBox to the add VBox

		HBox acalories = new HBox(); // add calories HBox
		acalories.setPadding(new Insets(0, 0, 10, 0));
		acalories.prefWidthProperty().bind(add.widthProperty().multiply(1.0));
		Label acaloriesBox = new Label("Calories"); // label for adding calories of food
		acaloriesBox.prefWidthProperty().bind(acalories.widthProperty().multiply(0.25));
		entercalories = new TextField(""); // text input for calories of food
		entercalories.prefWidthProperty().bind(acalories.widthProperty().multiply(.75));
		acalories.getChildren().add(acaloriesBox); // add calories label to the calories HBox
		acalories.getChildren().add(entercalories); // add calories input to the calories HBox
		add.getChildren().add(acalories); // add the calories HBox to the add VBox

		HBox afats = new HBox(); // add fats HBox
		afats.setPadding(new Insets(0, 0, 10, 0));
		afats.prefWidthProperty().bind(add.widthProperty().multiply(1.0));
		Label afatsBox = new Label("Fats"); // label for fats HBox
		afatsBox.prefWidthProperty().bind(afats.widthProperty().multiply(0.25));
		enterfats = new TextField(""); // text input for fats
		enterfats.prefWidthProperty().bind(afats.widthProperty().multiply(.75));
		afats.getChildren().add(afatsBox); // add fats label to add fats HBox
		afats.getChildren().add(enterfats); // add fats input to add fats HBox
		add.getChildren().add(afats); // add fats HBox to the add VBox

		HBox acarbs = new HBox(); // add carbs HBox
		acarbs.setPadding(new Insets(0, 0, 10, 0));
		acarbs.prefWidthProperty().bind(add.widthProperty().multiply(1.0));
		Label acarbsBox = new Label("Carbs"); // label for adding carbs
		acarbsBox.prefWidthProperty().bind(acarbs.widthProperty().multiply(0.25));
		entercarbs = new TextField(""); // text input for adding carbs
		entercarbs.prefWidthProperty().bind(acarbs.widthProperty().multiply(.75));
		acarbs.getChildren().add(acarbsBox); // add carbs label to carbs HBox
		acarbs.getChildren().add(entercarbs); // add carbs input to carbs HBox
		add.getChildren().add(acarbs); // add carbs HBox to the add VBox

		HBox afiber = new HBox(); // add fiber HBox
		afiber.setPadding(new Insets(0, 0, 10, 0));
		afiber.prefWidthProperty().bind(add.widthProperty().multiply(1.0));
		Label afiberBox = new Label("Fiber"); // label for adding fiber
		afiberBox.prefWidthProperty().bind(afiber.widthProperty().multiply(0.25));
		enterfiber = new TextField(""); // text input for adding fiber
		enterfiber.prefWidthProperty().bind(afiber.widthProperty().multiply(.75));
		afiber.getChildren().add(afiberBox); // add label to the fiber HBox
		afiber.getChildren().add(enterfiber); // add input to the fiber HBox
		add.getChildren().add(afiber); // add fiber HBox to the add VBox

		HBox aprotein = new HBox(); // add protein HBox
		aprotein.setPadding(new Insets(0, 0, 10, 0));
		aprotein.prefWidthProperty().bind(add.widthProperty().multiply(1.0));
		Label aproteinBox = new Label("Protein"); // label for protein add box
		aproteinBox.prefWidthProperty().bind(aprotein.widthProperty().multiply(0.25));
		enterprotein = new TextField(""); // text input for loading protein
		enterprotein.prefWidthProperty().bind(aprotein.widthProperty().multiply(.75));
		aprotein.getChildren().add(aproteinBox); // add protein label to add protein HBox
		aprotein.getChildren().add(enterprotein); // add input to add protein HBox
		add.getChildren().add(aprotein); // add protein HBox to add VBox

		HBox addFoodContain = new HBox(); // food contain HBox
		addFoodContain.setPadding(new Insets(0, 0, 10, 0));
		addFood = new Button("Add Food Item"); // add food button
		addFood.prefWidthProperty().bind(add.widthProperty().multiply(1.0));
		addFoodContain.getChildren().add(addFood); // add food button to the add food HBox
		add.getChildren().add(addFoodContain); // add food HBox to the add VBox
		HBox exportFoodContain = new HBox(); // food contain HBox
		exportFoodContain.setPadding(new Insets(0, 0, 10, 0));
		exportFood = new Button("Export Food"); // add food button
		exportFood.prefWidthProperty().bind(add.widthProperty().multiply(1.0));
		exportFoodContain.getChildren().add(exportFood); // add food button to the add food HBox
		add.getChildren().add(exportFoodContain); // add food HBox to the add VBox
		importFood = new Button("Import Food"); // import food button for reading in files
		importFood.prefWidthProperty().bind(add.widthProperty().multiply(1.0));
		add.getChildren().add(importFood); // add import to add VBox

	}
}
