package stream;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName E04LambdaInMap
 * @Description //TODO
 * @Author ccy
 * @Date 2020/12/11 14:50
 * @Version 1.0
 **/
public class E04LambdaInMap {
    public static void main(String[] args){
        HashMap<String, String> map = new HashMap<>();
        map.put("jack", "20");
        map.put("cill", "30");
        map.put("tide", "20");
        map.put("jack2", "40");

        //获取key：统计年龄为20的所有名字
        getKOfMap(map);

        //获取value：统计名字里包含jack的所有年龄
        getVOfMap(map);

    }

    private static void getVOfMap(HashMap<String, String> map) {
        List<Object> agesInJack = map.entrySet()
                .stream()
                .filter(e -> e.getKey().contains("jack"))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
        //[40, 20]
        System.out.println(agesInJack);
    }

    private static void getKOfMap(HashMap<String, String> map) {
        List<String> namesIn20 = map.entrySet()
                .stream()
                .filter(e -> "20".equals(e.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        //[tide, jack]
        System.out.println(namesIn20);
    }

}
