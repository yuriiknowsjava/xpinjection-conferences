package edu.yuriiknowsjava.xpinjection.conferences.services;

import edu.yuriiknowsjava.xpinjection.conferences.dto.ThemeDto;
import edu.yuriiknowsjava.xpinjection.conferences.entities.Theme;
import edu.yuriiknowsjava.xpinjection.conferences.mappers.ThemeMapper;
import edu.yuriiknowsjava.xpinjection.conferences.repositories.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ThemeServiceImpl implements ThemeService {

    private final ThemeRepository themeRepository;

    @Autowired
    public ThemeServiceImpl(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    @Transactional
    @Override
    public Page<ThemeDto> getThemes(Pageable pageable) {
        Page<Theme> themes = themeRepository.findAll(pageable);
        return themes.map(ThemeMapper::toDto);
    }
}
