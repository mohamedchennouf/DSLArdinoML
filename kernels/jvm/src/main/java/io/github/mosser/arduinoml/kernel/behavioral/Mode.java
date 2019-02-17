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
    private State initState;
    private TransitionMode transitionMode;


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

    public State getInitState() {
        return initState;
    }

    public void setInitState(State initState) {
        this.initState = initState;
    }

    public TransitionMode getTransitionMode() {
        return transitionMode;
    }

    public void setTransitionMode(TransitionMode transitionMode) {
        this.transitionMode = transitionMode;
    }

    public void setTransitions(List<Transition> transitions) {
        this.transitions = transitions;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}