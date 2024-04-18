package edu.yuriiknowsjava.xpinjection.conferences.repositories;

import java.math.BigInteger;
import java.util.Set;

import edu.yuriiknowsjava.xpinjection.conferences.entities.Theme;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeRepository extends ListPagingAndSortingRepository<Theme, BigInteger> {

    Set<Theme> findByIdInOrTagIn(Set<BigInteger> ids, Set<String> names);
}
