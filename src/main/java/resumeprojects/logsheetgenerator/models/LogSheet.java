package resumeprojects.logsheetgenerator.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Setter
@Getter
public class LogSheet {

    LogSheetMetaData metaData;

    List<Series> series;

    Date timestamp;

    double total;

    public List<Series> getSeries() {
        return series;
    }

    public void setSeries(List<Series> series) {
        this.series = series;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public LogSheetMetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(LogSheetMetaData metaData) {
        this.metaData = metaData;
    }
}
