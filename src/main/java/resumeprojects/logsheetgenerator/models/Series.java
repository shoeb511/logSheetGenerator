package resumeprojects.logsheetgenerator.models;

import java.util.List;

public class Series {

    int seriesNumber;

    SeriesType seriesType;

    List<ShotRecord> shotRecords;

    double subTotal;

    public int getSeriesNumber() {
        return seriesNumber;
    }

    public void setSeriesNumber(int seriesNumber) {
        this.seriesNumber = seriesNumber;
    }

    public SeriesType getSeriesType() {
        return seriesType;
    }

    public void setSeriesType(SeriesType seriesType) {
        this.seriesType = seriesType;
    }

    public List<ShotRecord> getShotRecords() {
        return shotRecords;
    }

    public void setShotRecords(List<ShotRecord> shotRecords) {
        this.shotRecords = shotRecords;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
}
