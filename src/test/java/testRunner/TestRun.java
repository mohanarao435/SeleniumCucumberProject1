package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions
     (
    	features={".//Features/"},
    	glue="stepdefinitions",
    	dryRun=false,
    	monochrome=true,
    	plugin= {"pretty","html:target/report.html"},
    	tags= "@sanity or @regression"
    		 
    		 
    		 
    		 )





public class TestRun {

	
}
