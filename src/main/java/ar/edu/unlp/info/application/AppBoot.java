package ar.edu.unlp.info.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;


@SpringBootApplication (scanBasePackages = { "ar.edu.unlp.info"})
public class AppBoot {


    public static void main(String[] args) throws IOException {
        System.out.println("Working Directory = " +
                System.getProperty("user.dir"));
        SpringApplication.run(AppBoot.class, args);


    }
}
