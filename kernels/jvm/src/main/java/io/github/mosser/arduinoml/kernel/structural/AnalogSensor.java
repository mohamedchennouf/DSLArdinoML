package io.github.mosser.arduinoml.kernel.structural;

import io.github.mosser.arduinoml.kernel.generator.Visitor;

public class AnalogSensor extends  Sensor { //extends Sensor {

    private double threshold;

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        System.out.println("oooooooo");
        System.out.println(threshold);
        this.threshold = threshold;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
