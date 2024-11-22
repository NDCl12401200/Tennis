package be.study.tennisBe.model.administrator;

import be.study.tennisBe.model.dto.ReservationDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.*;
import java.time.format.DateTimeFormatter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDate date;

    @Column
    private LocalTime startTime;

    @Column
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "court_id")
    private Court court;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserTennis user;

    public static Reservation from(ReservationDto reservationDto) {
        Reservation reservation = new Reservation();
        reservation.setId(reservationDto.getId());
        reservation.setDate(LocalDate.parse(reservationDto.getDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        reservation.setStartTime(LocalTime.parse(reservationDto.getStartTime(), DateTimeFormatter.ofPattern("HH:mm")));
        reservation.setEndTime(LocalTime.parse(reservationDto.getEndTime(), DateTimeFormatter.ofPattern("HH:mm")));
        reservation.setCourt(reservationDto.getCourt());
        reservation.setUser(reservationDto.getUser());
        return reservation;
    }
}
