package edu.yuriiknowsjava.xpinjection.conferences.services;

import java.util.List;
import java.util.Set;

import edu.yuriiknowsjava.xpinjection.conferences.dto.ThemeAdditionDto;
import edu.yuriiknowsjava.xpinjection.conferences.dto.ThemeDto;
import edu.yuriiknowsjava.xpinjection.conferences.entities.Theme;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ThemeService {

    Page<ThemeDto> getThemes(Pageable pageable);

    Set<Theme> getThemes(List<ThemeAdditionDto> themeAdditions);
}
