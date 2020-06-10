package self.learning.jpatutorial.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import self.learning.jpatutorial.dao.Point;
import self.learning.jpatutorial.service.PointService;

@RestController
@RequestMapping("/")
public class PointsController {

    @Autowired
    PointService pointService;

    @PostMapping("/AddPoint")
    public int addPoint(@RequestBody Point point) {
        return pointService.addPoint(point);
    }

    @GetMapping
    public Point getPoint(@RequestParam int id) {
        return pointService.getPoint(id);
    }


}
