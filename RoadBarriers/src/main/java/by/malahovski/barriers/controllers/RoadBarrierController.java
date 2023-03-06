package by.malahovski.barriers.controllers;

import by.malahovski.barriers.models.barriers.RoadMetalBarrier;
import by.malahovski.barriers.responseUserHelper.MessageResponse;
import by.malahovski.barriers.service.RoadBarrierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/barriers")
public class RoadBarrierController {

    private final RoadBarrierService roadBarrierService;

    @Autowired
    public RoadBarrierController(RoadBarrierService roadBarrierService) {
        this.roadBarrierService = roadBarrierService;
    }

    @GetMapping(value = "/metalbarrier/{length}/{holdingCapacity}/{workingWidth}")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<RoadMetalBarrier> getMetalBarrier(@PathVariable Integer length,
                                                            @PathVariable Integer holdingCapacity,
                                                            @PathVariable Double workingWidth) {
        return ResponseEntity.ok(roadBarrierService.calculateBarrierByParameters(length, holdingCapacity, workingWidth));
    }

    @PutMapping(value = "/updateprice")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Boolean> updatePriceExel(@RequestBody String name) {
        return ResponseEntity.ok(roadBarrierService.updatePriceOnFile(name));
    }

    @PutMapping(value = "/saveparameters/{holdingCapacity}/{workingWidth}")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<?> saveParameters(@PathVariable Integer holdingCapacity,
                                            @PathVariable Double workingWidth,
                                            @RequestBody String fileName) {
        roadBarrierService.serializeBarrierParameters(fileName, holdingCapacity, workingWidth);
        return ResponseEntity.ok(new MessageResponse("PARAMETERS SAVED"));
    }
}
