import java.util.HashMap;
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



}
