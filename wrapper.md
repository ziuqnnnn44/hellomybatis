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

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/0f474a0a-941e-4ec3-93c0-6b78bcb65d4a/Untitled.png)
