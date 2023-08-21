Wrapper 的作用就是用於定義各種各樣的條件（where）。所以不管是查詢、更新、刪除都會用到Wrapper。

```java
@Test
    public void testSelect2(){
        LambdaQueryWrapper<Food> wrapper= new LambdaQueryWrapper<>();
        wrapper.like(Food::getAddress,"台南市");
        wrapper.orderByDesc(Food::getId);
        List<Food> food = foodMapper.selectList(wrapper);

        food.forEach(System.out::println);
    }
```
<img width="1227" alt="image" src="https://github.com/ziuqnnnn44/hellomybatis/assets/66659394/8077bf65-b18c-4739-9c76-9a56c3c5d5d1">
