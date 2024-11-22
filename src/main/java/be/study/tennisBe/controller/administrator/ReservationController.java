package be.study.tennisBe.controller.administrator;

import be.study.tennisBe.model.administrator.Reservation;
import be.study.tennisBe.model.dto.ReservationDto;
import be.study.tennisBe.service.administrator.ReservationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "bearerAuth")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<ReservationDto>> getReservations() {
        List<Reservation> reservations = reservationService.getReservations();
        List<ReservationDto> reservationsDto = reservations.stream().map(ReservationDto::from).toList();
        return new ResponseEntity<>(reservationsDto, HttpStatus.OK);
    }

    @PutMapping("/reservation/{id}")
    public ResponseEntity<ReservationDto> editReservation(@RequestBody final ReservationDto reservationDto,
            @PathVariable final Long id) {
        Reservation reservation = reservationService.editReservation(id, Reservation.from(reservationDto));
        return new ResponseEntity<>(ReservationDto.from(reservation), HttpStatus.CREATED);
    }

    @DeleteMapping("/reservation/{id}")
    public ResponseEntity<ReservationDto> deleteReservation(@PathVariable final Long id) {
        Reservation reservation = reservationService.deleteReservation(id);
        return new ResponseEntity<>(ReservationDto.from(reservation), HttpStatus.ACCEPTED);
    }

    @PostMapping("/reservation")
    public ResponseEntity<ReservationDto> addReservation(@RequestBody final ReservationDto reservationDto) {
        Reservation reservation = reservationService.addReservationToUSerAndCourt(Reservation.from(reservationDto));
        return new ResponseEntity<>(ReservationDto.from(reservation), HttpStatus.OK);
    }

}
