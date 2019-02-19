package io.github.mosser.arduinoml.kernel.behavioral;

import io.github.mosser.arduinoml.kernel.NamedElement;
import io.github.mosser.arduinoml.kernel.generator.Visitable;
import io.github.mosser.arduinoml.kernel.generator.Visitor;
import io.github.mosser.arduinoml.kernel.structural.Actuator;

import java.util.ArrayList;
import java.util.List;

public class State implements NamedElement, Visitable {

	private String name;
	private List<Action> actions = new ArrayList<Action>();
	//private boolean emphasized = false;
	//private Actuator emphasizor = new Actuator();
	private Mode mode = null;
	private Transition transition;
	private Signaling signaling;
	private boolean show;


	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public List<Action> getActions() {
		return actions;
	}

	public void setActions(List<Action> actions) {
		this.actions = actions;
	}


	public Transition getTransition() {
		return transition;
	}

	public void setTransition(Transition transition) {
		this.transition = transition;
	}

	public Signaling getSignaling() {
		return signaling;
	}

	public void setSignaling(Signaling signaling) {
		this.signaling = signaling;
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	/*public void setEmphasized(boolean emphasized) {
		this.emphasized = emphasized;
	}

	public boolean getEmphasized() {
		return this.emphasized;
	}

	public Actuator getEmphasizor() {
		return emphasizor;
	}

	public void setEmphasizor(Actuator emphasizor) {
		this.emphasizor = emphasizor;
	}*/

	public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
