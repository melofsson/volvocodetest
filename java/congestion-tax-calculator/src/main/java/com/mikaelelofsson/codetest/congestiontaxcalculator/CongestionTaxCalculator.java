package com.mikaelelofsson.codetest.congestiontaxcalculator;

import com.mikaelelofsson.codetest.congestiontaxcalculator.vehicles.VehicleHandler;
import java.util.Date;

public class CongestionTaxCalculator {

    public int getTax(String vehicle, Date[] dates)
    {
        //Moved vehicle check to absolute beginning,
        //as this decides on if we need to check anything else
        if (VehicleHandler.isTollFreeVehicle(vehicle)) return 0;
        int fee = getTotalFeeForPeriod(dates);
        if (fee > 60) fee = 60;
        return fee;
    }

    private int getTotalFeeForPeriod(Date[] dates) {
        Date intervalStart = dates[0];
        int totalFee = 0;

        //Moved this out of iteration block to prevent tempFee from always becoming first time position in new hour period
        int tempFee = TollFeeHandler.getTollFee(intervalStart);

        for (int i = 0; i < dates.length ; i++) {
            Date date = dates[i];
            int nextFee = TollFeeHandler.getTollFee(date);

            long diffInMillies = date.getTime() - intervalStart.getTime();
            long minutes = diffInMillies/1000/60;

            if (minutes <= 60)
            {
                if (totalFee > 0) totalFee -= tempFee;
                if (nextFee >= tempFee) tempFee = nextFee;
                totalFee += tempFee;
            }
            else
            {
                totalFee += nextFee;
                //Bumped intervalStart to current date if more than 1 hour has passed
                intervalStart = dates[i];
            }
        }
        return totalFee;
    }
}
