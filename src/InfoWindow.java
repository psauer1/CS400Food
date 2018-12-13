/**
 * Filename:   InfoWindow.java
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

import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class InfoWindow {

	/* fields */
	private Stage stage;

	/**
	 * Generates an information window with instructions on how to use the program
	 */
	public InfoWindow() {
		stage = new Stage(); // creates new stage
		stage.setTitle("Instructions"); // names the error stage
		HBox outer = new HBox();
		Accordion accordion = new Accordion();
		accordion.prefWidthProperty().bind(stage.widthProperty().multiply(1.0)); // sets size of root
		outer.getChildren().add(accordion);
		Scene scene = new Scene(outer, 500, 300); // error message scene
		scene.getStylesheets().add("fx.css"); // apply style sheet
		stage.setScene(scene); // set the scene on the error stage

		// Information on how to interact with the sorting function of the program
		HBox sortContent = new HBox();
		TitledPane sort = new TitledPane("How To Use Sort", sortContent);
		TextArea instSort = new TextArea();
		instSort.setEditable(false);
		instSort.setText(
				"1. Select checkboxes of fields you want to sort \n2. Type in sortable values \n3. Click Sort Button \n4. Available items will appear in Food Items pane");
		sortContent.getChildren().add(instSort);
		sort.getStyleClass().add("danger");
		accordion.getPanes().add(sort);

		// Information on how to interact with the adding food function of the program
		HBox addContent = new HBox();
		TitledPane add = new TitledPane("How To Add/Export Food Items", addContent);
		TextArea addSort = new TextArea();
		addSort.setEditable(false);
		addSort.setText(
				"IMPORT:\n- Type in food info and click Add FoodItem \n- Click Import Food and import a valid csv file \n\n EXPORT: \n- Click Export food and the current list will export to csv. ");
		addContent.getChildren().add(addSort);
		add.getStyleClass().add("success");
		accordion.getPanes().add(add);

		// Information on how to interact with the analyzing a meal function of the
		// program
		HBox analyzeContent = new HBox();
		TitledPane analyze = new TitledPane("How To Analyze a Meal", analyzeContent);
		TextArea analyzeSort = new TextArea();
		analyzeSort.setEditable(false);
		analyzeSort.setText(
				"1. Select a food item in the Food Items list  \n2. Click Add \n3. Remove and Clear will remove from meal \n4. Click analyze meal for a full list of nutrients in the meal");
		analyzeContent.getChildren().add(analyzeSort);
		analyze.getStyleClass().add("info");
		accordion.getPanes().add(analyze);
	}

	/**
	 * Shows the instructions on how to use the program
	 */
	public void show() {
		stage.show();
	}
}
