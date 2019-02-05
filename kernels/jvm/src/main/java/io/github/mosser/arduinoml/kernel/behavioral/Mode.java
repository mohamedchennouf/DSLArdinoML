package io.github.mosser.arduinoml.kernel.behavioral;

import io.github.mosser.arduinoml.kernel.generator.Visitable;
import io.github.mosser.arduinoml.kernel.generator.Visitor;
import io.github.mosser.arduinoml.kernel.structural.AnalogSensor;


public class Mode implements Visitable {

    private String modeName;
    private AnalogSensor analogSensor;

    public String getModeName() {
        return modeName;
    }

    public void setModeName(String modeName) {
        this.modeName = modeName;
    }

    public AnalogSensor getAnalogSensor() {
        return analogSensor;
    }

    public void setAnalogSensor(AnalogSensor analogSensor) {
        this.analogSensor = analogSensor;
    }



    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}