package io.github.mosser.arduinoml.kernel.structural;

public enum SIGNAL {

	HIGH,
	LOW;


	int note;

	SIGNAL(int note){
		this.note = note;
	}

	SIGNAL() {}

	public int getIntValue(){
		return note;
	}

}
