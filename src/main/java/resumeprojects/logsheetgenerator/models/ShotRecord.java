package resumeprojects.logsheetgenerator.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class ShotRecord {
    int shotNumber;
    ShotType shotType;
    int scoreValue;
    Coordinates coordinates;
    String timestamp;
    IsInnerTen isInnerTen;
    double overtime;

    public int getShotNumber() {
        return shotNumber;
    }

    public void setShotNumber(int shotNumber) {
        this.shotNumber = shotNumber;
    }

    public ShotType getShotType() {
        return shotType;
    }

    public void setShotType(ShotType shotType) {
        this.shotType = shotType;
    }

    public int getScoreValue() {
        return scoreValue;
    }

    public void setScoreValue(int scoreValue) {
        this.scoreValue = scoreValue;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public IsInnerTen getIsInnerTen() {
        return isInnerTen;
    }

    public void setIsInnerTen(IsInnerTen isInnerTen) {
        this.isInnerTen = isInnerTen;
    }

    public double getOvertime() {
        return overtime;
    }

    public void setOvertime(double overtime) {
        this.overtime = overtime;
    }
}
