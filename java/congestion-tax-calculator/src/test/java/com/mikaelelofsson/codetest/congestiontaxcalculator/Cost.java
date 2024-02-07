package com.mikaelelofsson.codetest.congestiontaxcalculator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Date;

@SpringBootTest
class Cost {

	CongestionTaxCalculator taxCalculator = new CongestionTaxCalculator();
	private Date[] getDates(String[] stringDates) {
		return DataParser.getDatesFromStrings(stringDates);
	}

	@Test
	void maxCostLimit() {
		String[] stringDates = {
				"2013-02-08 06:20:27",//8
				"2013-02-08 14:35:00",//8
				"2013-02-08 15:29:00",
				"2013-02-08 15:47:00", //18
				"2013-02-08 16:01:00",
				"2013-02-08 16:48:00", //18
				"2013-02-08 17:49:00", //13

		};
		int cost = taxCalculator.getTax("Car", getDates(stringDates));

		Assert.isTrue(cost == 60, "Expected 60. Got " + cost);
	}

	@Test
	void passedMoreThanOneHourBorderValue() {
		String[] stringDates = {
				"2013-02-08 16:48:00", //18
				"2013-02-08 17:49:00", //13
		};
		Assert.isTrue(taxCalculator.getTax("Car", getDates(stringDates)) == 31, "Expected 13 as highest value");
	}

	@Test
	void passedExactlyOneHour() {
		String[] stringDates = {
				"2013-02-08 16:48:00", //18
				"2013-02-08 17:48:00", //13
		};
		Assert.isTrue(taxCalculator.getTax("Car", getDates(stringDates)) == 18, "Expected 13 as highest value");
	}

	@Test
	void highestTaxPaidWithinOneHour() {
		String[] stringDates = {
				"2013-02-08 17:49:00", //13 kronor
				"2013-02-08 18:29:00", //8 kronor
				"2013-02-08 18:35:00", //0 kronor
		};
		Assert.isTrue(taxCalculator.getTax("Car", getDates(stringDates)) == 13, "Expected 13 as highest value");
	}
	@Test
	void highestTaxPaidWithinOneHourExtendedOutsideHourSpan() {
		//Detected a bug . Value should be 18 + 13 = 31;
		String[] stringDates = {
				"2013-02-08 16:01:00", //18 kronor
				"2013-02-08 16:48:00", //18 kronor
				"2013-02-08 17:49:00", //13 kronor
				"2013-02-08 18:29:00", //8 kronor
		};
		Assert.isTrue(taxCalculator.getTax("Car", getDates(stringDates)) == 31, "Expected 31 as highest value");
	}

	@Test
	void noCostFreePeriods() {
		//Detected a bug . Value should be 18 + 13 = 31;
		String[] stringDates = {
				"2013-03-26 14:25:00",
				"2013-03-28 14:07:27"
		};
		Assert.isTrue(taxCalculator.getTax("Car", getDates(stringDates)) == 0, "Expected 31 as highest value");
	}

	@Test
	void costTimeperiodFirst() {
		//Detected a bug . Value should be 18 + 13 = 31;
		String[] stringDates = {
				"2013-01-14 06:00:00",
				"2013-01-14 06:29:00", //
		};
		Assert.isTrue(taxCalculator.getTax("Car", getDates(stringDates)) == 8, "Expected 31 as highest value");
	}
	@Test
	void costTimeperiodSecond() {
		//Detected a bug . Value should be 18 + 13 = 31;
		String[] stringDates = {
				"2013-01-14 06:30:00",
				"2013-01-14 06:59:00", //
		};
		Assert.isTrue(taxCalculator.getTax("Car", getDates(stringDates)) == 13, "Expected 31 as highest value");
	}
	@Test
	void costTimeperiodThree() {
		//Detected a bug . Value should be 18 + 13 = 31;
		String[] stringDates = {
				"2013-01-14 07:00:00",
				"2013-01-14 07:59:00", //
		};
		Assert.isTrue(taxCalculator.getTax("Car", getDates(stringDates)) == 18, "Expected 31 as highest value");
	}
	@Test
	void costTimeperiodFour() {
		//Detected a bug . Value should be 18 + 13 = 31;
		String[] stringDates = {
				"2013-01-14 08:00:00",
				"2013-01-14 08:29:00", //
		};
		Assert.isTrue(taxCalculator.getTax("Car", getDates(stringDates)) == 13, "Expected 31 as highest value");
	}
	@Test
	void costTimeperiodFive_LowerLimit() {
		//Detected a bug . Value should be 18 + 13 = 31;
		String[] stringDates = {
				"2013-01-14 14:59:00", //
		};
		Assert.isTrue(taxCalculator.getTax("Car", getDates(stringDates)) == 8, "Expected 31 as highest value");
	}
	@Test
	void costTimeperiodFive_UpperLimit() {
		//Detected a bug . Value should be 18 + 13 = 31;
		String[] stringDates = {
				"2013-01-14 08:30:00", //
		};
		Assert.isTrue(taxCalculator.getTax("Car", getDates(stringDates)) == 8, "Expected 31 as highest value");
	}
	@Test
	void costTimeperiodSix() {
		//Detected a bug . Value should be 18 + 13 = 31;
		String[] stringDates = {
				"2013-01-14 15:00:00", //0
				"2013-01-14 15:29:00", //0
		};
		Assert.isTrue(taxCalculator.getTax("Car", getDates(stringDates)) == 13, "Expected 31 as highest value");
	}
	@Test
	void costTimeperiodSeven_LowerLimit() {
		//Detected a bug . Value should be 18 + 13 = 31;
		String[] stringDates = {
				"2013-01-14 16:59:00", //0
		};
		Assert.isTrue(taxCalculator.getTax("Car", getDates(stringDates)) == 18, "Expected 31 as highest value");
	}
	@Test
	void costTimeperiodSeven_UpperLimit() {
		//Detected a bug . Value should be 18 + 13 = 31;
		String[] stringDates = {
				"2013-01-14 15:30:00", //0
		};
		Assert.isTrue(taxCalculator.getTax("Car", getDates(stringDates)) == 18, "Expected 31 as highest value");
	}
	@Test
	void costTimeperiodEight() {
		//Detected a bug . Value should be 18 + 13 = 31;
		String[] stringDates = {
				"2013-01-14 17:00:00", //0
				"2013-01-14 17:59:00",
		};
		Assert.isTrue(taxCalculator.getTax("Car", getDates(stringDates)) == 13, "Expected 31 as highest value");
	}
	@Test
	void costTimeperiodNine() {
		//Detected a bug . Value should be 18 + 13 = 31;
		String[] stringDates = {
				"2013-01-14 18:00:00", //0
				"2013-01-14 18:29:00",
		};
		Assert.isTrue(taxCalculator.getTax("Car", getDates(stringDates)) == 8, "Expected 31 as highest value");
	}
	@Test
	void costTimeperiodTen() {
		//Detected a bug . Value should be 18 + 13 = 31;
		String[] stringDates = {
				"2013-01-14 18:30:00", //0
				"2013-01-14 05:59:00",
		};
		Assert.isTrue(taxCalculator.getTax("Car", getDates(stringDates)) == 0, "Expected 31 as highest value");
	}









}
