package org.csu.petstore;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.csu.petstore.persistence") //在这个包下的所有类（和接口）都用Mybatis的方式生成一个mapper
public class PetStorePlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetStorePlusApplication.class, args);
    }

}
