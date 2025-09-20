package resumeprojects.logsheetgenerator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import resumeprojects.logsheetgenerator.models.LogSheet;
import resumeprojects.logsheetgenerator.services.CsvParserService;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/logs")
@CrossOrigin(origins = "http://localhost:3000")
public class LogController {

    @Autowired
    private CsvParserService csvParserService;

    @PostMapping("/upload")
    public Map<Integer, LogSheet> uploadCsv(@RequestParam("file") MultipartFile file, @RequestParam("shooterId") int shooterId) throws IOException {
        System.out.println("inside upload api controller");
        return csvParserService.parseCsv(file, shooterId);
    }
}
