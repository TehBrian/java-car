package car;

import car.part.CarPart;
import car.part.Engine;
import car.part.GasTank;
import car.part.OilTank;
import car.part.Tire;

import java.util.ArrayList;

public class Car implements Interactive {

    private final ArrayList<CarPart> parts = new ArrayList<>();

    public Car() {
        this.parts.add(new GasTank(17.2f, 35));
        this.parts.add(new OilTank(1));
        this.parts.add(new Engine(0));
        for (int t = 0; t < 4; t++) {
            this.parts.add(new Tire(32));
        }
    }

    public String listParts() {
        return String.join("\n", this.parts.stream().map(Object::toString).toList());
    }

    public void status() {
        for (final CarPart part : this.parts) {
            part.status();
        }
    }

    public void run() {
        try {
            do {
                final float miles = promptFloat("How many miles will you be driving?");

                Engine engine = null;
                OilTank oTank = null;
                for (final CarPart part : parts) {
                    part.function(miles);

                    if (part instanceof Engine instance) {
                        engine = instance;
                    } else if (part instanceof OilTank instance) {
                        oTank = instance;
                    }
                }

                if (engine != null && oTank != null) {
                    // The oil tank's behavior is affected by the engine's age in years,
                    // which are actually real-life minutes.
                    oTank.setEngineAgeModifier(1 + (engine.getLifeInMinutes() / engine.getBestCondition()));
                }
            } while (promptBoolean("Keep driving?"));
            this.status();
        } catch (final CarCrashException e) {
            System.out.println(e.getMessage());
            System.out.println("Your car crashed! You'll have to buy a new one and start over.");
        } finally {
            System.out.println("Thank you for driving responsibly.");
        }
    }

}
