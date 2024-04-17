package edu.yuriiknowsjava.xpinjection.conferences.services;

import edu.yuriiknowsjava.xpinjection.conferences.entities.Theme;
import edu.yuriiknowsjava.xpinjection.conferences.repositories.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ThemeServiceImpl implements ThemeService {

    private final ThemeRepository themeRepository;

    @Autowired
    public ThemeServiceImpl(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    @Override
    public Page<Theme> getThemes(Pageable pageable) {
        return themeRepository.findAll(pageable);
    }
}
