package main.groovy.groovuinoml.dsl

import groovuinoml.GrammarException
import io.github.mosser.arduinoml.kernel.behavioral.LogicalOperator
import io.github.mosser.arduinoml.kernel.behavioral.Mode
import io.github.mosser.arduinoml.kernel.structural.AnalogSensor
import main.groovy.groovuinoml.dsl.GroovuinoMLBinding
import sun.misc.Signal

import java.util.List;

import io.github.mosser.arduinoml.kernel.behavioral.Action
import io.github.mosser.arduinoml.kernel.behavioral.State
import io.github.mosser.arduinoml.kernel.structural.Actuator
import io.github.mosser.arduinoml.kernel.structural.Sensor
import io.github.mosser.arduinoml.kernel.structural.SIGNAL

abstract class GroovuinoMLBasescript extends Script {
	// sensor "name" pin n
	def sensor(String name) {
		[pin: { n -> ((GroovuinoMLBinding)this.getBinding()).getGroovuinoMLModel().createSensor(name, n) },
		 onPin: { n -> ((GroovuinoMLBinding)this.getBinding()).getGroovuinoMLModel().createSensor(name, n)}]
	}

	// actuator "name" pin n
	def actuator(String name) {
		[pin: { n -> ((GroovuinoMLBinding)this.getBinding()).getGroovuinoMLModel().createActuator(name, n) }]
	}

	// state "name" means actuator becomes signal [and actuator becomes signal]*n
	/*def state(String name) {
		List<Action> actions = new ArrayList<Action>()
		((GroovuinoMLBinding) this.getBinding()).getGroovuinoMLModel().createState(name, actions)
		// recursive closure to allow multiple and statements
		def closure
		closure = { actuator ->
			[becomes: { signal ->
				Action action = new Action()
				action.setActuator(actuator instanceof String ? (Actuator)((GroovuinoMLBinding)this.getBinding()).getVariable(actuator) : (Actuator)actuator)
				action.setValue(signal instanceof String ? (SIGNAL)((GroovuinoMLBinding)this.getBinding()).getVariable(signal) : (SIGNAL)signal)

				actions.add(action)
				[and: closure, or : closure]
			}]
		}
		[means: closure]
	}*/

	def inside(String modeName) {
		mode = modeName instanceof String ? (Mode)((GroovuinoMLBinding) this.getBinding()).getVariable(modeName) : (Mode) modeName
		//def state
		List<Action> actions = new ArrayList<Action>()
		[state : { name ->
		// recursive closure to allow multiple and statements
			def closure
			closure = { actuator ->
				[becomes: { signal ->
					Action action = new Action()
					action.setActuator(actuator instanceof String ? (Actuator)((GroovuinoMLBinding)this.getBinding()).getVariable(actuator) : (Actuator)actuator)
					action.setValue(signal instanceof String ? (SIGNAL)((GroovuinoMLBinding)this.getBinding()).getVariable(signal) : (SIGNAL)signal)

					actions.add(action)
					((GroovuinoMLBinding) this.getBinding()).getGroovuinoMLModel().createState(mode, name, actions)
					//mode.setStates()
					[and: closure, or : closure]
				}]
			}
			[means: closure]
		}]
		//mode.setStates()
	}

	// initial state
	def initial(state) {
		((GroovuinoMLBinding) this.getBinding()).getGroovuinoMLModel().setInitialState(state instanceof String ? (State)((GroovuinoMLBinding)this.getBinding()).getVariable(state) : (State)state)
	}


	def from(stateOrModeName1) { //stateOrModeName2
		List<Sensor> sensors = new ArrayList<Sensor>();
		List<SIGNAL> signales = new ArrayList<SIGNAL>();
		List<LogicalOperator> logicalOperator = new ArrayList<LogicalOperator>();
		int i = 0;
		[to: state = { stateOrModeName2 ->
			[when:  { sensor -> //boutton
				[becomes: signal = { signal ->
					sensorA = sensor instanceof String ? (Sensor) ((GroovuinoMLBinding) this.getBinding()).getVariable(sensor) : (Sensor) sensor
					signalA = signal instanceof String ? (SIGNAL) ((GroovuinoMLBinding) this.getBinding()).getVariable(signal) : (SIGNAL) signal
					signales.add(signalA)
					sensors.add(sensorA)

					stateOrModeObjetA = ((GroovuinoMLBinding) this.getBinding()).getVariable(stateOrModeName1)
					stateOrModeObjetB = ((GroovuinoMLBinding) this.getBinding()).getVariable(stateOrModeName2)

					//checking if stateOrModeObjetA and stateOrModeObjetB are both states
					try {
						stateA = (State) stateOrModeObjetA
						stateB = (State) stateOrModeObjetB
						((GroovuinoMLBinding) this.getBinding()).getGroovuinoMLModel().createTransitionState(
								stateA, //stateA, //stateOrModeA,//stateOrModeNameA,
								stateB, //stateB, //stateOrModeB,//stateOrModeNameB,
								sensors,
								signales,
								logicalOperator
						)
					} catch (Exception e){}

					try {
						modeA = (Mode) stateOrModeObjetA
						modeb = (Mode) stateOrModeObjetB
						((GroovuinoMLBinding) this.getBinding()).getGroovuinoMLModel().createTransitionMode(
								modeA, //stateA, //stateOrModeA,//stateOrModeNameA,
								modeB, //stateB, //stateOrModeB,//stateOrModeNameB,
								sensors,
								signales,
								logicalOperator
						)
					} catch (Exception e){}

					[and : { sensor2 ->
						[becomes: signal2 = { signal2 ->
							signalB = signal2 instanceof String ? (SIGNAL) ((GroovuinoMLBinding) this.getBinding()).getVariable(signal2) : (SIGNAL) signal2
							sensorB = sensor2 instanceof String ? (Sensor) ((GroovuinoMLBinding) this.getBinding()).getVariable(sensor2) : (Sensor) sensor2
							signales.add(signalB)
							sensors.add(sensorB)
							logicalOperator.add(LogicalOperator.AND_LOG);
						}]
					}, or: { sensor3 ->
						[becomes: signal2 = { signal3 ->
							signalC = signal3 instanceof String ? (SIGNAL) ((GroovuinoMLBinding) this.getBinding()).getVariable(signal3) : (SIGNAL) signal3
							sensorB = sensor3 instanceof String ? (Sensor) ((GroovuinoMLBinding) this.getBinding()).getVariable(sensor3) : (Sensor) sensor3
							signales.add(signalC)
							sensors.add(sensorB)
							logicalOperator.add(LogicalOperator.OR_LOG);
						}]
					}]
/*
					//check if both stateOrModeName1 and stateOrModeName2 are both models or states
					if (stateOrModeObjetA.getClass().equals(stateOrModeObjetB.getClass())) {

						if (stateOrModeObjetA.metaClass   getClass().equals("State")) {
						//stateOrModeA = stateOrModeObjetA.getClass().equals("Mode") ? (Mode) stateOrModeObjetA : (State) stateOrModeObjetA
						//stateOrModeB = stateOrModeObjetB.getClass().equals("State") ? (Mode) stateOrModeObjetB : (State) stateOrModeObjetB


						//TO DELETE
						stateA = stateOrModeName1 instanceof String ? (State) ((GroovuinoMLBinding) this.getBinding()).getVariable(stateOrModeName1) : (State) stateOrModeName1
						stateB = stateOrModeName2 instanceof String ? (State) ((GroovuinoMLBinding) this.getBinding()).getVariable(stateOrModeName2) : (State) stateOrModeName2

						//TO CHANGE stateA, stateB
						//modeNameA = stateA.getMode().getModeName()
						//modeNameB = stateB.getMode().getModeName()

						//if (modeNameA.equals(modeNameB)) {

						} else {
							//the case when user created the transition that goes from state in one mode to state in another mode
							throw new GrammarException(modeNameA)
							//System.out.println("Sorry, but you grammar: " + e + " is not correct");
							//e.printStackTrace()
						}
					/*} else {
						throw new GrammarException("both values have to be modules or states ")
					}*/
				}]
			}]
		}]
	}
	//signalstuff state1

	def signalstuff(state1){
	/*	sensorA = sensor instanceof String ? (Sensor) ((GroovuinoMLBinding) this.getBinding()).getVariable(sensor) : (Sensor) sensor
		signalA = signal instanceof String ? (SIGNAL) ((GroovuinoMLBinding) this.getBinding()).getVariable(signal) : (SIGNAL) signal
*/

		[with : actuator = {actuator1 ->
			((GroovuinoMLBinding) this.getBinding()).getGroovuinoMLModel().makeEmphasized(state1,actuator1);
		}]

	}

	def analogsensor(analogSensorName) {
		[threshold : { thresholdValue ->
			//AnalogSensor analogSensor = new AnalogSensor()
			((GroovuinoMLBinding) this.getBinding()).getGroovuinoMLModel().createAnalogSensor( analogSensorName, thresholdValue)

		}]
	}

	def mode(modeName) {
		Mode mode = new Mode()
		((GroovuinoMLBinding) this.getBinding()).getGroovuinoMLModel().createMode(modeName)
	}


	// export name
	def export(String name) {
		println(((GroovuinoMLBinding) this.getBinding()).getGroovuinoMLModel().generateCode(name).toString())
	}

	// disable run method while running
	int count = 0
	abstract void scriptBody()
	def run() {
		if(count == 0) {
			count++
			scriptBody()
		} else {
			println "Run method is disabled"
		}
	}



}