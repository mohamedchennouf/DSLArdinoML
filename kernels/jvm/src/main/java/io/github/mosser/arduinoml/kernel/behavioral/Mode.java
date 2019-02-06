package io.github.mosser.arduinoml.kernel.behavioral;

import io.github.mosser.arduinoml.kernel.generator.Visitable;
import io.github.mosser.arduinoml.kernel.generator.Visitor;
import io.github.mosser.arduinoml.kernel.structural.AnalogSensor;

import java.util.ArrayList;
import java.util.List;


public class Mode implements Visitable {

    private String modeName;
    private AnalogSensor analogSensor;
    private List<State> states = new ArrayList<>(  );


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

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

    public void addState(State state) {
        this.states.add( state );
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}