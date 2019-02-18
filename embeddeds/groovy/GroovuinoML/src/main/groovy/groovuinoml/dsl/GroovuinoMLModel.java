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
	private Mode initialMode;
	private State initialState;
	private ArrayList<Mode> modes;
	private List<AnalogSensor> analogSensors;



	private Binding binding;
	
	public GroovuinoMLModel(Binding binding) {
		this.bricks = new ArrayList<>();
		this.states = new ArrayList<>();
		this.modes = new ArrayList<>();
		this.analogSensors = new ArrayList<>(  );
		this.binding = binding;
	}
	
	public void createSensor(String name, Integer pinNumber) {
		Sensor sensor = new Sensor();
		sensor.setName(name);
		sensor.setPin(pinNumber);
		this.bricks.add(sensor);
		this.binding.setVariable(name, sensor);
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
		transition.setActualState( from );
		from.setTransition(transition);
		this.binding.setVariable( nameTransition, transition );
		//transitions.add(transition);
	}


	public void createTransitionMode(Mode mode1, Mode mode2, AnalogSensor analogSensor, String signe) {
		TransitionMode transitionMode = new TransitionMode();
		transitionMode.setNext(mode2);
		transitionMode.setAnalogSensors( analogSensor ); //for the list of sensors
		transitionMode.setSigne( signe );
		transitionMode.setActual( mode1 );
		mode1.setTransitionMode( transitionMode );
		//this.transitionModes.add( transitionMode );

	}


	public void createMode(String modeName, ArrayList<State> states, ArrayList<Transition> transitions, State initState) {
		Mode mode = new Mode();
		mode.setName(modeName);

		//set states
		mode.setStates(states);

		// set transitions
		mode.setTransitions(transitions);

		mode.setInitState(initState);

		//associate each state with mode
		for (State state : states) {
			state.setMode( mode );
		}

		this.modes.add(mode);
		this.binding.setVariable(modeName,mode);
	}

	public void createAnalogSensor(String analogSensorname, int pin) {
		AnalogSensor analogSensor = new AnalogSensor();
		analogSensor.setName( analogSensorname );
		analogSensor.setPin(pin);
		this.bricks.add( analogSensor );
		this.binding.setVariable(analogSensorname,analogSensor);
	}


	public void setInitialMode(Mode initialMode) {
			this.initialMode = initialMode;
	}

	public void setInitialState(State initialState) {
		this.initialState = initialState;
	}
	
	@SuppressWarnings("rawtypes")
	public Object generateCode(String appName) {
		App app = new App();
		app.setName(appName);
		app.setBricks(this.bricks);
		//app.setStates(this.states);
		app.setInitialMode(this.initialMode);
		app.setInitialState( this.initialState );
		//app.setAnalogSensor( this.analogSensors );
		app.setMode( this.modes );
		app.setAnalogSensor( this.analogSensors );
		Visitor codeGenerator = new ToWiring();
		app.accept(codeGenerator);
		
		return codeGenerator.getResult();
	}





}
