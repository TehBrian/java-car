package car.part;

import car.CarCrashException;

import java.util.Random;

public class Tire extends CarPart {

    private static short instanceCount;
    private final short tireNumber;
    private float inflationPSI;

    public Tire(final float inflationPSI) {
        super("tire", "% traction", 100);
        instanceCount += 1;
        this.tireNumber = instanceCount;
        this.inflationPSI = inflationPSI;
        if (instanceCount > 4) {
            this.status("Initializing alien technology for a fifth wheel...\n"
                    + "Initialization failed, please abort! Abort!");
        }
    }

    public float getInflationPSI() {
        return this.inflationPSI;
    }

    public short getTireNumber() {
        return this.tireNumber;
    }

    @Override
    public void replacePart() {
        super.replacePart();
        this.inflationPSI = 32;
    }

    @Override
    public void status() {
        System.out.printf("Tire #%s (serial #%d) is at %f%s and %f psi)\n",
                this.tireNumber, this.getSerialNumber(), this.condition,
                this.getConditionMeasure(), this.inflationPSI);
    }

    @Override
    public void status(final String extraMessage) {
        System.out.printf("Tire #%s (serial #%d, %f%s, %f psi) says: %s\n",
                this.tireNumber, this.getSerialNumber(), this.condition,
                this.getConditionMeasure(), this.inflationPSI, extraMessage);
    }

    public void function(final float milesDriven) throws CarCrashException {
        super.function(milesDriven);
        final Random rand = new Random();
        this.changeCondition(-1 * (milesDriven / 1000) * rand.nextFloat());
        final float pressureLoss;
        for (int i = 0; i < (milesDriven); i++) {
            if (rand.nextInt(2000) == 1999) {
                pressureLoss = (rand.nextFloat() * this.inflationPSI * 0.5f);
                this.inflationPSI -= pressureLoss;
                this.status("Tire #" + this.tireNumber + " just lost " + pressureLoss + "psi!");
                break;
            }
        }
        if (this.condition < 10 || this.inflationPSI < 15) {
            this.crashCar();
        } else if (this.condition < 25) {
            this.status("Low on traction.");
            if (getBoolean("Replace?")) {
                this.replacePart();
                this.status("Just replaced this worn-out tire.");
            }
        } else if (this.inflationPSI < 27) {
            final int remainder = 32 - (int) this.inflationPSI;
            this.status("Tire is low!");
            if (getBoolean("Fill it?")) {
                this.inflationPSI = 32;
                this.status("Traction is OK but just added " + remainder + "psi to fill this tire.");
            }
        } else {
            this.status("This tire has enough air and traction.");
        }
    }
}
