package resumeprojects.logsheetgenerator.Strategies;

import resumeprojects.logsheetgenerator.models.LogSheet;
import resumeprojects.logsheetgenerator.models.ShotRecord;

import java.util.List;

public interface LogReportStrategy {

    public LogSheet generateLogSheet(List<ShotRecord> shotRecords);
}
