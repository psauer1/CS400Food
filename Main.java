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

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox root = new HBox();

        root.setSpacing(10);
        root.setPadding(new Insets(15,20, 10,10));
        root.prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.9));

        VBox sort = new VBox();
        VBox analyze = new VBox();
        VBox add = new VBox();


        //Sort Section
        Label sortLabel = new Label("Sort Foods");
        sortLabel.setPadding(new Insets(0,0, 30,0));
        sortLabel.setFont(new Font("Arial",24));
        sort.getChildren().add(sortLabel);

        sort.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.25));
        HBox name = new HBox();
        name.setPadding(new Insets(0,0, 10,0));
        name.prefWidthProperty().bind(sort.widthProperty().multiply(1.0));

        CheckBox nameBox = new CheckBox("Name");
        nameBox.prefWidthProperty().bind(name.widthProperty().multiply(0.5));
        TextField lowname = new TextField("");
        lowname.prefWidthProperty().bind(name.widthProperty().multiply(.5));

        name.getChildren().add(nameBox);
        name.getChildren().add(lowname);

        sort.getChildren().add(name);

        sort.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.25));
        HBox calories = new HBox();
        calories.setPadding(new Insets(0,0, 10,0));
        calories.prefWidthProperty().bind(sort.widthProperty().multiply(1.0));

        CheckBox caloriesBox = new CheckBox("Calories");
        caloriesBox.prefWidthProperty().bind(calories.widthProperty().multiply(0.5));
        TextField lowCalories = new TextField("Low");
        lowCalories.prefWidthProperty().bind(calories.widthProperty().multiply(.250));
        TextField highCalories = new TextField("High");
        highCalories.prefWidthProperty().bind(calories.widthProperty().multiply(.250));

        calories.getChildren().add(caloriesBox);
        calories.getChildren().add(lowCalories);
        calories.getChildren().add(highCalories);

        sort.getChildren().add(calories);

        sort.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.25));
        HBox fats = new HBox();
        fats.setPadding(new Insets(0,0, 10,0));
        fats.prefWidthProperty().bind(sort.widthProperty().multiply(1.0));

        CheckBox fatsBox = new CheckBox("Fats");
        fatsBox.prefWidthProperty().bind(fats.widthProperty().multiply(0.5));
        TextField lowfats = new TextField("Low");
        lowfats.prefWidthProperty().bind(fats.widthProperty().multiply(.250));
        TextField highfats = new TextField("High");
        highfats.prefWidthProperty().bind(fats.widthProperty().multiply(.250));

        fats.getChildren().add(fatsBox);
        fats.getChildren().add(lowfats);
        fats.getChildren().add(highfats);

        sort.getChildren().add(fats);

        sort.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.25));
        HBox carbs = new HBox();
        carbs.setPadding(new Insets(0,0, 10,0));
        carbs.prefWidthProperty().bind(sort.widthProperty().multiply(1.0));

        CheckBox carbsBox = new CheckBox("Carbs");
        carbsBox.prefWidthProperty().bind(carbs.widthProperty().multiply(0.5));
        TextField lowcarbs = new TextField("Low");
        lowcarbs.prefWidthProperty().bind(carbs.widthProperty().multiply(.250));
        TextField highcarbs = new TextField("High");
        highcarbs.prefWidthProperty().bind(carbs.widthProperty().multiply(.250));

        carbs.getChildren().add(carbsBox);
        carbs.getChildren().add(lowcarbs);
        carbs.getChildren().add(highcarbs);

        sort.getChildren().add(carbs);

        sort.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.25));
        HBox fiber = new HBox();
        fiber.setPadding(new Insets(0,0, 10,0));
        fiber.prefWidthProperty().bind(sort.widthProperty().multiply(1.0));

        CheckBox fiberBox = new CheckBox("Fiber");
        fiberBox.prefWidthProperty().bind(fiber.widthProperty().multiply(0.5));
        TextField lowfiber = new TextField("Low");
        lowfiber.prefWidthProperty().bind(fiber.widthProperty().multiply(.250));
        TextField highfiber = new TextField("High");
        highfiber.prefWidthProperty().bind(fiber.widthProperty().multiply(.250));

        fiber.getChildren().add(fiberBox);
        fiber.getChildren().add(lowfiber);
        fiber.getChildren().add(highfiber);

        sort.getChildren().add(fiber);


        sort.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.25));
        HBox protein = new HBox();
        protein.setPadding(new Insets(0,0, 10,0));
        protein.prefWidthProperty().bind(sort.widthProperty().multiply(1.0));

        CheckBox proteinBox = new CheckBox("Protein");
        proteinBox.prefWidthProperty().bind(protein.widthProperty().multiply(0.5));
        TextField lowprotein = new TextField("Low");
        lowprotein.prefWidthProperty().bind(protein.widthProperty().multiply(.250));
        TextField highprotein = new TextField("High");
        highprotein.prefWidthProperty().bind(protein.widthProperty().multiply(.250));

        protein.getChildren().add(proteinBox);
        protein.getChildren().add(lowprotein);
        protein.getChildren().add(highprotein);

        sort.getChildren().add(protein);

        Button sortFood = new Button("Sort Food");
        sortFood.prefWidthProperty().bind(sort.widthProperty().multiply(1.0));
        sort.getChildren().add(sortFood);


        //Analyze Section
        Label analyzeLabel = new Label("Analyze Meal");
        analyzeLabel.setPadding(new Insets(0,0, 30,0));
        analyzeLabel.setFont(new Font("Arial",24));
        analyze.getChildren().add(analyzeLabel);

        analyze.prefWidthProperty().bind(root.widthProperty().multiply(0.50));
        HBox subAnalyze = new HBox();
        subAnalyze.setPadding(new Insets(0,0, 10,0));

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
        ObservableList<String> itemsInMeal = FXCollections.observableArrayList ();
        mealList.setItems(itemsInMeal);
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

        Button analyzeMeal = new Button("Analyze Meal");
        analyzeMeal.prefWidthProperty().bind(analyze.widthProperty().multiply(1.0));

        analyze.getChildren().add(subAnalyze);

        analyze.getChildren().add(analyzeMeal);




        //Add Section
        Label addLabel = new Label("Add Food");
        addLabel.setPadding(new Insets(0,0, 30,0));
        addLabel.setFont(new Font("Arial",24));
        add.getChildren().add(addLabel);

        add.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.25));

        HBox aname = new HBox();
        aname.setPadding(new Insets(0,0, 10,0));
        aname.prefWidthProperty().bind(add.widthProperty().multiply(1.0));

        Label anameBox = new Label("Name");
        anameBox.prefWidthProperty().bind(aname.widthProperty().multiply(0.25));
        TextField entername = new TextField("");
        entername.prefWidthProperty().bind(aname.widthProperty().multiply(.75));

        aname.getChildren().add(anameBox);
        aname.getChildren().add(entername);

        add.getChildren().add(aname);

        HBox acalories = new HBox();
        acalories.setPadding(new Insets(0,0, 10,0));
        acalories.prefWidthProperty().bind(add.widthProperty().multiply(1.0));

        Label acaloriesBox = new Label("Calories");
        acaloriesBox.prefWidthProperty().bind(acalories.widthProperty().multiply(0.25));
        TextField entercalories = new TextField("");
        entercalories.prefWidthProperty().bind(acalories.widthProperty().multiply(.75));

        acalories.getChildren().add(acaloriesBox);
        acalories.getChildren().add(entercalories);

        add.getChildren().add(acalories);


        HBox afats = new HBox();
        afats.setPadding(new Insets(0,0, 10,0));
        afats.prefWidthProperty().bind(add.widthProperty().multiply(1.0));

        Label afatsBox = new Label("Fats");
        afatsBox.prefWidthProperty().bind(afats.widthProperty().multiply(0.25));
        TextField enterfats = new TextField("");
        enterfats.prefWidthProperty().bind(afats.widthProperty().multiply(.75));

        afats.getChildren().add(afatsBox);
        afats.getChildren().add(enterfats);

        add.getChildren().add(afats);


        HBox acarbs = new HBox();
        acarbs.setPadding(new Insets(0,0, 10,0));
        acarbs.prefWidthProperty().bind(add.widthProperty().multiply(1.0));

        Label acarbsBox = new Label("Carbs");
        acarbsBox.prefWidthProperty().bind(acarbs.widthProperty().multiply(0.25));
        TextField entercarbs = new TextField("");
        entercarbs.prefWidthProperty().bind(acarbs.widthProperty().multiply(.75));

        acarbs.getChildren().add(acarbsBox);
        acarbs.getChildren().add(entercarbs);

        add.getChildren().add(acarbs);


        HBox afiber = new HBox();
        afiber.setPadding(new Insets(0,0, 10,0));
        afiber.prefWidthProperty().bind(add.widthProperty().multiply(1.0));

        Label afiberBox = new Label("Fiber");
        afiberBox.prefWidthProperty().bind(afiber.widthProperty().multiply(0.25));
        TextField enterfiber = new TextField("");
        enterfiber.prefWidthProperty().bind(afiber.widthProperty().multiply(.75));

        afiber.getChildren().add(afiberBox);
        afiber.getChildren().add(enterfiber);

        add.getChildren().add(afiber);



        HBox aprotein = new HBox();
        aprotein.setPadding(new Insets(0,0, 10,0));
        aprotein.prefWidthProperty().bind(add.widthProperty().multiply(1.0));

        Label aproteinBox = new Label("Protein");
        aproteinBox.prefWidthProperty().bind(aprotein.widthProperty().multiply(0.25));
        TextField enterprotein = new TextField("");
        enterprotein.prefWidthProperty().bind(aprotein.widthProperty().multiply(.75));

        aprotein.getChildren().add(aproteinBox);
        aprotein.getChildren().add(enterprotein);

        add.getChildren().add(aprotein);

        HBox addFoodContain = new HBox();
        addFoodContain.setPadding(new Insets(0,0, 10,0));
        Button addFood = new Button("Add Food");
        addFood.prefWidthProperty().bind(sort.widthProperty().multiply(1.0));
        addFoodContain.getChildren().add(addFood);
        add.getChildren().add(addFoodContain);

        Button importFood = new Button("Import Food");
        importFood.prefWidthProperty().bind(sort.widthProperty().multiply(1.0));
        add.getChildren().add(importFood);


        root.getChildren().add(sort);
        root.getChildren().add(analyze);
        root.getChildren().add(add);



        Scene scene = new Scene(root, 1000, 500);

        primaryStage.setTitle("ALL THE RIGHT FOOD IN ALL THE RIGHT PLACES");
        primaryStage.setScene(scene);
        primaryStage.show();

        addToMeal.setOnMouseClicked((event) -> {
            itemsInMeal.add(foodList.getSelectionModel().getSelectedItem());
            mealList.setItems(itemsInMeal);

        });

        removeFromMeal.setOnMouseClicked((event) -> {
            itemsInMeal.remove(mealList.getSelectionModel().getSelectedItem());
            mealList.setItems(itemsInMeal);

        });


        importFood.setOnMouseClicked((event) -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            fileChooser.showOpenDialog(primaryStage);
        });

        analyzeMeal.setOnMouseClicked((event) -> {

            Stage stageA = new Stage();
            stageA.setTitle("Analyze");

            HBox analyzeRoot = new HBox();
            analyzeRoot.setSpacing(10);
            analyzeRoot.setPadding(new Insets(15,20, 10,10));
            analyzeRoot.prefHeightProperty().bind(stageA.heightProperty().multiply(0.9));

            VBox mealFood = new VBox();
            VBox foodTotals = new VBox();

            mealFood.prefWidthProperty().bind(analyzeRoot.widthProperty().multiply(0.40));
            foodTotals.prefWidthProperty().bind(analyzeRoot.widthProperty().multiply(0.60));

            Label mealLabel = new Label("Meal");
            mealLabel.setPadding(new Insets(0,0, 30,0));
            mealLabel.setFont(new Font("Arial",24));
            mealFood.getChildren().add(mealLabel);

            ListView<String> finalMeal = new ListView<String>();
            ObservableList<String> mealFinalItems = FXCollections.observableArrayList (
                    "Carrot","Lettuce","Granola","Ice Cream");
            finalMeal.setItems(mealFinalItems);
            finalMeal.prefWidthProperty().bind(mealFood.widthProperty().multiply(1.0));
            mealFood.getChildren().add(finalMeal);

            Label nutLabel = new Label("Nutrition Facts");
            nutLabel.setPadding(new Insets(0,0, 30,0));
            nutLabel.setFont(new Font("Arial",24));
            foodTotals.getChildren().add(nutLabel);

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



            analyzeRoot.getChildren().add(mealFood);
            analyzeRoot.getChildren().add(foodTotals);
            Scene analyzeScene = new Scene(analyzeRoot, 600, 400);


            stageA.setScene(analyzeScene);
            stageA.show();
        });
    }


    public static void main(String[] args) {
        launch(args);


    }
}
