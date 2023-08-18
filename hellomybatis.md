遇到問題

問題一

****@SpringBootTest Canot resolve symbol ‘SpringBootTest‘****

****@SpringBoot註解不存在****

解決方法

1.查看pom.xml，有

2.import org.springframework.boot.test.context.SpringBootTest，灰色

3.查看目錄 —>**測試例子寫在了/src/main/java/目錄下，所以無法引用spring-boot-starter-test**

問題二

**Caused by: java.lang.IllegalArgumentException: Property 'sqlSessionFactory' or 'sqlSessionTemplate' are required**

解決方法

升級版本—>**使用 Spring Boot 3 版本整合 MyBatis 時，要把 mybatis-spring-boot-starter 也升級**

資料庫 表

```sql
CREATE TABLE `food` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `address` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `comment` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Data for table `employee`food
--

INSERT INTO `food` VALUES 
	(1,'俺の豚','710台南市永康區東橋一路250號','人氣很高的拉麵店，桂花紅茶冰沙好喝'),
	(2,'當月麵店','701台南市東區長榮路三段66巷23號','豬腳麵跟雞腿麵都不錯，買飲料可以一直續杯喔'),
	(3,'禾品平價鐵板燒','701台南市東區光明街203號','鹽酥雞腿排、蔥爆牛肉都不錯，菜量也很多，還有小美冰淇淋好吃'),
	(4,'泰典2527泰式料理','701台南市東區裕農路383號','凱焦蓋飯是指蛋包飯，蛋很香有加紅蘿蔔、碎肉以及洋蔥，裡面是白飯喔。'),
	(5,'好食殿食堂','好食殿食堂','鯖魚丼跟照燒雞腿丼都很好吃，照燒醬有甜可以接受');
```

pom

```sql
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.1.2</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.example</groupId>
	<artifactId>demo</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>demo</name>
	<description>Demo project for Spring Boot</description>
	<properties>
		<java.version>17</java.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

    <dependency>
       <groupId>com.baomidou</groupId>
       <artifactId>mybatis-plus-boot-starter</artifactId>
       <version>3.5.3.2</version>
    </dependency>
        

		
		<dependency>
			<groupId>com.mysql</groupId>
			<artifactId>mysql-connector-j</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
        </dependency>
		
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<excludes>
						<exclude>
							<groupId>org.projectlombok</groupId>
							<artifactId>lombok</artifactId>
						</exclude>
					</excludes>
				</configuration>
			</plugin>
		</plugins>
	</build>

</project>
```

application.properties

```html
spring.datasource.url=jdbc:mysql://localhost:3306/food
spring.datasource.username=springstudent
spring.datasource.password=springstudent

logging.level.com.example.demo=debug
```

emtity

```java
package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Food {
	
	private int id;
	

	private String name;
	

	private String address;
	

	private String comment;

}
```

mapper

```java
package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Food;

public interface FoodMapper extends BaseMapper<Food>{

}
```

test

```java
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
```
