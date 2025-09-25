package resumeprojects.logsheetgenerator.util;

import org.springframework.web.multipart.MultipartFile;
import resumeprojects.logsheetgenerator.models.LogSheet;
import resumeprojects.logsheetgenerator.models.LogSheetMetaData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Util {

    public static LogSheetMetaData setMetaToLogSheet(MultipartFile sl, int bib) throws IOException {

        LogSheetMetaData meta = new LogSheetMetaData();

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(sl.getInputStream()))){

            String line;
            int fp = 0;
            String name = "";
            String nation = "";

            while((line = reader.readLine()) != null){
                //String[] part = line.split(",");
                if (line.trim().isEmpty()) continue;

                String[] part = line.split(";");

                // Defensive check
//                if (part.length < 4) {
//                    System.out.println("⚠️ Skipping bad line: " + line);
//                    continue; // or throw exception
//                }
                if(Integer.parseInt(part[1]) == bib){
                    name = part[4];
                    nation = part[5];
                    fp = Integer.parseInt(part[10]);
                    break;
                }
            }
            meta.setBibNumber(bib);
            meta.setFp(fp);
            meta.setShooterName(name);
            meta.setNation(nation);
        }
        return meta;
    }
}
