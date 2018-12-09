
import java.util.*;

public class FoodList {

	private TreeMap<String, Food> name = new TreeMap<String, Food>();
	private HashMap<String, TreeMap> vals = new HashMap<String, TreeMap>();

	/**
	 * Constructor
	 * 
	 * Creates hash map to with internal tree map nodes for each nutrition value for a given food
	 */
	public FoodList() {
		vals.put("cal", new TreeMap<Float, Food>());
		vals.put("protein", new TreeMap<Float, Food>());
		vals.put("carbs", new TreeMap<Float, Food>());
		vals.put("fat", new TreeMap<Float, Food>());
		vals.put("fiber", new TreeMap<Float, Food>());
	}

	/**
	 * Inserting a food item into the data structure
	 * 
	 * @param item
	 */
	public void insertFood(Food item) {
		putVals(item);	// call helper method to put values into each specific hash map
	}

	/**
	 * Removing a food item form the data structure
	 * 
	 * @param itemName
	 */
	public void removeFood(String itemName) {
		name.remove(itemName); // removes the mapping from the tree to the given food item
	}

	/**
	 * Inserts all items from a given array into the data structure
	 * 
	 * @param items
	 */
	public void insertAllFood(Food[] items) {
		for (Food item : items) {
			putVals(item);
		}
	}


	/**
	 * Search through names that match a given input string
	 * 
	 * @param searchName
	 * @return
	 */
	public Food getFood(String searchName) {
		// TODO: Add error signals from handlers
		return name.getOrDefault(searchName, null);	// returns the mapping of that specific item
	}

	/**
	 * Searches through a given hash map based on the input string and returns items in that range
	 * 
	 * @param key
	 * @param high
	 * @param low
	 * @return
	 */
	public FoodList getFoodRange(String key, Float low, Float high) throws IllegalArgumentException {
		// TODO: Add user input handling
		Collection<Food> searchList = vals.get(key).subMap(low, high).values();
		FoodList subTree = new FoodList();
		subTree.insertAllFood(searchList.toArray(new Food[searchList.size()]));
		return subTree;
	}

	public Food[] getNameRange(String nameKey){
		ArrayList<Food> nameList = new ArrayList<Food>(name.values());
		System.out.println(nameList.toString());
		ArrayList<Food> finalNames = new ArrayList<>();
		for(Food item : nameList){
			if(item.getName().contains(nameKey)) finalNames.add(item);
		}

		return finalNames.toArray(new Food[finalNames.size()]);
	}


	/**
	 * Returns items in a given collection
	 * 
	 * @return
	 */
	public ArrayList<String> getNames() {
		Collection<Food> fList = name.values();
		ArrayList<String> names = new ArrayList<>();
		for (Food fitem : fList) {
			names.add(fitem.getName());
		}
		return names;
	}

	/**
	 * Returns all the given food items in the data structure
	 * 
	 * @return food array
	 */
	public Food[] getAll() {
		return name.values().toArray(new Food[name.values().size()]);
	}

	/**
	 * Private helper
	 * 
	 * Inserts item all of the tree maps within the hash map
	 * 
	 * @param item
	 */
	private void putVals(Food item) {
		name.put(item.getName(), item);
		vals.get("cal").put(item.getCal(), item);
		vals.get("protein").put(item.getProtein(), item);
		vals.get("carbs").put(item.getCarb(), item);
		vals.get("fat").put(item.getFat(), item);
		vals.get("fiber").put(item.getFiber(), item);
	}
}
