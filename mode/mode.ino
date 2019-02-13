
//const int timeGeneral = 0;     // the number of the pushbutton pin
int pinNb = 11;
long timerOn = 0;
long time = 0; 
long debounce = 200;
long sensorValue = 0;
int threshold1 = 60;
int threshold2 = 70;


void setup() {
  // put your setup code here, to run once:
    pinMode(9, INPUT);  // button1 [Sensor]
    pinMode(10, INPUT);  // button2 [Sensor]
    pinMode(12, OUTPUT); // led [Actuator]
    pinMode(8, OUTPUT); // buzzer [Actuator] 
    pinMode(pinNb, INPUT); //analog sensor
}
    

    void state_1(){
      digitalWrite(12,HIGH);
      digitalWrite(8,HIGH);
      if(digitalRead(9) == LOW && digitalRead(10) == HIGH ) {
        modeA(2);
    } else {
        modeA(1);
    }

    }

    void state_2(){
      digitalWrite(12,LOW);
      digitalWrite(8,LOW);
      if(digitalRead(9) == HIGH && digitalRead(10) == HIGH ) {
        modeA(1);
      } else {
        modeA(2);
      }
    }

    //we know thz analog sensors that r defined in this mode and therfor theirthresholdand it's pin
    void modeA(int currentStateNb) {
      boolean guard = millis() - time > debounce;
     // boolean timeDifferenceThreshold = millis() - timeGeneral 
      if ( guard && (analogRead(pinNb) > threshold1)){ // trnasition to modeB
         modeB(1);
      } else if ( guard && analogRead(pinNb) < threshold2) { // trnasition to modeC
        modeC(1);
      } else{ //transition of states happens here 
        switch (currentStateNb) {
          case 1:
            state_1();
            break;
          case 2:
            state_2();
            break;
          case 3:
            // another state
            break;
        }
      }
      
    }

     void modeB(int currentStateNb) {


    }

    void modeC(int currentStateNb) {}


void loop() {
     //timeGeneral = millis();
     modeA(1);

  // put your main code here, to run repeatedly:

}
