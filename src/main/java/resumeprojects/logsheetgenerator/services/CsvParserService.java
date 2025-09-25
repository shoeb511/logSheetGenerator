package resumeprojects.logsheetgenerator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import resumeprojects.logsheetgenerator.Strategies.LogReportStrategy;
import resumeprojects.logsheetgenerator.models.*;
import resumeprojects.logsheetgenerator.util.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CsvParserService {

    @Autowired
    LogReportStrategy logReportStrategy;

    public CsvParserService(LogReportStrategy logReportStrategy) {
        this.logReportStrategy = logReportStrategy;
    }

    public Map<Integer, LogSheet> parseCsv(MultipartFile sl, MultipartFile file, int bibNumber) throws IOException {
        System.out.println("Parsing CSV... inside service class.....");
        Map<Integer, String> map = new HashMap<>();

        int fp = 0;
        List<ShotRecord> shotRecords = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))){

            String line;
            int i = 1;

            while((line =reader.readLine()) != null){

                if (line.trim().isEmpty()) continue;

                String[] part = line.split(",");

                // Defensive check
                if (part.length < 4) {
                    System.out.println("⚠️ Skipping bad line: " + line);
                    continue; // or throw exception
                }

                if(Integer.parseInt(part[0]) == bibNumber){

                    ShotRecord shotRecord = new ShotRecord();
                    shotRecord.setShotNumber(i);
                    shotRecord.setScoreValue(Integer.parseInt(part[1]));
                    shotRecord.setCoordinates(new Coordinates(Double.parseDouble(part[8]), Double.parseDouble(part[9])));
                    IsInnerTen ii = IsInnerTen.NO;
                    if(Integer.parseInt(part[7]) == 1 ){
                        ii = IsInnerTen.YES;
                    }
                    shotRecord.setIsInnerTen(ii);
                    shotRecord.setTimestamp(part[6]);
                    shotRecord.setOvertime(Double.parseDouble(part[11]));

                    ShotType shotType = ShotType.SIGHTER;

                    if(Integer.parseInt(part[2]) == 1 ){
                        shotType = ShotType.MATCH;
                    }

                    shotRecord.setShotType(shotType);

                    shotRecords.add(shotRecord);
                    i++;
                }
            }
        }

        LogSheet logSheet = logReportStrategy.generateLogSheet(shotRecords);

        LogSheetMetaData lsMetaData = Util.setMetaToLogSheet(sl, bibNumber);

        logSheet.setMetaData(lsMetaData);

        Map<Integer, LogSheet> logSheetMap = new HashMap<>();

        logSheetMap.put(bibNumber, logSheet);

        return logSheetMap;
    }

}
