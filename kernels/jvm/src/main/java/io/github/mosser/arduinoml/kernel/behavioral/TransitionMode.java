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
    private List<AnalogSensor> analogSensors = new ArrayList<AnalogSensor>();
    private List<SIGNAL> value;
    private List<LogicalOperator> logicalOperator = new ArrayList<>();


    public Mode getNext() {
        return next;
    }

    public void setNext(Mode next) {
        this.next = next;
    }

    public List<AnalogSensor> getAnalogSensors() {
        return analogSensors;
    }

    public void setAnalogSensors(List<AnalogSensor> analogSensors) {
        this.analogSensors = analogSensors;
    }

    public List<SIGNAL> getValue() {
        return value;
    }

    public void setValue(List<SIGNAL> value) {
        this.value = value;
    }

    public void setValue(SIGNAL value) {
        this.value.add( value );
    }


    public  List<LogicalOperator>  getLogicalOperator() {
        return logicalOperator;
    }

    public void setLogicalOperator( List<LogicalOperator>  logicalOperator) {
        this.logicalOperator = logicalOperator;
    }


    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }


}
