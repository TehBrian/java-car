package car.part;

import car.CarCrashException;

public class Engine extends CarPart {

    private final long creationTime;
    private float lifeInMinutes;

    /**
     * The engine lasts 5 years (5 real-life minutes), and as it gets
     * older, the oil tank needs more frequent changes (see {@link Car#run()}
     * for how they talk to each other).
     *
     * @param yearsOld how old the engine is
     */
    public Engine(final float yearsOld) {
        super("engine", " years left", 5);
        this.creationTime = System.currentTimeMillis();
        this.lifeInMinutes = 0;
    }

    public float getLifeInMinutes() {
        return this.lifeInMinutes;
    }

    @Override
    public void replacePart() {
        super.replacePart();
        this.lifeInMinutes = 0;
    }

    public void function(final float milesDriven) throws CarCrashException {
        super.function(milesDriven);
        this.lifeInMinutes = (System.currentTimeMillis() - this.creationTime) / 60000f;
        this.setCondition(this.bestCondition - lifeInMinutes);
        if (this.condition <= 0) {
            this.crashCar();
        } else if (this.condition <= 2) {
            this.status("Your engine is getting old!");
            if (getBoolean("Replace?")) {
                this.replacePart();
                this.status("Engine replaced!");
            }
        } else {
            this.status("You'll need a new engine in " + this.condition + " years.");
        }
    }

}
