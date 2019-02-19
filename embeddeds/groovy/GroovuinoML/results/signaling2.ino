// Wiring code generated from an ArduinoML model
// Application name: Switch!


void setup(){
  pinMode(9, INPUT);  // button1 [Sensor]
  pinMode(12, OUTPUT); // led [Actuator]
  pinMode(8, OUTPUT); // buzzer [Actuator]
  Serial.begin(9600);
}

long timerOn = 0;
long time = 0; long debounce = 200;
long sensorValue = 0;



  void state_on() {
    digitalWrite(8,HIGH);
    boolean guard = millis() - time > debounce;
    if( guard && (digitalRead(9) == LOW )) {
      time = millis();
      state_off();
    } else {
      state_on();
    }
  }

  void state_off() {
	if(timerOn++ < 30000){
		digitalWrite(12,HIGH);
	}
	else{
		digitalWrite(12,LOW);
	}
    digitalWrite(8,LOW);
    boolean guard = millis() - time > debounce;
    if( guard && (digitalRead(9) == HIGH )) {
      time = millis();
      timerOn = 0;
      state_on();
    } else {
      state_off();
    }
  }

void loop() {
  state_off();
}