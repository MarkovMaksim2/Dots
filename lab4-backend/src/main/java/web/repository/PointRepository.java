package web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.entities.Point;

import java.util.List;

public interface PointRepository extends JpaRepository<Point, Long> {
    List<Point> findAllByUserLogin(String userLogin);
    void deleteAllByUserLogin(String userLogin);
}
