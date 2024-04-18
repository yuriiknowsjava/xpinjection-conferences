package edu.yuriiknowsjava.xpinjection.conferences.dev;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Profile("dev")
@Component
class DevModeWarning implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
        log.warn("You are running the application in \"dev\" mode!");
    }
}
