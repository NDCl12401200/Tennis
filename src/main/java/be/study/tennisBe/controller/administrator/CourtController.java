package be.study.tennisBe.controller.administrator;

import be.study.tennisBe.model.administrator.Court;
import be.study.tennisBe.model.dto.CourtDto;
import be.study.tennisBe.service.administrator.CourtService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "bearerAuth")
public class CourtController {

    private final CourtService courtService;

    @Autowired
    public CourtController (CourtService courtService) {
        this.courtService = courtService;
    }
     @GetMapping("/courts")
    public ResponseEntity<List<CourtDto>> getCourts(){
        List<Court> court = courtService.getCourts();
        List<CourtDto> courtDto = court.stream().map(CourtDto::from).toList();
        return new ResponseEntity<>(courtDto, HttpStatus.OK);
     }

     @PostMapping("/court")
    public ResponseEntity<CourtDto> postCourt (@RequestBody final CourtDto courtDto) {
        Court court = courtService.addCourt(Court.from(courtDto));
        return new ResponseEntity<>(CourtDto.from(court), HttpStatus.CREATED);
     }

     @PutMapping("/Court/edit/{id}")
    public ResponseEntity<CourtDto> editCourt(@PathVariable Long id, @RequestBody CourtDto courtDto) {
        Court court = courtService.editCourt(id, Court.from(courtDto));
        return new ResponseEntity<>(CourtDto.from(court), HttpStatus.ACCEPTED);
     }

     @DeleteMapping("/Court/delete/{id}")
    public ResponseEntity<CourtDto> deleteCourt(@PathVariable Long id) {
        Court court = courtService.deleteCourt(id);
        return new ResponseEntity<>(CourtDto.from(court), HttpStatus.ACCEPTED);
     }
}
