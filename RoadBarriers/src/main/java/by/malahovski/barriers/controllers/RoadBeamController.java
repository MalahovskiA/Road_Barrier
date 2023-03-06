package by.malahovski.barriers.controllers;

import by.malahovski.barriers.models.barriers.roadBarrierKit.RoadBeam;
import by.malahovski.barriers.responseUserHelper.MessageResponse;
import by.malahovski.barriers.service.RoadBeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/beam")
public class RoadBeamController {

    private final RoadBeamService roadBeamService;

    @Autowired
    public RoadBeamController(RoadBeamService roadBeamService) {
        this.roadBeamService = roadBeamService;
    }

    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(roadBeamService.deleteBeamById(id));
    }

    @GetMapping(value = "/save/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<?> saveBeam(@PathVariable Long id, @RequestBody RoadBeam roadBeam) {
        roadBeamService.saveBeam(id, roadBeam);
        return ResponseEntity.ok(new MessageResponse("Beam SAVED"));
    }

    @GetMapping(value = "/name/{name}")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<RoadBeam> getBeamByName(@PathVariable String name) {
        return ResponseEntity.ok(roadBeamService.getByName(name));
    }

    @GetMapping(value = "/length/{length}")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<List<RoadBeam>> getBeamsByLength(@PathVariable Integer length) {
        return ResponseEntity.ok(roadBeamService.getByLength(length));
    }

    @GetMapping(value = "/pitch/{pitch}")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<List<RoadBeam>> getBeamsByPitch(@PathVariable Integer pitch) {
        return ResponseEntity.ok(roadBeamService.getByHolePitch(pitch));
    }

    @GetMapping(value = "/thickness/{thickness}")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<List<RoadBeam>> getBeamsByThickness(@PathVariable Double thickness) {
        return ResponseEntity.ok(roadBeamService.getByThickness(thickness));
    }

    @GetMapping(value = "/weight/{weight}")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<List<RoadBeam>> getBeamsByWeight(@PathVariable Double weight) {
        return ResponseEntity.ok(roadBeamService.getByWeight(weight));
    }

    @GetMapping(value = "/price/{price}")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<List<RoadBeam>> getBeamsByPrice(@PathVariable Double price) {
        return ResponseEntity.ok(roadBeamService.getByPrice(price));
    }

    @GetMapping(value = "/lessthan/{length}/{thickness}")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<List<RoadBeam>> getBeamsLengthIsLessThanAndThicknessIsLessThane(@PathVariable Integer length,
                                                                                          @PathVariable Double thickness) {
        return ResponseEntity.ok(roadBeamService.getByLengthAndThicknessLessThan(length, thickness));
    }
}
