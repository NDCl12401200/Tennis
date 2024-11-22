package be.study.tennisBe.service.administrator;

import be.study.tennisBe.exceptions.IdNotFoundException;
import be.study.tennisBe.model.administrator.Court;
import be.study.tennisBe.repository.administrator.CourtRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CourtService {

    private final CourtRepository courtRepository;

    @Autowired
    public CourtService(CourtRepository courtRepository) {
        this.courtRepository = courtRepository;
    }

    public List<Court> getCourts () {
        return StreamSupport.stream(courtRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Court addCourt(Court court) {
        return courtRepository.save(court);
    }

    public Court getCourtById(Long id) {
        return courtRepository.findById(id).orElseThrow(() -> new IdNotFoundException(id, "court"));
    }

    public Court deleteCourt(Long id) {
        Court court = getCourtById(id);
        courtRepository.delete(court);
        return court;
    }

    public Court getCourtByName(String name) {
        return courtRepository.findByName(name);
    }

    public Court editCourt (Long id, Court court) {
        Court courtToEdit = getCourtById(id);
        courtToEdit.setName(court.getName());
        courtToEdit.setAddress(court.getAddress());
        courtToEdit.setDescription(court.getDescription());
        courtToEdit.setImageUrl(court.getImageUrl());
        courtToEdit.setPrice(court.getPrice());
        courtToEdit.setAvailable(court.isAvailable());
        courtRepository.save(courtToEdit);
        return courtToEdit;
    }

}
