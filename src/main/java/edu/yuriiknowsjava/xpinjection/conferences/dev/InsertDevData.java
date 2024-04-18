package edu.yuriiknowsjava.xpinjection.conferences.dev;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

import edu.yuriiknowsjava.xpinjection.conferences.entities.Conference;
import edu.yuriiknowsjava.xpinjection.conferences.entities.Theme;
import edu.yuriiknowsjava.xpinjection.conferences.repositories.ConferenceRepository;
import edu.yuriiknowsjava.xpinjection.conferences.repositories.ThemeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Slf4j
@Profile("dev")
@Component
class InsertDevData implements CommandLineRunner {
    private final ConferenceRepository conferenceRepository;
    private final ThemeRepository themeRepository;

    @Transactional
    @Override
    public void run(String... args) {
        Consumer<Object> doNothing = Function.identity()::apply;
        log.info("Inserting dev conference data into database for local development...");
        List<Theme> defaultThemes = themeRepository.findAll(Sort.by("id").ascending());
        Theme java = getThemeByName(defaultThemes, "java");
        Theme springBoot = getThemeByName(defaultThemes, "spring boot");
        Theme maven = getThemeByName(defaultThemes, "maven");
        Theme kubernetes = getThemeByName(defaultThemes, "kubernetes");
        Theme react = getThemeByName(defaultThemes, "react");
        Theme nodejs = getThemeByName(defaultThemes, "nodejs");

        var xpInjectionStr = "XP Injection";
        Conference xpInjection = Conference.builder().name(xpInjectionStr)
                .startDate(createDate(2025, 2, 1))
                .endDate(createDate(2025, 3, 1))
                .build();
        conferenceRepository.findByName(xpInjectionStr)
                .ifPresentOrElse(
                        doNothing,
                        () -> {
                            Stream.of(java, springBoot, kubernetes).forEach(xpInjection::addTheme);
                            conferenceRepository.save(xpInjection);
                        }
                );

        var lvivJavaDayStr = "Lviv Java Day";
        Conference lvivJavaDay = Conference.builder().name(lvivJavaDayStr)
                .startDate(createDate(2024, 5, 14))
                .endDate(createDate(2024, 5, 15))
                .build();
        conferenceRepository.findByName(lvivJavaDayStr)
                .ifPresentOrElse(
                        doNothing,
                        () -> {
                            Stream.of(java, springBoot, maven).forEach(lvivJavaDay::addTheme);
                            conferenceRepository.save(lvivJavaDay);
                        }
                );

        var devoxxStr = "Devoxx";
        Conference devoxx = Conference.builder().name(devoxxStr)
                .startDate(createDate(2022, 7, 2))
                .endDate(createDate(2022, 8, 2))
                .build();
        conferenceRepository.findByName(devoxxStr)
                .ifPresentOrElse(
                        doNothing,
                        () -> {
                            Stream.of(java, springBoot, maven, kubernetes).forEach(devoxx::addTheme);
                            conferenceRepository.save(devoxx);
                        });

        var fwdaysStr = "Fwdays";
        Conference fwdays = Conference.builder()
                .name(fwdaysStr)
                .startDate(createDate(2022, 7, 2))
                .endDate(createDate(2022, 8, 2))
                .build();
        conferenceRepository.findByName(fwdaysStr)
                .ifPresentOrElse(
                        doNothing,
                        () -> {
                            Stream.of(react, nodejs).forEach(fwdays::addTheme);
                            conferenceRepository.save(fwdays);
                        });
        log.info("Inserted dev conference data into database for local development.");
    }

    private static @NotNull Theme getThemeByName(List<Theme> defaultThemes, String tag) {
        return defaultThemes.stream()
                .filter(theme -> theme.getTag().equalsIgnoreCase(tag))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No theme found for " + tag));
    }

    private ZonedDateTime createDate(int year, int month, int dayOfMonth) {
        var localDateTime = LocalDateTime.of(LocalDate.of(year, month, dayOfMonth), LocalTime.MIDNIGHT);
        return ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
    }
}
