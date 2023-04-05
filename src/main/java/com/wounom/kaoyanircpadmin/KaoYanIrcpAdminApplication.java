package com.wounom.kaoyanircpadmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableTransactionManagement
@EnableOpenApi
@MapperScan("com.wounom.kaoyanircpadmin.dao")
public class KaoYanIrcpAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(KaoYanIrcpAdminApplication.class, args);
    }

}
