package microwave;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class Stepdefs {

	public Microwave microwave;

	public void printDigits(byte [] digits) {
    	System.out.println("Digits is: " + 
    			digits[DisplayController.TENS_OF_MINUTES] + " " + 
    			digits[DisplayController.MINUTES] + " " + 
    			digits[DisplayController.TENS_OF_SECONDS] + " " + 
    			digits[DisplayController.SECONDS]);
	}
	
	public void printStatus() {
		printDigits(microwave.digits());
		System.out.println("Mode is: " + microwave.getMode()); 
	}


	@Given("^presets are$")
    public void presets_are(DataTable dataTable) throws Throwable {
        List<Preset> presets = new ArrayList<>();
        List<Map<String, String>> dataTableMaps = dataTable.asMaps(String.class, String.class);
        for(Map<String,String> row:dataTableMaps){
            presets.add(new Preset(row.get("name")
                    ,Integer.parseInt(row.get("timeToCook"))
                    ,Integer.parseInt(row.get("powerLevel"))));
        }
		microwave = new Microwave(new ModeController(), new DisplayController(100), presets);
    	microwave.setDoorOpen(false);
		presets.stream().forEach(p -> System.out.println(p));
    }

    @Given("^polling rate is (\\d+) ms$")
    public void polling_rate_is_ms(int rate) throws Throwable {
    	microwave.setTickRateInHz(rate);
        // System.out.println("polling rate is " + rate + " ms.");
    }

	@Given("^([A-Za-z]+) presses the (\\d+) key$")
    public void user_presses_the_key(String user, int key) throws Throwable {
        microwave.digitPressed(key);
        microwave.tick();
	}

	@Given("^([A-Za-z]+) presses the following keys as a table:")
	public void user_presses_keys_table(String user, List<Integer> keys) {
		for (Integer key: keys) {
			microwave.digitPressed(key);
			microwave.tick();
		}
	}
	
    @When("^([A-Za-z]+) presses the start key$")
    public void user_presses_the_start_key(String user) throws Throwable {
        microwave.startPressed();
        microwave.tick();
    }

    @When("^(\\d+) seconds elapse$")
    public void seconds_elapse(int time) throws Throwable {
        for (int i = 0; i < 20*time; i++) {
        	microwave.tick(); 
        }
    }



    @Then("^digits reads (\\d)(\\d)(\\d)(\\d)$")
    public void digits_reads(int tensOfMinutes, int minutes, int tensOfSeconds, int seconds) throws Throwable {
    	byte [] digits = microwave.digits();
        for(byte b: microwave.digits()){
            System.out.println(b);
        }
    	assertEquals((int)digits[DisplayController.TENS_OF_MINUTES], tensOfMinutes);
    	assertEquals((int)digits[DisplayController.MINUTES], minutes);
    	assertEquals((int)digits[DisplayController.TENS_OF_SECONDS], tensOfSeconds);
    	assertEquals((int)digits[DisplayController.SECONDS], seconds); 
    }

    @Then("^mode is cooking$")
    public void mode_is_cooking() throws Throwable {
    	assertEquals(microwave.getMode(), ModeController.Mode.Cooking);
    }

    @Then("^mode is setup$")
    public void mode_is_setup() throws Throwable {
    	assertEquals(microwave.getMode(), ModeController.Mode.Setup);
    }

    @Then("^mode is suspended$")
    public void mode_is_suspended() throws Throwable {
    	assertEquals(microwave.getMode(), ModeController.Mode.Suspended);
    }

    @Given("^Bob presses the (\\d+) scenario key$")
    public void bob_presses_the_scenario_key(int arg1) throws Throwable {
    	microwave.presetPressed(arg1);
    }


    @When("^Bob presses the (\\d+) scenario key it will out-of-range fail$")
    public void bob_presses_the_scenario_key_it_will_out_of_range_fail(int arg1) throws Throwable {
        try {
        	microwave.presetPressed(arg1);
        	fail("Test should have failed with out-of-range exception");
        } catch (IllegalArgumentException e) { }
    }

    @Then("^Bob presses the (\\d+) scenario key it will mode fail$")
    public void bob_presses_the_scenario_key_it_will_mode_fail(int arg1) throws Throwable {
        try {
        	microwave.presetPressed(arg1);
        	fail("Test should have failed with mode exception");
        } catch (IllegalArgumentException e) { }
    }

    @When("^Bob presses the clear key$")
    public void bob_presses_the_clear_key() throws Throwable {
    	microwave.clearPressed();
		microwave.tick();
    }

}
