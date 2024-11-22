package be.study.tennisBe.model.administrator;

import java.util.List;
import java.util.stream.Collectors;

import be.study.tennisBe.model.dto.CourtDto;
import jakarta.annotation.Generated;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "CourtTennis")
public class Court {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 45, unique = true)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, length = 45)
    private String description;

    @Column(nullable = false)
    private String imageUrl;

    @Column( nullable = false, length = 45)
    private double price;

    @Column(nullable = false, length = 45)
    private boolean isAvailable;


    public static Court from(CourtDto courtDto) {
        Court court = new Court();
        court.setId(courtDto.getId());
        court.setName(courtDto.getName());
        court.setAddress(courtDto.getAddress());
        court.setDescription(courtDto.getDescription());
        court.setImageUrl(courtDto.getImageUrl());
        court.setPrice(courtDto.getPrice());
        court.setAvailable(courtDto.isAvailable());
        return court;
    }
}
