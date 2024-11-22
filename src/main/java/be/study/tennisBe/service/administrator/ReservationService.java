package be.study.tennisBe.service.administrator;

import be.study.tennisBe.exceptions.IdNotFoundException;
import be.study.tennisBe.model.administrator.Reservation;
import be.study.tennisBe.model.administrator.UserTennis;
import be.study.tennisBe.repository.administrator.ReservationRepository;

import jakarta.transaction.Transactional;
import org.apache.el.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserTennisService userTennisService;
    private final CourtService courtService;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, UserTennisService userTennisService,
            CourtService courtService) {
        this.reservationRepository = reservationRepository;
        this.userTennisService = userTennisService;
        this.courtService = courtService;
    }

    public List<Reservation> getReservations() {
        return StreamSupport.stream(reservationRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Reservation addReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElseThrow(() -> new IdNotFoundException(id, "reservation"));
    }

    public Reservation deleteReservation(Long id) {
        Reservation reservation = getReservationById(id);
        reservationRepository.delete(reservation);
        return reservation;

    }

    public Reservation editReservation(Long id, Reservation reservation) {
        Reservation reservationToEdit = getReservationById(id);
        reservationToEdit.setEndTime(reservation.getEndTime());
        reservationToEdit.setDate(reservation.getDate());
        reservationToEdit.setStartTime(reservation.getStartTime());
        reservationToEdit.setCourt(courtService.getCourtById(reservation.getCourt().getId()));
        reservationToEdit.setUser(userTennisService.getUserTennisById(reservation.getUser().getId()));
        reservationRepository.save(reservationToEdit);
        return reservationToEdit;
    }

    @Transactional
    public Reservation addReservationToUSerAndCourt(Reservation reservation) {
        Reservation reservation1 = addReservation(reservation);
        reservation1.setUser(userTennisService.getUserTennisById(reservation.getUser().getId()));
        reservation1.setCourt(courtService.getCourtById(reservation.getCourt().getId()));
        return reservation1;
    }

}
