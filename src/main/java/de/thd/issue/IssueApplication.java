package de.thd.issue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(
        basePackageClasses = {IssueApplication.class}
)
@SpringBootApplication
public class IssueApplication {

    public static void main(String[] args) {
        SpringApplication.run(IssueApplication.class, args);
    }
}
