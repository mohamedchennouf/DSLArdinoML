package io.github.mosser.arduinoml.kernel.behavioral;

import io.github.mosser.arduinoml.kernel.NamedElement;
import io.github.mosser.arduinoml.kernel.generator.Visitable;
import io.github.mosser.arduinoml.kernel.generator.Visitor;
import io.github.mosser.arduinoml.kernel.structural.AnalogSensor;

import java.util.ArrayList;
import java.util.List;


public class Mode implements NamedElement, Visitable {

    private String name;
    private List<State> states = new ArrayList<>();
    private List<Transition> transitions = new ArrayList<>();
    private State initState;
    private List<TransitionMode> transitionMode = new ArrayList<>();
    private boolean show;


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

    public List<TransitionMode> getTransitionMode() {
        return this.transitionMode;
    }

    /*public void setTransitionMode(List<TransitionMode> transitionMode) {
        this.transitionMode = transitionMode;
    }*/

    public void setTransitionMode(TransitionMode transitionMode) {
        this.transitionMode.add( transitionMode );
    }


    public void setTransitions(List<Transition> transitions) {
        this.transitions = transitions;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit( this );
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

}
