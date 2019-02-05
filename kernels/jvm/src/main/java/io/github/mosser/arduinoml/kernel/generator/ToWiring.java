package io.github.mosser.arduinoml.kernel.generator;

import io.github.mosser.arduinoml.kernel.App;
import io.github.mosser.arduinoml.kernel.behavioral.*;
import io.github.mosser.arduinoml.kernel.structural.*;

/**
 * Quick and dirty visitor to support the generation of Wiring code
 */
public class ToWiring extends Visitor<StringBuffer> {

	private final static String CURRENT_STATE = "current_state";

	public ToWiring() {
		this.result = new StringBuffer();
	}

	private void w(String s) {
		result.append(String.format("%s\n",s));
	}

	@Override
	public void visit(App app) {
		w("// Wiring code generated from an ArduinoML model");
		w(String.format("// Application name: %s\n", app.getName()));

		w("void setup(){");
		for(Brick brick: app.getBricks()){
			brick.accept(this);
		}
		w("}\n");


		w("long timerOn = 0;");
		w("long time = 0; long debounce = 200;\n");



		for(Mode mode : app.getModes()) {
			mode.accept(this);
			for(State state: mode.getStates()){
				state.accept(this);
			}
			w("}\n\n");

		}



		if (app.getInitial() != null) {
			w("void loop() {");
			w(String.format("  state_%s();", app.getInitial().getName()));
			w("}");
		}

	}

	@Override
	public void visit(Actuator actuator) {
	 	w(String.format("  pinMode(%d, OUTPUT); // %s [Actuator]", actuator.getPin(), actuator.getName()));
	}


	@Override
	public void visit(Sensor sensor) {
		w(String.format("  pinMode(%d, INPUT);  // %s [Sensor]", sensor.getPin(), sensor.getName()));
	}

	@Override
	public void visit(SignalStuff signalStuff) {
		w("// Signal Stuff");
	}

	@Override
	public void visit(State state) {
		w(String.format("  void state_%s() {",state.getName()));

		if(state.getEmphasized() ) {
			w("  if(timerOn++ < 10000){");
			w("     digitalWrite(" + state.getEmphasizor().getPin() + "," + SIGNAL.HIGH.name() + ");");
			w("  }");
			w("  else{");
			w("     digitalWrite(" + state.getEmphasizor().getPin() + "," + SIGNAL.LOW.name() + ");");
			w("  }");

		}

		for(Action action: state.getActions()) {
			action.accept(this);
		}

		if (state.getTransition() != null) {
			w("    boolean guard = millis() - time > debounce;");
			context.put(CURRENT_STATE, state);
			state.getTransition().accept(this);
			w("  }\n");
		}else{
			w("  }");
		}
	}

	@Override
	public void visit(Transition transition) {
		String multipleSensorsEquation = transition.getSensor().isEmpty()?
				"    if(guard ":
				"    if( guard && (" ;
		int i = 0;
		for (Sensor sensor : transition.getSensor()) {//get Sensor-> liste des sensors
			multipleSensorsEquation += "digitalRead(" + sensor.getPin() + ") == " ;
			SIGNAL signal = transition.getValue().get(i);
			multipleSensorsEquation += (signal.equals(SIGNAL.HIGH) || signal.equals(SIGNAL.LOW))? signal : signal.getIntValue();
			if(transition.getLogicalOperator().size() > i){ // if there is another condition
				multipleSensorsEquation += (transition.getLogicalOperator().get(i++).equals(LogicalOperator.AND_LOG )?" && " : " || ");
			}
		}

		multipleSensorsEquation +=  transition.getSensor().isEmpty()? "){" :" )) {";

		w(multipleSensorsEquation);
		/*w(String.format("  if( digitalRead(%d) == %s && guard ) {",
				 transition.getSensor().getPin(),transition.getValue()));*/

		if(((State) context.get(CURRENT_STATE)).getEmphasized()){
			w("     timerOn = 0;");
		}
		w("      time = millis();");
		w(String.format("      state_%s();",transition.getNext().getName()));
		w("    } else {");
		w(String.format("      state_%s();",((State) context.get(CURRENT_STATE)).getName()));
		w("    }");
	}

	@Override
	public void visit(Action action) {
		w(String.format("    digitalWrite(%d,%s);",action.getActuator().getPin(),action.getValue()));
		/*w(String.format("  digitalWrite(%d,%s);",
				action.getActuator().getPin(),
				(action.getValue().equals(SIGNAL.HIGH) ||
				action.getValue().equals(SIGNAL.LOW))?
						action.getValue() : action.getValue().getIntValue()));*/

	}


	//************PARTIE MODE ************
	@Override
	public void visit(AnalogSensor analogSensor) {
		w(String.format("  analogSensor(%d,%s);", analogSensor.getThreshold(), analogSensor.getName()));
		/*w(String.format("  digitalWrite(%d,%s);",
				action.getActuator().getPin(),
				(action.getValue().equals(SIGNAL.HIGH) ||
				action.getValue().equals(SIGNAL.LOW))?
						action.getValue() : action.getValue().getIntValue()));*/

	}


	@Override
	public void visit(Mode mode) {
		w(String.format("mode(%s,%s) {\n",mode.getModeName(), mode.getAnalogSensor().getName()));
		/*w(String.format("  digitalWrite(%d,%s);",
				action.getActuator().getPin(),
				(action.getValue().equals(SIGNAL.HIGH) ||
				action.getValue().equals(SIGNAL.LOW))?
						action.getValue() : action.getValue().getIntValue()));*/

	}
}
