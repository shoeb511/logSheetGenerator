package resumeprojects.logsheetgenerator.models;

public class LogSheetMetaData {

    int fp;
    int bibNumber;
    String shooterName;
    String nation;
    String event;

    public int getFp() {
        return fp;
    }

    public void setFp(int fp) {
        this.fp = fp;
    }

    public int getBibNumber() {
        return bibNumber;
    }

    public void setBibNumber(int bibNumber) {
        this.bibNumber = bibNumber;
    }

    public String getShooterName() {
        return shooterName;
    }

    public void setShooterName(String shooterName) {
        this.shooterName = shooterName;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
