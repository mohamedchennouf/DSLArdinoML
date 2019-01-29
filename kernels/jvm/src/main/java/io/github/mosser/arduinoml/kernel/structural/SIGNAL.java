package io.github.mosser.arduinoml.kernel.structural;

public enum SIGNAL {

	HIGH,
	LOW,
	C1 (49),
	E1 (41),
	A1 (55),
	G1 (49),
	B1 (62),
	F1 (44),
	;

	int note;
	SIGNAL(){}
	SIGNAL(int i) {
	}

	public int intValue(){
		return note;
	}

}
