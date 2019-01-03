package com.datastructure.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication


public class DemoApplication{



    public static void main(String[] args) {

        String str = "sywuhduwqhdioqhdihwiudhuwhduwqhduwqhduqwhduqhdioqhdioqhiodhqwoidhioqwhdioqhdiqhwdohqwoihdoiqwhdioqhdioqwhdiquwdubcnxmaskaiehdxbhwjshsush";
        String revstr = new StringBuffer(str).reverse().toString();
        System.out.println(revstr);
            SpringApplication.run(DemoApplication.class,args);


        System.out.println(getValue(2));

    }

    public static int getValue(int i) {
        int result = 0;
        switch (i) {
            case 1:
                result = result + i;
            case 2:
                result = result + i * 2;
            case 3:
                result = result + i * 3;
        }
        return result;
    }
}