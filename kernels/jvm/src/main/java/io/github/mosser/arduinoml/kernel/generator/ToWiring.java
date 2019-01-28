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

		w("long time = 0; long debounce = 200;\n");

		for(State state: app.getStates()){
			state.accept(this);
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
	public void visit(State state) {
		w(String.format("void state_%s() {",state.getName()));
		for(Action action: state.getActions()) {
			action.accept(this);
		}

		if (state.getTransition() != null) {
			w("  boolean guard = millis() - time > debounce;");
			context.put(CURRENT_STATE, state);
			state.getTransition().accept(this);
			w("}\n");
		}

	}

	@Override
	public void visit(Transition transition) {
		String multipleSensorsEquation = "  if( guard && (" ;
		String sensorsRepresentation = "";

		System.out.println(transition.getLogicalOperator());
        System.out.println("Sensor : " + transition.getSensor());
        System.out.println("Value "+ transition.getValue());
        System.out.println("Next" + transition.getNext());


	//	if(transition.getLogicalOperator().size()>1){
		//LogicalOperator logicalOperator = transition.getLogicalOperator().get(0);

		//String logOpRepresentation = "";


		//if (logicalOperator.equals( LogicalOperator.AND_LOG )) logOpRepresentation = " && ";
		//else logOpRepresentation = " || ";
		int i = 0;

		for (Sensor sensor : transition.getSensor()) {//get Sensor-> liste des sensors
			multipleSensorsEquation += "digitalRead(" + sensor.getPin() + ") == " + transition.getValue().get(i);
			if(transition.getLogicalOperator().size() > i+1){ // if there is another condition
				multipleSensorsEquation += (transition.getLogicalOperator().get(++i).equals(LogicalOperator.AND_LOG )?" && " : " || ");
			}
		}

		multipleSensorsEquation += " )) {";



		w(multipleSensorsEquation);


		/*w(String.format("  if( digitalRead(%d) == %s && guard ) {",
				 transition.getSensor().getPin(),transition.getValue()));*/
		w("    time = millis();");
		w(String.format("    state_%s();",transition.getNext().getName()));
		w("  } else {");
		w(String.format("    state_%s();",((State) context.get(CURRENT_STATE)).getName()));
		w("  }");
	}

	@Override
	public void visit(Action action) {
		w(String.format("  digitalWrite(%d,%s);",action.getActuator().getPin(),action.getValue()));
	}

}
