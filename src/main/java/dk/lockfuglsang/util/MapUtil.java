package dk.lockfuglsang.util;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MapUtil {
    public static <K, V extends Comparable<? super V>> Map<K, V>
    sortByValue(Map<K, V> map, final Comparator<V> comparator) {
        List<Map.Entry<K, V>> list =
                new LinkedList<>(map.entrySet());
        list.sort((o1, o2) -> comparator.compare(o1.getValue(), o2.getValue()));

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}