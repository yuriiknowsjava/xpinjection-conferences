package edu.yuriiknowsjava.xpinjection.conferences.repositories;

import edu.yuriiknowsjava.xpinjection.conferences.entities.Talk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface TalkRepository extends CrudRepository<Talk, Long>, ListPagingAndSortingRepository<Talk, Long> {

    Page<Talk> findByConferenceId(Long id, Pageable pageable);

    boolean existsByConferenceIdAndTitle(Long id, String title);
}
