package io.github.mosser.arduinoml.kernel.behavioral;

import io.github.mosser.arduinoml.kernel.generator.Visitable;
import io.github.mosser.arduinoml.kernel.generator.Visitor;
import io.github.mosser.arduinoml.kernel.structural.*;

import java.util.ArrayList;
import java.util.List;

public class Transition implements Visitable {

	private State next;
	private List<Sensor> sensors = new ArrayList<Sensor>();
	private List<SIGNAL> value;
	private List<LogicalOperator> logicalOperator;


	public State getNext() {
		return next;
	}

	public void setNext(State next) {
		this.next = next;
	}

	public List<Sensor> getSensor() {
		return sensors;
	}

	public void setSensors(List<Sensor> sensors) {
		this.sensors = sensors;
	}

	/*public void setSensor(Sensor sensor) {
		this.sensors.add(sensor);
	}*/

	public List<SIGNAL> getValue() {
		return value;
	}

	public void setValue(List<SIGNAL> value) {
		this.value = value;
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
