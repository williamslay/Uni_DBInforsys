package com.wu.messagepush;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.wu.messagepush.mapper")
public class MessagePushApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(MessagePushApplication.class);
        springApplication.setAllowCircularReferences(Boolean.TRUE);
        springApplication.run(args);
    }

}
