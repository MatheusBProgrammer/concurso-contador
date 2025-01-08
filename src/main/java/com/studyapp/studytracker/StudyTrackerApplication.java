package com.studyapp.studytracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = "com.studyapp.studytracker")
@EnableMongoRepositories(basePackages = "com.studyapp.studytracker.repository")

public class StudyTrackerApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudyTrackerApplication.class, args);
    }
}
