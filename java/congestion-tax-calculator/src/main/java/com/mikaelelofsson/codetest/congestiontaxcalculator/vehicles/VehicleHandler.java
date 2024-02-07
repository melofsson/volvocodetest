package com.mikaelelofsson.codetest.congestiontaxcalculator.vehicles;

import java.util.HashMap;
import java.util.Map;

public class VehicleHandler {

    private static Map<String, Integer> tollFreeVehicles = new HashMap<>();
    /*Initially a bug here. Busses are missing a toll free vechile.
    Also Tractor is put here, which is not specifed in requirements.*/
    static {
        tollFreeVehicles.put("Motorcycle", 0);
        tollFreeVehicles.put("Buss", 1);
        tollFreeVehicles.put("Emergency", 2);
        tollFreeVehicles.put("Diplomat", 3);
        tollFreeVehicles.put("Foreign", 4);
        tollFreeVehicles.put("Military", 5);
    }

    public static boolean isTollFreeVehicle(String vehicle) {
        if (vehicle == null) return false;
        return tollFreeVehicles.containsKey(vehicle);
    }
}
