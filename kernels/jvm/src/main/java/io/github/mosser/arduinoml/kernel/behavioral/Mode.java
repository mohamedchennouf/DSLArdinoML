package io.github.mosser.arduinoml.kernel.behavioral;

import io.github.mosser.arduinoml.kernel.generator.Visitable;
import io.github.mosser.arduinoml.kernel.generator.Visitor;
import io.github.mosser.arduinoml.kernel.structural.AnalogSensor;

import java.util.ArrayList;
import java.util.List;


public class Mode implements Visitable {

    private String modeName;
    private List<State> states = new ArrayList<>();
    private List<Transition> transitions = new ArrayList<>();


    public String getModeName() {
        return modeName;
    }

    public void setModeName(String modeName) {
        this.modeName = modeName;
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

    public List<Transition> getTransitions() {
        return transitions;
    }

    public void setTransitions(ArrayList<Transition> transitions) {
        this.transitions = transitions;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}