package edu.yuriiknowsjava.xpinjection.conferences.configurations;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@ConfigurationProperties(prefix = "edu.yuriiknowsjava.xpinjection.conferences")
public class ConferencesConfiguration {
    /**
     *  Be careful when passing in many values. The JDBC standard does not guarantee that you can use more than 100 values for an in expression list.
     *  Various databases exceed this number, but they usually have a hard limit for how many values are allowed. For example, Oracle’s limit is 1000.
     *  For more details,
     *  see <a href="https://docs.spring.io/spring-framework/reference/data-access/jdbc/parameter-handling.html#jdbc-in-clause">
     *      Passing in Lists of Values for IN Clause</a>
     */
    @Min(1)
    private int maxConferenceThemes = 100;
}
