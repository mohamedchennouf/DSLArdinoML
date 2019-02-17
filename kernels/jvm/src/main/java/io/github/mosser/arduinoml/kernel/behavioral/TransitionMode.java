package io.github.mosser.arduinoml.kernel.behavioral;

import io.github.mosser.arduinoml.kernel.generator.Visitable;
import io.github.mosser.arduinoml.kernel.generator.Visitor;
import io.github.mosser.arduinoml.kernel.structural.AnalogSensor;
import io.github.mosser.arduinoml.kernel.structural.SIGNAL;
import io.github.mosser.arduinoml.kernel.structural.Sensor;

import java.util.ArrayList;
import java.util.List;

public class TransitionMode implements Visitable {


    private Mode next;
    private Mode actual;
    private Sensor analogSensors;
    private double signalValue;
    private String signe;


    public Mode getNext() {
        return next;
    }

    public void setNext(Mode next) {
        this.next = next;
    }

    public Sensor getAnalogSensors() {
        return analogSensors;
    }

    public void setAnalogSensors(Sensor analogSensors) {
        this.analogSensors = analogSensors;
    }

    public double getSignalValue() {
        return signalValue;
    }

    public void setSignalValue(double signal) {
        this.signalValue = signal;
    }

    public Mode getActual() {
        return actual;
    }

    public void setActual(Mode actual) {
        this.actual = actual;
    }

    public String getSigne() {
        return signe;
    }

    public void setSigne(String signe) {
        this.signe = signe;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }


}