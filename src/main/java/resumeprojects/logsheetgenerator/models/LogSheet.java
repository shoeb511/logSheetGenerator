package resumeprojects.logsheetgenerator.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Setter
@Getter
public class LogSheet {
    int targetNumber;

    int bibNumber;

    List<ShotRecord> shotRecords = new ArrayList<ShotRecord>();

    public int getTargetNumber() {
        return targetNumber;
    }

    public void setTargetNumber(int targetNumber) {
        this.targetNumber = targetNumber;
    }

    public int getBibNumber() {
        return bibNumber;
    }

    public void setBibNumber(int bibNumber) {
        this.bibNumber = bibNumber;
    }

    public List<ShotRecord> getShotRecords() {
        return shotRecords;
    }

    public void setShotRecords(List<ShotRecord> shotRecords) {
        this.shotRecords = shotRecords;
    }
}
