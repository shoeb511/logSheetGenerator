package resumeprojects.logsheetgenerator.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import resumeprojects.logsheetgenerator.services.CsvParserService;


public class Coordinates {

    double x;
    double y;

    public Coordinates(double x, double y) {
        this.x = x;
        this.y = y;
    }


    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
