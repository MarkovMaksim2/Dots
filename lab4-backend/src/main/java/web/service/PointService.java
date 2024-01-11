package web.service;

import org.springframework.transaction.annotation.Transactional;
import web.dto.PointDto;

import java.util.List;

public interface PointService {
    PointDto addPoint(PointDto pointDto);

    List<PointDto> getPointsFromUserLogin();

    @Transactional
    void deletePointsFromUserLogin();
}
