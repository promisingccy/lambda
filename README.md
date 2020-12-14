# lambda
stream lambda usage demos

##### GetStream

/************* 通过 .stream 获取 *************/
String[] strings = {"a", "b", "c"};
Stream<String> stream = Arrays.stream(strings);

/************* 通过 .stream 获取 *************/
Stream<String> stream1 = Stream.of("a", "b", "c");

/************* stream 多线程 *************/
List<String> list = Arrays.asList("a", "b", "c");
//多线程输出 顺序不保证  b c a
list.parallelStream().forEach(e-> System.out.println(e));

/************* stream 与 list 互转 *************/
List<String> listFromStream = stream.collect(Collectors.toList());
//[a, b, c]
System.out.println(listFromStream);

##### StreamBase
/**
 * stream的三个组成：数据源/中间操作终止操作。
 *
 * 中间操作是对数据的加工，是lazy操作，
 * 并不会立马启动，而是等待终止操作才会执行!
 *
 * @数据源
 * @中间操作方法：
 * distinct 去重->new Stream
 * filter 过滤->new Stream
 * map 再加工->new Stream
 * flatMap 平铺层级关系->new Stream
 * ----------------
 * @终止操作方法：
 * count 计数->int
 * anyMatch 只要有一个为真 则为真->bool
 * allMatch 全部为真 则为真->bool
 * noneMatch 都不为真 则为真->bool
 * reduce  初始值，遍历算法->int
 * collect 将stream转为集合
 */

##### LambdaInMap
HashMap<String, String> map = new HashMap<>();
map.put("jack", "20");
map.put("cill", "30");
map.put("tide", "20");
map.put("jack2", "40");

List<Object> agesInJack = map.entrySet()
        .stream()
        .filter(e -> e.getKey().contains("jack"))
        .map(Map.Entry::getValue)
        .collect(Collectors.toList());
//[40, 20]
System.out.println(agesInJack);

List<String> namesIn20 = map.entrySet()
        .stream()
        .filter(e -> "20".equals(e.getValue()))
        .map(Map.Entry::getKey)
        .collect(Collectors.toList());
//[tide, jack]
System.out.println(namesIn20);

##### StreamPeek
/**
 * peek主要用于debug 输出遍历流程中的数据状态；
 * 如果是基本类型，peek不会改变返回结果；
 * 如果是对象，peek改变了其中属性则会影响返回结果
 */

// filter:three
// map:THREE
// filter:four
// map:FOUR
List<String> list = Stream.of("one", "two", "three", "four")
        .filter(e -> e.length() > 3)
        .peek(e -> System.out.println("filter:" + e))
        .map(String::toUpperCase)
        .peek(e -> System.out.println("map:" + e))
        .collect(Collectors.toList());
//[THREE, FOUR]
System.out.println(list);


##### StreamCollectUsage

List<String> list = Arrays.asList("one", "two", "three", "four");
List<String> listWithCouple = Arrays.asList("one", "one", "three", "four");

Map<Boolean, List<String>> lengthWithGroupBy = list.stream().collect(Collectors.partitioningBy(s -> s.length() > 3));
//{false=[one, two], true=[three, four]}
System.out.println(lengthWithGroupBy);

Map<Integer, Set<String>> collectGroup = list.stream().collect(Collectors.groupingBy(String::length,
        Collectors.toSet()));
//{3=[one, two], 4=[four], 5=[three]}
System.out.println(collectGroup);

IntSummaryStatistics lengthRes = list.stream().collect(Collectors.summarizingInt(String::length));
//IntSummaryStatistics{count=4, sum=15, min=3, average=3.750000, max=5}
System.out.println(lengthRes);

String joinStr = list.stream().collect(Collectors.joining(" ","ccy_","_ccy"));
//ccy_one two three four_ccy
System.out.println(joinStr);

ArrayList<String> newCollectList = list.stream().collect(Collectors.collectingAndThen(Collectors.toList(), l -> {
    return new ArrayList<>(l);
}));
System.out.println(newCollectList);

Map<String, Integer> collect = listWithCouple.stream().collect(Collectors.toMap(Function.identity(), String::length, (a, b) -> a));
//{four=4, one=3, three=5}
System.out.println(collect);

Map<String, Integer> mapFromStream = list.stream().collect(Collectors.toMap(Function.identity(), String::length));
//{four=4, one=3, three=5, two=3}
System.out.println(mapFromStream);

Set<String> setFromStream =
        listWithCouple.stream().map(String::toUpperCase).collect(Collectors.toCollection(TreeSet::new));
//[FOUR, ONE, THREE]
System.out.println(setFromStream);

Set<String> setFromStream = listWithCouple.stream().map(String::toUpperCase).collect(Collectors.toSet());
//[ONE, FOUR, THREE]
System.out.println(setFromStream);

List<String> listFromStream = list.stream().map(String::toUpperCase).collect(Collectors.toList());
//[ONE, TWO, THREE, FOUR]
System.out.println(listFromStream);

