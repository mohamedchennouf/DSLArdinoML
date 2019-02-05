package groovuinoml.dsl;

import java.util.*;


import groovy.lang.Binding;
import io.github.mosser.arduinoml.kernel.App;
import io.github.mosser.arduinoml.kernel.behavioral.*;
import io.github.mosser.arduinoml.kernel.generator.ToWiring;
import io.github.mosser.arduinoml.kernel.generator.Visitor;
import io.github.mosser.arduinoml.kernel.structural.*;

public class GroovuinoMLModel {
	private List<Brick> bricks;
	private List<State> states;
	private State initialState;
	private List<Mode> modes;
	//private List<AnalogSensor> analogSensors;


	private Binding binding;
	
	public GroovuinoMLModel(Binding binding) {
		this.bricks = new ArrayList<Brick>();
		this.states = new ArrayList<State>();
		this.modes = new ArrayList<Mode>();
		//this.analogSensors = new ArrayList<AnalogSensor>();
		this.binding = binding;
	}
	
	public void createSensor(String name, Integer pinNumber) {
		Sensor sensor = new Sensor();
		sensor.setName(name);
		sensor.setPin(pinNumber);
		this.bricks.add(sensor);
		this.binding.setVariable(name, sensor);
//		System.out.println("> sensor " + name + " on pin " + pinNumber);
	}
	
	public void createActuator(String name, Integer pinNumber) {
		Actuator actuator = new Actuator();
		actuator.setName(name);
		actuator.setPin(pinNumber);
		this.bricks.add(actuator);
		this.binding.setVariable(name, actuator);
	}
	
	public void createState(Mode mode, String name, List<Action> actions) {
		State state = new State();
		state.setName(name);
		state.setActions(actions);
		this.states.add(state);
		this.binding.setVariable(name, state);
		mode.setState( state );
	}

	public void makeEmphasized(String statename,String actuator){
		for (State state1 : states){
			if (state1.getName().equals(statename))
				state1.setEmphasized(true);
				for(Brick actuator1 : bricks){
					if(actuator1.getName().equals(actuator)){
						state1.setEmphasizor((Actuator)actuator1);
					}
				}
		}
	}

	public void createTransition(State from, State to, List<Sensor> sensors, List<SIGNAL> value,  List<LogicalOperator>  logicalOperator) {
		Transition transition = new Transition();
		transition.setNext(to);
		transition.setSensors(sensors); //for the list of sensors
		transition.setValue(value);
		transition.setLogicalOperator(logicalOperator);
		from.setTransition(transition);
	}

	////////****************
	public void createMode(String modeName, String analogSensorname, Integer threshold) {
		AnalogSensor analogSensor = new AnalogSensor();
		analogSensor.setName( analogSensorname );
		analogSensor.setThreshold( threshold );

		Mode mode = new Mode();
		mode.setModeName( modeName );
		mode.setAnalogSensor( analogSensor );

		this.modes.add(mode);
		this.binding.setVariable(modeName,mode);
		this.bricks.add( analogSensor );
	}
	
	public void setInitialState(State state) {
		this.initialState = state;
	}
	
	@SuppressWarnings("rawtypes")
	public Object generateCode(String appName) {
		App app = new App();
		app.setName(appName);
		app.setBricks(this.bricks);
		app.setStates(this.states);
		app.setInitial(this.initialState);
		//app.setAnalogSensor( this.analogSensors );
		app.setMode( this.modes );
		Visitor codeGenerator = new ToWiring();
		app.accept(codeGenerator);
		
		return codeGenerator.getResult();
	}



}
