package features;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.masterthought.cucumber.presentation.PresentationMode;
import net.masterthought.cucumber.sorting.SortingMethod;
import org.apache.commons.io.FileUtils;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import com.intuit.karate.junit5.Karate.Test;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestParallel
   {
	   @Test
	   public void testParallel() {
		   //System.setProperty("karate.env", "dev");
		   Results results = Runner
				   .path("classpath:features")
				   .outputCucumberJson(true)
				   //.tags("@abc", "~@ignore")
				   .parallel(1);
		   generateReport(results.getReportDir());
		   assertEquals(0, results.getFailCount(), results.getErrorMessages());
	   }

	   public static void generateReport(String karateOutputPath) {
		   Collection<File> jsonFiles = FileUtils.listFiles(new File(karateOutputPath), new String[] {"json"}, true);
		   List<String> jsonPaths = new ArrayList(jsonFiles.size());
		   jsonFiles.forEach(file -> jsonPaths.add(file.getAbsolutePath()));
		   Configuration config = new Configuration(new File("target"), "APIAutomation");
		   config.setSortingMethod(SortingMethod.NATURAL);
		   // optional configuration - check javadoc for details
		   config.addPresentationModes(PresentationMode.EXPAND_ALL_STEPS);
		   config.setTrendsStatsFile(new File("target/demo-trends.json"));
		   ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
		   reportBuilder.generateReports();
	   }
	 
   }
