package edu.yuriiknowsjava.xpinjection.conferences;

import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@Component
public interface TestProfile {
}
