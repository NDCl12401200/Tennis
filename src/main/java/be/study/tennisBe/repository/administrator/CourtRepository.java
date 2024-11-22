package be.study.tennisBe.repository.administrator;

import be.study.tennisBe.model.administrator.Court;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CourtRepository extends CrudRepository<Court, Long> {

    Court findByName(final String name);
}
