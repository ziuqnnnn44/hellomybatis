問題

check the manual that corresponds to your [MySQL](https://activity.huaweicloud.com/dbs_Promotion/index.html?utm_source=hwc-csdn&utm_medium=share-op&utm_campaign=&utm_content=&utm_term=&utm_adplace=AdPlace070851) server version for the right syntax

關於SQL錯誤

解決方法

**修改資料庫表中衝突名稱，這裡我原本用delete，後來改為deleted，修改為和 SQL語句關鍵字不衝突的其他名稱**

@TableLogic 邏輯刪除

```java
@Test
    public void testGetAll() {
        List<Food> foodList = foodMapper.selectList(null);
        foodList.forEach(System.out::println);
    }
```

==> Preparing: SELECT id,name,address,comment,deleted FROM food WHERE deleted=0

```java
@Test
    public void deleteFood() {
        foodMapper.deleteById(5);
    }
```

==> Preparing: UPDATE food SET deleted=1 WHERE id=? AND deleted=0

@Version

```java
@Test
    public void testLockA(){
        Food food = foodmapper.selectById(4);
        food.setBalance(food.getBalance()+20);
        foodmapper.updateById(food);
    }
```

: ==>  Preparing: UPDATE food SET name=?, address=?, comment=?, version=?, balance=? WHERE id=? AND version=? AND deleted=0

c.e.demo.mapper.FoodMapper.updateById    : ==> Parametrs: 2(Integer), 120(Integer), 4(Integer), 1(Integer)

c.e.demo.mapper.FoodMapper.updateById    : <==    Updates: 1

```java
@Test
    public void testLockB(){
        Food food = foodmapper.selectById(4);
        food.setBalance(food.getBalance()+30);
        foodmapper.updateById(food);
    }
```

c.e.demo.mapper.FoodMapper.updateById    : ==>  Preparing: UPDATE food SET name=?, address=?, comment=?, version=?, balance=? WHERE id=? AND version=? AND deleted=0

c.e.demo.mapper.FoodMapper.updateById    : ==> Parameters: (String), 3(Integer), 150(Integer), 4(Integer), 2(Integer)

c.e.demo.mapper.FoodMapper.updateById    : <==    Updates: 1
