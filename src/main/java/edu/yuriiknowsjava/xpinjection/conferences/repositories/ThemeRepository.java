package edu.yuriiknowsjava.xpinjection.conferences.repositories;

import java.math.BigInteger;

import edu.yuriiknowsjava.xpinjection.conferences.entities.Theme;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeRepository extends ListPagingAndSortingRepository<Theme, BigInteger> {
}
