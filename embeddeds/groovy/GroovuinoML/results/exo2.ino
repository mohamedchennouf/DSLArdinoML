// Wiring code generated from an ArduinoML model
// Application name: Switch!

void setup(){
  pinMode(9, INPUT);  // button1 [Sensor]
  pinMode(10, INPUT);  // button2 [Sensor]
  pinMode(12, OUTPUT); // led [Actuator]
  pinMode(8, OUTPUT); // buzzer [Actuator]
}

long time = 0; long debounce = 200;

void state_on() {
  digitalWrite(8,HIGH);
  boolean guard = millis() - time > debounce;
  if( guard && (digitalRead(9) == LOW || digitalRead(10) == LOW )) {
    time = millis();
    state_off();
  } else {
    state_on();
  }
}

void state_off() {
  digitalWrite(8,LOW);
  boolean guard = millis() - time > debounce;
  if( guard && (digitalRead(9) == HIGH && digitalRead(10) == HIGH )) {
    time = millis();
    state_on();
  } else {
    state_off();
  }
}

void loop() {
  state_off();
}

