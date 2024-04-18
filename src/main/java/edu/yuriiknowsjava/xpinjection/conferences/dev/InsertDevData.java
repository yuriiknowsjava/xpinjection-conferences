package edu.yuriiknowsjava.xpinjection.conferences.dev;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.function.Consumer;
import java.util.function.Function;

import edu.yuriiknowsjava.xpinjection.conferences.entities.Conference;
import edu.yuriiknowsjava.xpinjection.conferences.repositories.ConferenceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Profile("dev")
@Component
class InsertDevData implements CommandLineRunner {
    private final ConferenceRepository conferenceRepository;

    @Deprecated
    InsertDevData(ConferenceRepository conferenceRepository) {
        this.conferenceRepository = conferenceRepository;
    }

    @Override
    public void run(String... args) {
        Consumer<Object> doNothing = Function.identity()::apply;
        log.info("Inserting dev conference data into database for local development...");

        var xpInjection = "XP Injection";
        conferenceRepository.findByName(xpInjection)
                .ifPresentOrElse(
                        doNothing,
                        () -> conferenceRepository.save(
                                Conference.builder().name(xpInjection)
                                        .startDate(createDate(2025, 2, 1))
                                        .endDate(createDate(2025, 3, 1))
                                        .build()
                        ));

        var lvivJavaDay = "Lviv Java Day";
        conferenceRepository.findByName(lvivJavaDay)
                .ifPresentOrElse(
                        doNothing,
                        () -> conferenceRepository.save(
                                Conference.builder().name(lvivJavaDay)
                                        .startDate(createDate(2024, 5, 14))
                                        .endDate(createDate(2024, 5, 15))
                                        .build()
                        ));

        var devoxx = "Devoxx";
        conferenceRepository.findByName(devoxx)
                .ifPresentOrElse(
                        doNothing,
                        () -> conferenceRepository.save(
                                Conference.builder().name(devoxx)
                                        .startDate(createDate(2022, 7, 2))
                                        .endDate(createDate(2022, 8, 2))
                                        .build()
                        ));

        var fwdays = "Fwdays";
        conferenceRepository.findByName(fwdays)
                .ifPresentOrElse(
                        doNothing,
                        () -> conferenceRepository.save(
                                Conference.builder()
                                        .name(fwdays)
                                        .startDate(createDate(2022, 7, 2))
                                        .endDate(createDate(2022, 8, 2))
                                        .build()
                        )
                );
        log.info("Inserted dev conference data into database for local development.");
    }

    private ZonedDateTime createDate(int year, int month, int dayOfMonth) {
        var localDateTime = LocalDateTime.of(LocalDate.of(year, month, dayOfMonth), LocalTime.MIDNIGHT);
        return ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
    }
}
