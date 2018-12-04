import java.util.ArrayList;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

public class FoodList {

    TreeMap<String, Food> name = new TreeMap<String, Food>();
    HashMap<String, TreeMap> vals = new HashMap<String, TreeMap>();

    public FoodList(){
        vals.put("cal", new TreeMap<String, Food>());
        vals.put("protein", new TreeMap<String, Food>());
        vals.put("carbs", new TreeMap<String, Food>());
        vals.put("fat", new TreeMap<String, Food>());
        vals.put("fiber", new TreeMap<String, Food>());
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
        return name.get(searchName);
    }

    public FoodList getFoodRange(String key, int high, int low){
        Food[] searchList = (Food[]) vals.get(key).subMap(high,low).values().toArray();
        FoodList subTree = new FoodList();
        subTree.insertAllFood(searchList);
        return subTree;
    }

    public ArrayList<String> getNames() {
        Food[] fullList = (Food[]) name.values().toArray();
        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i < fullList.length; i++) {
            names.add(fullList[i].getName());
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
