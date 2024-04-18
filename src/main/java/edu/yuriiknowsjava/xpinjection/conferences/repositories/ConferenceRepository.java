package edu.yuriiknowsjava.xpinjection.conferences.repositories;

import java.math.BigInteger;
import java.util.Optional;

import edu.yuriiknowsjava.xpinjection.conferences.entities.Conference;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConferenceRepository extends CrudRepository<Conference, BigInteger>,
        ListPagingAndSortingRepository<Conference, BigInteger> {

    Optional<Conference> findByName(String name);

    boolean existsByName(String name);
}
