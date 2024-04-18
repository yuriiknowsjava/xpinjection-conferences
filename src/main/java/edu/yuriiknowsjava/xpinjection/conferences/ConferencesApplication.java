package edu.yuriiknowsjava.xpinjection.conferences;

import edu.yuriiknowsjava.xpinjection.conferences.configurations.ConferencesConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(ConferencesConfiguration.class)
@SpringBootApplication
public class ConferencesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConferencesApplication.class, args);
    }
}
