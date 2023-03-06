package by.malahovski.barriers.controllers;


import by.malahovski.barriers.models.barriers.roadBarrierKit.RoadRack;
import by.malahovski.barriers.responseUserHelper.MessageResponse;
import by.malahovski.barriers.service.RoadRackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/rack")
public class RoadRackController {
    private final RoadRackService roadRackService;

    @Autowired
    public RoadRackController(RoadRackService roadRackService) {
        this.roadRackService = roadRackService;
    }

    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(roadRackService.deleteRackById(id));
    }

    @GetMapping(value = "/save/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<?> saveRack(@PathVariable Long id, @RequestBody RoadRack roadRack) {
        roadRackService.saveRack(id, roadRack);
        return ResponseEntity.ok(new MessageResponse("Rack SAVED"));
    }

    @GetMapping(value = "/name/{name}")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<RoadRack> getRackByName(@PathVariable String name) {
        return ResponseEntity.ok(roadRackService.findByName(name));
    }

    @GetMapping(value = "/profile/{profile}")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<List<RoadRack>> getRackByProfile(@PathVariable String profile) {
        return ResponseEntity.ok(roadRackService.findByRoadRackProfile(profile));
    }


    @GetMapping(value = "/height/{height}")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<List<RoadRack>> getRoadRackByHeight(@PathVariable Double height) {
        return ResponseEntity.ok(roadRackService.findByHeight(height));
    }

    @GetMapping(value = "/thickness/{thickness}")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<List<RoadRack>> getRackByThickness(@PathVariable Double thickness) {
        return ResponseEntity.ok(roadRackService.findByThickness(thickness));
    }

    @GetMapping(value = "/weight/{weight}")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<List<RoadRack>> getRackByWeight(@PathVariable Double weight) {
        return ResponseEntity.ok(roadRackService.findByWeight(weight));
    }

    @GetMapping(value = "/price/{price}")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<List<RoadRack>> getConsolesPrice(@PathVariable Double price) {
        return ResponseEntity.ok(roadRackService.findByPrice(price));
    }
}
