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
	private ArrayList<Mode> modes;
	private List<Transition> transitions;


	private Binding binding;
	
	public GroovuinoMLModel(Binding binding) {
		this.bricks = new ArrayList<>();
		this.states = new ArrayList<>();
		this.modes = new ArrayList<>();
		this.transitions = new ArrayList<>();
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
	
	public void createState(String name, List<Action> actions) {
		try{
			this.binding.getVariable(name);
		} catch (groovy.lang.MissingPropertyException e) {
			State state = new State();
			state.setName( name );
			state.setActions( actions );
			this.states.add(state);
			this.binding.setVariable( name, state );
		}
	}

	public void makeEmphasized(String statename,String actuator){
		/*for (State state1 : states){
			if (state1.getName().equals(statename))
				state1.setEmphasized(true);
				for(Brick actuator1 : bricks){
					if(actuator1.getName().equals(actuator)){
						state1.setEmphasizor((Actuator)actuator1);
					}
				}
		}*/
	}

	// List<Sensors>
	public void createTransition(String nameTransition, State from, State to, List<Sensor> sensors, List<SIGNAL> value,  List<LogicalOperator>  logicalOperator) {
		Transition transition = new Transition();
		transition.setName(nameTransition);
		transition.setNext(to);
		transition.setSensors(sensors); //for the list of sensors
		transition.setValue(value);
		transition.setLogicalOperator(logicalOperator);
		from.setTransition(transition);
		transitions.add(transition);
	}

	public void createTransitionMode(Mode from, Mode to, List<Sensor> sensors, List<SIGNAL> value,  List<LogicalOperator>  logicalOperator) {
		/*Transition transition = new Transition();
		transition.setNext(to);
		transition.setSensors(sensors); //for the list of sensors
		transition.setValue(value);
		transition.setLogicalOperator(logicalOperator);
		//from.setTransition(transition);*/
	}

	////////****************
	public void createMode(String name, ArrayList<String> states, ArrayList<String> transitions) {
		Mode mode = new Mode();
		//set Name
		mode.setModeName(name);
		//set states
		ArrayList<State> myStates = new ArrayList<>();
		for(String state : states){
			myStates.add(getState(state));
		}
		mode.setStates(myStates);
		// set transitions
		ArrayList<Transition> myTransitions = new ArrayList<>();
		for(String transition : transitions){
			myTransitions.add(getTransition(transition));
		}
		mode.setTransitions(myTransitions);
		this.modes.add(mode);
		this.binding.setVariable(name,mode);
	}

	public void createAnalogSensor(String analogSensorname, Integer threshold) {
		AnalogSensor analogSensor = new AnalogSensor();
		analogSensor.setName( analogSensorname );
		analogSensor.setThreshold( threshold );
		this.bricks.add( analogSensor );
		this.binding.setVariable(analogSensorname,analogSensor);
	}


		public void setInitialState(State state) {
		this.initialState = state;
	}
	
	@SuppressWarnings("rawtypes")
	public Object generateCode(String appName) {
		App app = new App();
		app.setName(appName);
		app.setBricks(this.bricks);
		//app.setStates(this.states);
		app.setInitial(this.initialState);
		//app.setAnalogSensor( this.analogSensors );
		app.setMode( this.modes );
		Visitor codeGenerator = new ToWiring();
		app.accept(codeGenerator);
		
		return codeGenerator.getResult();
	}


	public State getState(String name){
		State mystate = new State();
		for(State state : this.states){
			if(state.getName().equals(name)){
				mystate = state;
			}
		}
		return mystate;
	}

	public Transition getTransition(String name){
		System.out.print(this.transitions.size());
		Transition myTransition = new Transition();
		for(Transition transition : this.transitions){
			if(transition.getName().equals(name)){
				myTransition = transition;
			}
		}
		System.out.print("transition name "+ myTransition.getName());
		return myTransition;
	}



}
