package io.github.mosser.arduinoml.kernel.behavioral;

import io.github.mosser.arduinoml.kernel.generator.Visitable;
import io.github.mosser.arduinoml.kernel.generator.Visitor;
import io.github.mosser.arduinoml.kernel.structural.AnalogSensor;

import java.util.ArrayList;
import java.util.List;


public class Mode implements Visitable {

    private String modeName;
    private List<State> states = new ArrayList<>(  );
    private List<TransitionMode> transitionMode =new ArrayList<TransitionMode>(  );
    private State initState;


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

    public List<TransitionMode> getTransitionMode() {
        return transitionMode;
    }

    public void setTransitionMode(TransitionMode transitionMode) {
        this.transitionMode.add(transitionMode);
    }

    public void setTransitionMode(List<TransitionMode> transitionMode) {
        this.transitionMode = transitionMode;
    }

    public State getInitState() {
        return initState;
    }

    public void setInitState(State initState) {
        this.initState = initState;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}