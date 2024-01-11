package web.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.dto.PointDto;
import web.service.PointService;

import java.util.List;

@RestController
@RequestMapping("/api/points")
@RequiredArgsConstructor
public class PointController {
    private final PointService pointService;

    @PostMapping("/add")
    public ResponseEntity<PointDto> addPoint(@RequestBody PointDto pointDto) {
          return  ResponseEntity.ok(pointService.addPoint(pointDto));
    }

    @GetMapping("/get_points")
    public ResponseEntity<List<PointDto>> getPoints() {
        return ResponseEntity.ok(pointService.getPointsFromUserLogin());
    }

    @DeleteMapping("/delete_points")
    public ResponseEntity<List<PointDto>> deletePoints() {
        pointService.deletePointsFromUserLogin();
        return ResponseEntity.ok(null);
    }
}
