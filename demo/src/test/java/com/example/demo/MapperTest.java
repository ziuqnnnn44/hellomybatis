package com.example.demo;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Food;
import com.example.demo.mapper.FoodMapper;

@SpringBootTest
public class MapperTest {

	
	
    @Autowired
    private FoodMapper foodMapper;
    
    @Test
    public void testGetAll() {
        List<Food> foodList = foodMapper.selectList(null);
        foodList.forEach(System.out::println);
    }
    
    @Test
    public void deleteFood() {
    	foodMapper.deleteById(5);
    }



    @Test
    public void addFood(){
        Food food = new Food(5,"好食殿食堂","701台南市東區長榮路三段30巷22號","鯖魚丼跟照燒雞腿丼都很好吃，照燒醬有甜可以接受");
        foodMapper.insert(food);
    }

    @Test
    public void updateFood(){
        Food food = new Food(5,"2","11","111");
        foodMapper.updateById(food);
    }

}
