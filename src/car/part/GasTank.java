package car.part;

import car.CarCrashException;

public class GasTank extends CarPart {

    private final float milesPerGallon;

    public GasTank(final float capacityGallons, final float milesPerGallon) {
        super("gas tank", " gallons", capacityGallons);
        this.milesPerGallon = milesPerGallon;
    }

    public float getMilesPerGallon() {
        return this.milesPerGallon;
    }

    public void fillTank(final float numOfGallons) {
        final float remainder = this.getBestCondition() - this.condition;
        if (this.condition < 0) {
            this.status("Nice try, but you can't steal my gas with a negative number! Adding "
                    + remainder + " gallons to reach capacity.");
            this.setCondition(this.getBestCondition());
        } else if (numOfGallons > remainder) {
            this.status("Too much! Only adding " + remainder + " gallons to reach capacity.");
            this.setCondition(this.getBestCondition());
        } else {
            this.status("Adding " + numOfGallons + " gallons. You now have "
                    + this.condition + numOfGallons + " gallons.");
            this.changeCondition(numOfGallons);
        }
    }

    public void function(final float milesDriven) throws CarCrashException {
        super.function(milesDriven);
        final float gallonsConsumed = milesDriven / this.milesPerGallon;
        this.changeCondition(-1 * gallonsConsumed);
        if (this.condition <= 0) {
            this.crashCar();
        } else if (this.condition <= (this.getBestCondition() / 4)) {
            this.status("Low on gas!");
            if (promptBoolean("Refill?")) {
                this.fillTank(promptFloat("How many gallons would you like to add?"));
            }
        }
        if ((this.condition / this.getBestCondition()) < 0.5) {
            this.status("Your gas tank is less than half full.");
        } else {
            this.status("You're good on gas.");
        }
    }

}
