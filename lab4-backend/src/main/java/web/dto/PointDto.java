package web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PointDto {
    private Long id;
    private double x;
    private double y;
    private double r;
    private boolean inArea;
    private String userLogin;

    public void checkArea() {
        if (r < 0) {
            double newR = -r, newX = -x, newY = -y;

            inArea = ((newX <= 0) && (newX >= -newR) && (newY >= 0) && (newY <= newR)) ||
                    ((newX >= 0) && (newY >= 0) && (((newX * newX) + (newY * newY)) <= (newR * newR))) ||
                    ((newY >= ((-newX * 2) - newR)) && (newY <= 0) && (newX <= 0));
        } else {
            inArea = ((x <= 0) && (x >= -r) && (y >= 0) && (y <= r)) ||
                    ((x >= 0) && (y >= 0) && (((x * x) + (y * y)) <= (r * r))) ||
                    ((y >= ((-x * 2) - r)) && (y <= 0) && (x <= 0));
        }
    }
}
