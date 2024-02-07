package com.mikaelelofsson.codetest.congestiontaxcalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@SpringBootApplication

@RestController
public class CongestionTaxCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CongestionTaxCalculatorApplication.class, args);
	}

	@GetMapping("/tax")
	public int simpleRequest(@RequestParam String vehicleType,
								@RequestParam String[] dates) {
		Date[] parsedDates = DataParser.getDatesFromStrings(dates);
		CongestionTaxCalculator taxCalculator = new CongestionTaxCalculator();
		return taxCalculator.getTax(vehicleType, parsedDates);
	}
}
