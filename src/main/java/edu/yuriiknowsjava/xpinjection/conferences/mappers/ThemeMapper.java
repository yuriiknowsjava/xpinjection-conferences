package edu.yuriiknowsjava.xpinjection.conferences.mappers;

import edu.yuriiknowsjava.xpinjection.conferences.dto.ThemeDto;
import edu.yuriiknowsjava.xpinjection.conferences.entities.Theme;

public class ThemeMapper {

    public static ThemeDto toDto(Theme theme) {
        return new ThemeDto(
                theme.getId(),
                theme.getTag(),
                theme.getDescription()
        );
    }
}
