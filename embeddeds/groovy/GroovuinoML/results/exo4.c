// Wiring code generated from an ArduinoML model
// Application name: Switch!

void setup(){
  pinMode(9, INPUT);  // button [Sensor]
  pinMode(12, OUTPUT); // led [Actuator]
  pinMode(8, OUTPUT); // buzzer [Actuator]
}

long time = 0; long debounce = 200;

void state_state1() {
  digitalWrite(8,HIGH);
  boolean guard = millis() - time > debounce;
  if( guard && (digitalRead(9) == HIGH )) {
    time = millis();
    state_state2();
  } else {
    state_state1();
  }
}

void state_state2() {
  digitalWrite(8,LOW);
  digitalWrite(12,HIGH);
  boolean guard = millis() - time > debounce;
  if( guard && (digitalRead(9) == HIGH )) {
    time = millis();
    state_state3();
  } else {
    state_state2();
  }
}

void state_state3() {
  digitalWrite(12,LOW);
  boolean guard = millis() - time > debounce;
  if( guard && (digitalRead(9) == HIGH )) {
    time = millis();
    state_state1();
  } else {
    state_state3();
  }
}

void loop() {
  state_state3();
}

