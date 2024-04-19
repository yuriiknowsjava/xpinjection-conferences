package edu.yuriiknowsjava.xpinjection.conferences.services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import edu.yuriiknowsjava.xpinjection.conferences.dto.ThemeAdditionDto;
import edu.yuriiknowsjava.xpinjection.conferences.dto.ThemeDto;
import edu.yuriiknowsjava.xpinjection.conferences.entities.Theme;
import edu.yuriiknowsjava.xpinjection.conferences.mappers.ThemeMapper;
import edu.yuriiknowsjava.xpinjection.conferences.repositories.ThemeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class ThemeServiceImpl implements ThemeService {

    private final ThemeRepository themeRepository;

    @Transactional
    @Override
    public Page<ThemeDto> getThemes(Pageable pageable) {
        Page<Theme> themes = themeRepository.findAll(pageable);
        return themes.map(ThemeMapper::toDto);
    }

    @Override
    public Set<Theme> getThemes(List<ThemeAdditionDto> themeAdditions) {
        Set<Long> ids = themeAdditions.stream().map(ThemeAdditionDto::id).collect(Collectors.toSet());
        Set<String> tags = themeAdditions.stream().map(ThemeAdditionDto::tag).collect(Collectors.toSet());
        return themeRepository.findByIdInOrTagIn(ids, tags);
    }
}
