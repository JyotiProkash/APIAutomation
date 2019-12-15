package features;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.intuit.karate.KarateOptions;
import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

@KarateOptions(features = {
	    "classpath:features/AllEmployees.feature",
	    "classpath:features/DeleteRecord.feature",
	    "classpath:features/CreateAnEmployee.feature",
	    "classpath:features/SpecificEmployee.feature",
	    "classpath:features/UpdateEmployeeRecord.feature"})

public class TestParallel 
   {
	
	@Test
    public void testParallel() {
		System.setProperty("karate.env", "dev");
        //String karateOutputPath="target/surefire-reports";
        Results results = Runner.parallel(getClass(), 1);
        generateReport(results.getReportDir());
        assertTrue(results.getErrorMessages(), results.getFailCount() == 0);
    } 
	
	 private static void generateReport(String karateOutputPath) 
	    {
		 
		    Collection<File> jsonFiles = FileUtils.listFiles(new File(karateOutputPath), new String[] {"json"}, true);
	        List<String> jsonPaths = new ArrayList<String>(jsonFiles.size());
	        jsonFiles.forEach(file->jsonPaths.add(file.getAbsolutePath()));
	        /*for (File file : jsonFiles) {
	            jsonPaths.add(file.getAbsolutePath());
	        }*/
	        
		    Configuration config = new Configuration(new File("target"), "KarateAPITest");
	        config.addClassifications("dev", System.getProperty("karate.env"));
	        ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
	        reportBuilder.generateReports();
	    }
	 
   }
