package be.study.tennisBe.model.dto;

import be.study.tennisBe.model.administrator.Court;
import be.study.tennisBe.model.administrator.Reservation;
import be.study.tennisBe.model.administrator.UserTennis;
import lombok.Data;

import java.time.format.DateTimeFormatter;

@Data
public class ReservationDto {

    private Long id;
    private String date;
    private String startTime;
    private String endTime;
    private Court court;
    private UserTennis user;

    public static ReservationDto from(Reservation reservation) {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId(reservation.getId());
        reservationDto.setDate(reservation.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        reservationDto.setStartTime(reservation.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm")));
        reservationDto.setEndTime(reservation.getEndTime().format(DateTimeFormatter.ofPattern("HH:mm")));
        reservationDto.setCourt(reservation.getCourt());
        reservationDto.setUser(reservation.getUser());
        return reservationDto;
    }
}
