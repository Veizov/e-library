package bg.tu.sofia.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by denislav.veizov on 7.5.2018 г..
 */
public class SorterUtils {

    public static Map<String, String> bookSorterColumnMap() {
        Map<String, String> map = new HashMap<>();
        map.put("title", "l.title");
        map.put("category", "l.category.name");
        map.put("year", "l.year");
        return map;
    }

}
