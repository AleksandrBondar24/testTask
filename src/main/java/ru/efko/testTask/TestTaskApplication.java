package ru.efko.testTask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.efko.testTask.service.ExelReaderService;

import java.io.IOException;

@SpringBootApplication
public class TestTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestTaskApplication.class, args);
        ExelReaderService service = new ExelReaderService();
        service.readFile("C:/Users/user/Downloads/Тестовое задание Java/Тестовое задание/data.xlsx");
    }
}
