package be.study.tennisBe.model.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import be.study.tennisBe.model.administrator.Court;
import lombok.Data;

@Data
public class CourtDto {

    private Long id;
    private String name;
    private String address;
    private String description;
    private String imageUrl;
    private double price;
    private boolean isAvailable;

    public static CourtDto from(Court court) {
        CourtDto courtDto = new CourtDto();
        courtDto.setId(court.getId());
        courtDto.setName(court.getName());
        courtDto.setAddress(court.getAddress());
        courtDto.setDescription(court.getDescription());
        courtDto.setImageUrl(court.getImageUrl());
        courtDto.setPrice(court.getPrice());
        courtDto.setAvailable(court.isAvailable());
        return courtDto;
    }
}
