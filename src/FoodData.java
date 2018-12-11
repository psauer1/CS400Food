
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

/**
 * This class represents the backend for managing all 
 * the operations associated with FoodItems
 *
 * @author sapan (sapan@cs.wisc.edu)
 */
public class FoodData implements FoodDataADT<FoodItem> {

	/*fields*/
	
    private List<FoodItem> foodItemList = new ArrayList<>();	// List of all the food items.
    private HashMap<String, TreeMap<Double, ArrayList<FoodItem>>> indexes = new HashMap<>();	// Map of nutrients and their corresponding index

    /**
     * Public constructor
     */
    public FoodData() {
        indexes.put("calories", new TreeMap<Double, ArrayList<FoodItem>>());
        indexes.put("protein", new TreeMap<Double, ArrayList<FoodItem>>());
        indexes.put("carbs", new TreeMap<Double, ArrayList<FoodItem>>());
        indexes.put("fat", new TreeMap<Double, ArrayList<FoodItem>>());
        indexes.put("fiber", new TreeMap<Double, ArrayList<FoodItem>>());
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
            String[] info = new String[7];
            Boolean valid = true;
            info[0]=scanner.next();
            info[1]=scanner.next();
            if (info[0] == null || info[0].equals("")) { // check for valid inputs from parsing
                valid = false;
            }
            if (info[1] == null || info[1].equals("")) { // check for valid inputs from parsing
                valid = false;
            }
            for (int i = 2; i < 7; i++) {
                scanner.next();
                info[i] = scanner.next().replaceAll("\\s+", "");
                if (info[i] == null || info[i].equals("")) { // check for valid inputs from parsing
                    valid = false;
                }
            }
            if (valid) {
                System.out.println(info[0]+" "+info[1]+" "+info[2]+" "+info[3]+" "+info[4]+" "+info[5]+" "+info[6]);
                FoodItem item = new FoodItem(info[0], info[1], Double.valueOf(info[2]), Double.valueOf(info[3]),
                        Double.valueOf(info[6]), Double.valueOf(info[4]), Double.valueOf(info[5]));
                addFoodItem(item);
            }
        }
        scanner.close();    
    }

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
        FoodData subList = this;
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

    /**
     * Gets all of the names of the items in the food list
     * 
     * @return List<String>
     */
    public List<String> getNames() {
        ArrayList<String> names = new ArrayList<>();
        for(FoodItem nameItem:foodItemList){
            names.add(nameItem.getName());
        }
        return names;
    }

    /**
     * Removes food item from food list
     * 
     * @param name
     */
    public void removeFoodItem(String name){
        for(FoodItem food : foodItemList){
            if(food.getName().equals(name))
            	foodItemList.remove(food);	// if the item name matches remove it
        }
    }

    /**
     * Gets the food item that matches the parameter name, throws an exception if there is not match
     * 
     * @param name
     * @return FoodItem
     */
    public FoodItem getFood(String name){
        for(FoodItem food : foodItemList){
            if(food.getName().equals(name)) return food;
        }
        throw new NoSuchElementException();
    }

    /**
     * Creates an input for a file to be exported
     * 
     * @param filename
     */
    public void saveFoodItems(String filename){
        File newCSV = new File(filename);
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(newCSV);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        for (FoodItem item : foodItemList) { // print to csv in valid read in format
            sb.append(item.getID());
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
    }

    /**
     * Returns food items that satisfy the rules passed into the method
     * 
     * @param key
     * @param low
     * @param high
     * @return
     * @throws IllegalArgumentException
     */
    private FoodData getFoodRange(String key, Double low, Double high) throws IllegalArgumentException {
        Collection<ArrayList<FoodItem>> searchList = indexes.get(key).subMap(low, high).values();
        ArrayList<FoodItem> allItems = new ArrayList<>();
        for(ArrayList<FoodItem> e : searchList){
            allItems.addAll(e);
        }
        FoodData subTree = new FoodData();
        subTree.insertAllFood(allItems.toArray(new FoodItem[allItems.size()]));
        return subTree;
    }

    /**
     * Private helper
     * Inserts item all of the tree maps within the hash map
     *
     * @param item
     */
    private void putVals(FoodItem item) {

        ArrayList<FoodItem> newCal = indexes.get("calories").get(item.getCal());
        if(newCal == null) newCal = new ArrayList<>();
        newCal.add(item);
        ArrayList<FoodItem> newProt = indexes.get("protein").get(item.getProtein());
        if(newProt == null) newProt = new ArrayList<>();
        newProt.add(item);
        ArrayList<FoodItem> newCarbs = indexes.get("carbs").get(item.getCarb());
        if(newCarbs == null) newCarbs = new ArrayList<>();
        newCarbs.add(item);
        ArrayList<FoodItem> newFat = indexes.get("fat").get(item.getFat());
        if(newFat == null) newFat = new ArrayList<>();
        newFat.add(item);
        ArrayList<FoodItem> newFib = indexes.get("fiber").get(item.getFiber());
        if(newFib == null) newFib = new ArrayList<>();
        newFib.add(item);

        foodItemList.add(item);
        indexes.get("calories").put(item.getCal(),newCal);
        indexes.get("protein").put(item.getProtein(), newProt);
        indexes.get("carbs").put(item.getCarb(), newCarbs);
        indexes.get("fat").put(item.getFat(), newFat);
        indexes.get("fiber").put(item.getFiber(), newFib);
    }

    /**
     * Inserts an array of food items into the data structure
     * 
     * @param items
     */
    public void insertAllFood(FoodItem[] items) {
        for (FoodItem item : items) {
            putVals(item);
        }
    }
}
