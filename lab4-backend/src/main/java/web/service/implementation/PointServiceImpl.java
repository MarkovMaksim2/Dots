package web.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dto.PointDto;
import web.entities.Point;
import web.mapper.PointMapper;
import web.repository.PointRepository;
import web.service.PointService;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PointServiceImpl implements PointService {
    @Autowired
    private PointRepository pointRepository;

    @Override
    public PointDto addPoint(PointDto pointDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        pointDto.checkArea();

        pointDto.setUserLogin(userDetails.getUsername());


        Point point = PointMapper.mapToPoint(pointDto);
        Point savedPoint = pointRepository.save(point);
        return PointMapper.mapToPointDto(savedPoint);
    }

    @Override
    public List<PointDto> getPointsFromUserLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return pointRepository.findAllByUserLogin(userDetails.getUsername()).stream()
                .map(PointMapper::mapToPointDto)
                .toList();
    }

    @Transactional
    @Override
    public void deletePointsFromUserLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        pointRepository.deleteAllByUserLogin(userDetails.getUsername());
    }
}
