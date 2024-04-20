package edu.yuriiknowsjava.xpinjection.conferences.repositories;

import edu.yuriiknowsjava.xpinjection.conferences.entities.Talk;
import org.springframework.data.repository.CrudRepository;

public interface TalkRepository extends CrudRepository<Talk, Long> {
}
