package by.malahovski.barriers.controllers;

import by.malahovski.barriers.models.barriers.EClassOfTheBarrier;
import by.malahovski.barriers.models.barriers.RoadBarrierParameters;
import by.malahovski.barriers.models.barriers.RoadMetalBarrier;
import by.malahovski.barriers.service.RoadBarrierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/barriers")
public class RoadBarrierController {

    private final RoadBarrierService roadBarrierService;

    @Autowired
    public RoadBarrierController(RoadBarrierService roadBarrierService) {
        this.roadBarrierService = roadBarrierService;
    }

    @GetMapping(value = "/all")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<List<RoadBarrierParameters>> getAllBarriers() {
        List<RoadBarrierParameters> roadBarrierParametersList = roadBarrierService.getAllBarriers();
        return ResponseEntity.status(HttpStatus.FOUND).body(roadBarrierParametersList);
    }

    @GetMapping(value = "/class/{class}")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<List<RoadBarrierParameters>> getBarriersByClass(@PathVariable("class") String byClass) {
        List<RoadBarrierParameters> roadBarrierParametersList = roadBarrierService.getBarriersByClass(EClassOfTheBarrier.valueOf(byClass));
        return ResponseEntity.status(HttpStatus.FOUND).body(roadBarrierParametersList);
    }

    @GetMapping(value = "/name/{name}")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<RoadBarrierParameters> getBarriersByName(@PathVariable("name") String name) {
        RoadBarrierParameters roadBarrierParametersList = roadBarrierService.getBarriersByName(name);
        return ResponseEntity.status(HttpStatus.FOUND).body(roadBarrierParametersList);
    }

    @GetMapping(value = "/technical/{holdingCapacity}/{workingWidth}")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<List<RoadBarrierParameters>> getTechnicalCalculate(@PathVariable Integer holdingCapacity,
                                                                             @PathVariable Double workingWidth) {
        return ResponseEntity.ok(roadBarrierService.getRoadBarrierParametersByParameters(holdingCapacity,workingWidth));
    }

    @GetMapping(value = "/calculate/{holdingCapacity}/{workingWidth}")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<List<RoadBarrierParameters>> calculateBarrier(
                                                                        @PathVariable Integer holdingCapacity,
                                                                        @PathVariable Double workingWidth) {
        return ResponseEntity.ok(roadBarrierService.getRoadBarrierParametersByParameters(holdingCapacity,workingWidth));
    }

    @GetMapping(value = "/metalbarrier/{length}/{holdingCapacity}/{workingWidth}")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<RoadMetalBarrier> getMetalBarrier(@PathVariable Integer length,
                                                            @PathVariable Integer holdingCapacity,
                                                            @PathVariable Double workingWidth) {
        return ResponseEntity.ok(roadBarrierService.calculateBarrierByParameters(length,holdingCapacity,workingWidth));
    }

    @PutMapping(value = "/updateprice")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Boolean> updatePriceExel(@RequestBody String name) {
        return ResponseEntity.ok(roadBarrierService.updatePriceOnFile(name));
    }
}
