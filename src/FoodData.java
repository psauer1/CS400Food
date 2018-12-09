import apple.laf.JRSUIUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * This class represents the backend for managing all 
 * the operations associated with FoodItems
 *
 * @author sapan (sapan@cs.wisc.edu)
 */
public class FoodData implements FoodDataADT<FoodItem> {

    // List of all the food items.
    private List<FoodItem> foodItemList;

    // Map of nutrients and their corresponding index
    private HashMap<String, TreeMap<Double, FoodItem>> indexes;


    /**
     * Public constructor
     */
    public FoodData() {
        indexes.put("calories", new TreeMap<Double, FoodItem>());
        indexes.put("protein", new TreeMap<Double, FoodItem>());
        indexes.put("carbs", new TreeMap<Double, FoodItem>());
        indexes.put("fat", new TreeMap<Double, FoodItem>());
        indexes.put("fiber", new TreeMap<Double, FoodItem>());
    }


    /*
     * (non-Javadoc)
     * @see skeleton.FoodDataADT#loadFoodItems(java.lang.String)
     */
    @Override
    public void loadFoodItems(String filePath) {
        Scanner scanner = null;
        File chosen = new File(filePath);
        try { // parsing the user file input
            scanner = new Scanner(chosen);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        scanner.useDelimiter(",|\\n"); // parsing delimiter
        while (scanner.hasNext()) { // loop for parsing
            String[] info = new String[6];
            Boolean valid = true;
            info[0]=scanner.next();
            for (int i = 1; i < 7; i++) {
                scanner.next();
                info[i] = scanner.next().replaceAll("\\s+", "");
                if (info[i] == null || info[i].equals("")) { // check for valid inputs from parsing
                    valid = false;
                }
            }
            if (valid) {
                FoodItem item = new FoodItem(info[0], info[1], Double.valueOf(info[2]), Double.valueOf(info[3]),
                        Double.valueOf(info[6]), Double.valueOf(info[4]), Double.valueOf(info[5]));
                addFoodItem(item);
            }
        }
        scanner.close();    }

    /*
     * (non-Javadoc)
     * @see skeleton.FoodDataADT#filterByName(java.lang.String)
     */
    @Override
    public List<FoodItem> filterByName(String substring) {
        ArrayList<FoodItem> finalNames = new ArrayList<>();
        for(FoodItem item : foodItemList){
            if(item.getName().contains(substring)) finalNames.add(item);
        }

        return finalNames;
    }

    /*
     * (non-Javadoc)
     * @see skeleton.FoodDataADT#filterByNutrients(java.util.List)
     */
    @Override
    public List<FoodItem> filterByNutrients(List<String> rules) {
        FoodData subList = new FoodData();
        for(String rule : rules){
            String[] r = rule.split(" ");
            if(r[1].equals("==")) {
                subList = subList.getFoodRange(r[0], Double.valueOf(r[2]), Double.valueOf(r[2]));
            }
            else if(r[1].equals(">=")) {
                subList = subList.getFoodRange(r[0], Double.valueOf(r[2]), (double) 100000);
            }
            else if(r[1].equals("<=")) {
                subList = subList.getFoodRange(r[0], (double) -1.0, Double.valueOf(r[2]));
            }

        }
        return subList.getAllFoodItems();
    }

    /*
     * (non-Javadoc)
     * @see skeleton.FoodDataADT#addFoodItem(skeleton.FoodItem)
     */
    @Override
    public void addFoodItem(FoodItem foodItem) {
        putVals(foodItem);
    }

    /*
     * (non-Javadoc)
     * @see skeleton.FoodDataADT#getAllFoodItems()
     */
    @Override
    public List<FoodItem> getAllFoodItems() {
        return foodItemList;
    }

    public void saveFoodItems(String filename){

    }


    private FoodData getFoodRange(String key, Double low, Double high) throws IllegalArgumentException {
        // TODO: Add user input handling
        Collection<FoodItem> searchList = indexes.get(key).subMap(low, high).values();
        FoodData subTree = new FoodData();
        subTree.insertAllFood(searchList.toArray(new FoodItem[searchList.size()]));
        return subTree;
    }

    /**
     * Private helper
     *
     * Inserts item all of the tree maps within the hash map
     *
     * @param item
     */
    private void putVals(FoodItem item) {
        foodItemList.add(item);
        indexes.get("calories").put(item.getCal(), item);
        indexes.get("protein").put(item.getProtein(), item);
        indexes.get("carbs").put(item.getCarb(), item);
        indexes.get("fat").put(item.getFat(), item);
        indexes.get("fiber").put(item.getFiber(), item);
    }

    private void insertAllFood(FoodItem[] items) {
        for (FoodItem item : items) {
            putVals(item);
        }
    }

}

