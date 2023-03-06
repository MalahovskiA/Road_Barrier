package by.malahovski.barriers.controllers;

import by.malahovski.barriers.models.barriers.roadBarrierKit.RoadConsole;
import by.malahovski.barriers.responseUserHelper.MessageResponse;
import by.malahovski.barriers.service.RoadConsoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/console")
public class RoadConsoleController {

    private final RoadConsoleService roadConsoleService;

    @Autowired
    public RoadConsoleController(RoadConsoleService roadConsoleService) {
        this.roadConsoleService = roadConsoleService;
    }

    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(roadConsoleService.deleteConsoleById(id));
    }

    @GetMapping(value = "/save/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<?> saveConsole(@PathVariable Long id, @RequestBody RoadConsole roadConsole) {
        roadConsoleService.saveConsole(id, roadConsole);
        return ResponseEntity.ok(new MessageResponse("Console SAVED"));
    }

    @GetMapping(value = "/name/{name}")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<List<RoadConsole>> getConsoleByName(@PathVariable String name) {
        return ResponseEntity.ok(roadConsoleService.findByName(name));
    }

    @GetMapping(value = "/designation/{designation}")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<RoadConsole> getConsoleByDesignation(@PathVariable String designation) {
        return ResponseEntity.ok(roadConsoleService.findByDesignation(designation));
    }

    @GetMapping(value = "/length/{length}")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<List<RoadConsole>> getConsolesByLength(@PathVariable Integer length) {
        return ResponseEntity.ok(roadConsoleService.findByLength(length));
    }

    @GetMapping(value = "/thickness/{thickness}")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<List<RoadConsole>> getConsolesByThickness(@PathVariable Double thickness) {
        return ResponseEntity.ok(roadConsoleService.findByThickness(thickness));
    }

    @GetMapping(value = "/weight/{weight}")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<List<RoadConsole>> getConsolesByWeight(@PathVariable Double weight) {
        return ResponseEntity.ok(roadConsoleService.findByWeight(weight));
    }

    @GetMapping(value = "/price/{price}")
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<List<RoadConsole>> getConsolesPrice(@PathVariable Double price) {
        return ResponseEntity.ok(roadConsoleService.findByPrice(price));
    }
}
