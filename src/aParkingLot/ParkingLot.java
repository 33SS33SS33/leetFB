package aParkingLot;

import java.util.logging.Level;

/**
 * Created by GAOSHANSHAN835 on 2016/5/12.
 */
public class ParkingLot {
    private Level[] levels;
    private final int NUM_LEVELS = 5;

    public ParkingLot() {
    }
    public boolean parkVehicle(Vehicle vehicle) {
        return  true;
    }

    public class Level{
        private int floor;
        private ParkingSpot[] spots;
        private int availableSpots = 0; // number of free spots
        private static final int SPOTS_PER_ROW = 10;

        public Level(int fir, int numberSpots) { ... }

        public int availableSpots() { return availableSpots; }

        /* Find a place to park this vehicle. Return false if failed. */
        public boolean parkVehicle(Vehicle vehicle) { ... }

        /* Park a vehicle starting at the spot spotNumber., and
        * continuing until vehicle.spotsNeeded. */
        private boolean parkStartingAtSpot(int num, Vehicle v) { ... }

        /* Find a spot to park this vehicle. Return index of spot, or -1
        * on failure. */
        private int findAvailableSpots(Vehicle vehicle) { ... }

        /* When a car was removed from the spot, increment
        * availableSpots */
        public void spotFreed() { availableSpots++; }
    }
    }
}
