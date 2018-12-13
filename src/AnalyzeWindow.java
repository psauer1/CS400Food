/**
 * Filename:   AnalyzeWindow.java
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

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AnalyzeWindow {
    public AnalyzeWindow(FoodData menu){
        Double[] data = {(double) 0, (double) 0, (double) 0, (double) 0, (double) 0}; // array for
        // storing
        // nutrition
        // totals
        for (FoodItem foodItem : menu.getAllFoodItems()) {	// loop for summing all of the nutrition totals in meal list
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
        finalMeal.setItems(FXCollections.observableArrayList(menu.getNames())); // displays names from
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
    }
}
