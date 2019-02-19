// Wiring code generated from an ArduinoML model
// Application name: Switch!



//******** signaling stuff *********
void mode_jour(String currentStateName);
void state_on();
void state_off();
void mode_nuit(String currentStateName);
void state_on1();
void state_off1();


void setup(){
  pinMode(9, INPUT);  // button1 [Sensor]
  pinMode(10, INPUT);  // button2 [Sensor]
  pinMode(1, INPUT); // analogsensor [AnalogSensor]
  pinMode(12, OUTPUT); // led [Actuator]
  pinMode(8, OUTPUT); // buzzer [Actuator]
  Serial.begin(9600);
}

long timerOn = 0;
long time = 0; long debounce = 200;
long sensorValue = 0;



  void mode_jour(String currentStateName) {
    if(analogRead(1) > 1.000000){
      mode_nuit("state_on1");
    }
    else{
      if(strcmp(currentStateName.c_str(), "state_on") == 0){
        state_on();
      } else if(strcmp(currentStateName.c_str(), "state_off") == 0){
        state_off();
      }
    }
  }

  void mode_nuit(String currentStateName) {
    if(analogRead(1) < 1.000000){
      mode_jour("state_off");
    }
    else{
      if(strcmp(currentStateName.c_str(), "state_on1") == 0){
        state_on1();
      } else if(strcmp(currentStateName.c_str(), "state_off1") == 0){
        state_off1();
      }
    }
  }

  void state_on() {
	if(timerOn++ < 20){
		digitalWrite(8,HIGH);
	}
	else if(timerOn >= 20 && timerOn < 40){
		digitalWrite(8,LOW);
		timerOn++;
	}
	else if(timerOn >= 40 && timerOn < 60){
		digitalWrite(8,HIGH);
		timerOn++;
	}
	else if(timerOn >= 60 && timerOn < 80){
		digitalWrite(8,LOW);
		timerOn++;
	}
	else if(timerOn >= 80 && timerOn < 100){
		digitalWrite(8,HIGH);
		timerOn++;
	}
	else{
		digitalWrite(8,LOW);
	}
    digitalWrite(12,HIGH);
    if( (digitalRead(9) == LOW )) {
      timerOn = 0;
      mode_jour("state_off");
    } else {
      mode_jour("state_on");
    }
  }

  void state_off() {
    digitalWrite(12,LOW);
    if( (digitalRead(9) == HIGH )) {
      mode_jour("state_on");
    } else {
      mode_jour("state_off");
    }
  }





  void state_on1() {
    digitalWrite(12,HIGH);
    digitalWrite(8,HIGH);
    if( (digitalRead(9) == LOW && digitalRead(10) == LOW )) {
      mode_nuit("state_off1");
    } else {
      mode_nuit("state_on1");
    }
  }

  void state_off1() {
    digitalWrite(12,LOW);
    digitalWrite(8,LOW);
    if( (digitalRead(9) == HIGH && digitalRead(10) == HIGH )) {
      mode_nuit("state_on1");
    } else {
      mode_nuit("state_off1");
    }
  }





void loop() {
  mode_nuit("state_on1");
}