package car.part;

import car.CarCrashException;
import car.Functional;
import car.Interactive;

public abstract class CarPart implements Functional, Interactive {
    // instanceCount can help us assign a serial number to each part,
    // to help out the mechanics.
    private static long instanceCount = 0;

    private final String partName;
    private final long serialNumber;
    private final float bestCondition;
    private final String conditionMeasure;

    protected float condition;
    protected float currentTotalMiles;

    public CarPart(final String name, final String conditionMeasure, final float bestCondition) {
        instanceCount += 1;
        this.serialNumber = instanceCount;
        this.partName = name;
        this.conditionMeasure = conditionMeasure;
        this.bestCondition = bestCondition;
        this.condition = this.bestCondition;
        this.currentTotalMiles = 0;
    }

    public String getPartName() {
        return this.partName;
    }

    public long getSerialNumber() {
        return this.serialNumber;
    }

    public float getBestCondition() {
        return this.bestCondition;
    }

    public String getConditionMeasure() {
        return this.conditionMeasure;
    }

    public boolean isBroken() {
        return this.condition <= 0;
    }

    public boolean isLikeNew() {
        return this.condition >= this.bestCondition;
    }

    public float getCondition() {
        return this.condition;
    }

    public void setCondition(final float newCondition) {
        this.condition = newCondition;
        if (this.condition < 0) {
            this.condition = 0;
        } else if (this.condition > this.bestCondition) {
            this.condition = this.bestCondition;
        }
    }

    public float getCurrentTotalMiles() {
        return this.currentTotalMiles;
    }

    public void changeCondition(final float delta) {
        this.condition += delta;
        if (this.condition < 0) {
            this.condition = 0;
        } else if (this.condition > this.bestCondition) {
            this.condition = this.bestCondition;
        }
    }

    public void makeBroken() {
        this.condition = 0;
    }

    public void replacePart() {
        this.condition = this.bestCondition;
        this.currentTotalMiles = 0;
    }

    public void status() {
        System.out.printf("Your %s (serial #%d) is at %f%s.\n",
                this.partName, this.serialNumber, this.condition, this.conditionMeasure);
    }

    public void status(final String extraMessage) {
        System.out.printf("Your %s (serial #%d, %f%s) says: %s\n",
                this.partName, this.serialNumber, this.condition, this.conditionMeasure, extraMessage);
    }

    public void crashCar() throws CarCrashException {
        throw new CarCrashException("The " + this.partName + " broke and crashed the car!");
    }

    public void function(final float milesDriven) throws CarCrashException {
        this.currentTotalMiles += milesDriven;
    }

}
