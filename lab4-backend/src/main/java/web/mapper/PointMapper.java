package web.mapper;

import web.dto.PointDto;
import web.entities.Point;

public class PointMapper {
    public static Point mapToPoint(PointDto pointDto) {
        return new Point(
                pointDto.getId(),
                pointDto.getX(),
                pointDto.getY(),
                pointDto.getR(),
                pointDto.isInArea(),
                pointDto.getUserLogin()
        );
    }

    public static PointDto mapToPointDto(Point point) {
        return new PointDto(
                point.getId(),
                point.getX(),
                point.getY(),
                point.getR(),
                point.isInArea(),
                point.getUserLogin()
        );
    }
}
