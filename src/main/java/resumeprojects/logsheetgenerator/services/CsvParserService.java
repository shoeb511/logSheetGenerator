package resumeprojects.logsheetgenerator.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import resumeprojects.logsheetgenerator.models.*;

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

    public Map<Integer, LogSheet> parseCsv(MultipartFile file, int lineNumber) throws IOException {
        System.out.println("Parsing CSV... inside service class.....");
        Map<Integer, String> map = new HashMap<>();

        int bibNumer = -1;
        int fp = lineNumber;
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

                if(Integer.parseInt(part[3]) == fp){

                    if(bibNumer == -1){
                        bibNumer = Integer.parseInt(part[0]);
                    }

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
        LogSheet logSheet = new LogSheet();
        logSheet.setTargetNumber(lineNumber);
        logSheet.setBibNumber(bibNumer);
        logSheet.setShotRecords(shotRecords);

        Map<Integer, LogSheet> logSheetMap = new HashMap<>();

        logSheetMap.put(fp, logSheet);

        return logSheetMap;
    }

}
