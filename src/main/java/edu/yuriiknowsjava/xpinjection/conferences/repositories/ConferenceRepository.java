package edu.yuriiknowsjava.xpinjection.conferences.repositories;

import java.math.BigInteger;
import java.util.Optional;

import edu.yuriiknowsjava.xpinjection.conferences.entities.Conference;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConferenceRepository extends JpaRepository<Conference, BigInteger>,
        ListPagingAndSortingRepository<Conference, BigInteger> {

    Optional<Conference> findByName(String name);

    boolean existsByName(String name);

    @EntityGraph("Conference.themes")
    @NotNull
    @Override
    Page<Conference> findAll(@NotNull Pageable pageable);
}
