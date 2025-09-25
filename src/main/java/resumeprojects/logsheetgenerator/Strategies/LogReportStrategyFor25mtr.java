package resumeprojects.logsheetgenerator.Strategies;

import org.springframework.stereotype.Component;
import resumeprojects.logsheetgenerator.models.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

@Component
public class LogReportStrategyFor25mtr implements LogReportStrategy {
    @Override
    public LogSheet generateLogSheet(List<ShotRecord> shotRecords) {

        LogSheet sheet = new LogSheet();
        List<Series> seriesList = new LinkedList<>();

        //sighting series
        List<ShotRecord> sightingRecords = new ArrayList<ShotRecord>();


        for (ShotRecord shotRecord : shotRecords) {
            if(shotRecord.getShotType().equals(ShotType.SIGHTER)){
                sightingRecords.add(shotRecord);
                //shotRecords.remove(shotRecord);
            }
        }
        Series sightingSeries = new Series();
        sightingSeries.setSeriesNumber(1);
        sightingSeries.setSeriesType(SeriesType.SIGHTING_SERIES);
        sightingSeries.setShotRecords(sightingRecords);

        seriesList.add(sightingSeries);


        Queue<ShotRecord> queue = shotRecords.stream()
                .filter(sr -> sr.getShotType().equals(ShotType.MATCH))
                .collect(Collectors.toCollection(LinkedList::new));


        int seriesNumber = 0;
        int matchShotNumber = 1;
        while (!queue.isEmpty()) {
            Series series = new Series();
            List<ShotRecord> shotRecordsForSeries = new ArrayList<>();
            double subTotal = 0;
            for(int i = 0; i < 5 ; i++){
                if(!queue.isEmpty()){
                    ShotRecord shotRecord = queue.poll();
                    if(shotRecord.getShotType().equals(ShotType.MATCH)){
                        subTotal += shotRecord.getScoreValue();
                        shotRecord.setShotNumber(matchShotNumber);
                        shotRecordsForSeries.add(shotRecord);
                        matchShotNumber++;
                    }
                }
            }
            seriesNumber +=1;
            series.setSeriesNumber(seriesNumber);
            series.setSeriesType(SeriesType.MATCH_SERIES);
            series.setShotRecords(shotRecordsForSeries);
            series.setSubTotal(subTotal);

            seriesList.add(series);
        }

        double totalScore = 0;

        for(Series series : seriesList){
            totalScore += series.getSubTotal();
        }

        sheet.setSeries(seriesList);
        sheet.setTotal(totalScore);

        return sheet;
    }
}
