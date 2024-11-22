package be.study.tennisBe.repository.administrator;

import be.study.tennisBe.model.administrator.Reservation;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
   // Reservation findById(Long id);
}
