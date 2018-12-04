import java.util.*;

public class FoodList {

    TreeMap<String, Food> name = new TreeMap<String, Food>();
    HashMap<String, TreeMap> vals = new HashMap<String, TreeMap>();

    public FoodList(){
        vals.put("cal", new TreeMap<Float, Food>());
        vals.put("protein", new TreeMap<Float, Food>());
        vals.put("carbs", new TreeMap<Float, Food>());
        vals.put("fat", new TreeMap<Float, Food>());
        vals.put("fiber", new TreeMap<Float, Food>());
    }

    public void insertFood(Food item){
        putVals(item);
    }

    public void insertAllFood(Food[] items){
        for (Food item : items) {
            putVals(item);
        }
    }

    public Food getFood(String searchName){
        return name.getOrDefault(searchName, null);
    }

    public FoodList getFoodRange(String key, Float high, Float low){
        Collection<Food> searchList = vals.get(key).subMap(high,low).values();
        FoodList subTree = new FoodList();
        subTree.insertAllFood(searchList.toArray(new Food[searchList.size()]));
        return subTree;
    }

    public ArrayList<String> getNames() {
        Collection<Food> fList = name.values();
        ArrayList<String> names = new ArrayList<>();
        for (Food fitem : fList) {
            names.add(fitem.getName());
        }
        return names;
    }

    private void putVals(Food item) {
        name.put(item.getName(), item);
        vals.get("cal").put(item.getCal(), item);
        vals.get("protein").put(item.getProtein(), item);
        vals.get("carbs").put(item.getCarb(), item);
        vals.get("fat").put(item.getFat(), item);
        vals.get("fiber").put(item.getFiber(), item);
    }


}
