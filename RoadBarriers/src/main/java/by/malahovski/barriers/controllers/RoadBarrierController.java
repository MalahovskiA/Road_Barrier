package by.malahovski.barriers.controllers;

import by.malahovski.barriers.models.barriers.RoadBarrier;
import by.malahovski.barriers.service.RoadBarrierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        public ResponseEntity<List<RoadBarrier>> getAllBarriers() {
            List<RoadBarrier> roadBarrierList = roadBarrierService.getAllBarriers();
            return ResponseEntity.status(HttpStatus.FOUND).body(roadBarrierList);
        }
}
