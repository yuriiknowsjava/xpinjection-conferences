package edu.yuriiknowsjava.xpinjection.conferences.services;

import edu.yuriiknowsjava.xpinjection.conferences.entities.Theme;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ThemeService {

    Page<Theme> getThemes(Pageable pageable);
}
