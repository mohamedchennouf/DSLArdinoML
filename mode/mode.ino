
//const int timeGeneral = 0;     // the number of the pushbutton pin
long timerOn = 0;
long time = 0; 
long debounce = 200;
long sensorValue = 0;
int threshold1 = 60;
int threshold2 = 70;


void setup() {
  // put your setup code here, to run once:
    pinMode(9, INPUT);  // button1 [Sensor]
    pinMode(12, OUTPUT); // led [Actuator]
    pinMode(11, INPUT); //analog sensor
}
    

    void state_1(){
      digitalWrite(12,HIGH);
      if(digitalRead(9) == LOW) {
        modeA("state2");
    } else {
        modeA("state1");
    }

    }

    void state_2(){
      digitalWrite(12,LOW);
      if(digitalRead(9) == HIGH) {
        modeA("state1");
      } else {
        modeA("state2");
      }
    }

    //we know thz analog sensors that r defined in this mode and therfor theirthresholdand it's pin
    void modeA(String currentStateName) {
     // boolean timeDifferenceThreshold = millis() - timeGeneral 
      if (analogRead(pinNb) > threshold1){ // trnasition to modeB
         modeB(state1);
      /*} else if (analogRead(pinNb) < threshold2) { // trnasition to modeC
        modeC(null);*/
      } else{ //transition of states happens here 
        switch (currentStateName) {
          case "state1":
            state_1();
            break;
          case "state2":
            state_2();
            break;
          case "state3":
            // another state
            break;
        }
      }
      
    }

     void modeB(String currentStateName) {
           // boolean timeDifferenceThreshold = millis() - timeGeneral 
      if (analogRead(pinNb) < threshold1){ // trnasition to modeB
         modeA(state1);
      } else{ //transition of states happens here 
        switch (currentStateName) {
          case "state1":
            state_1();
            break;
          case "state2":
            state_2();
            break;
          case "state3":
            // another state
            break;
        }


    }

    //void modeC(String currentStateName) {}


void loop() {
     //timeGeneral = millis();
     modeA(1);

  // put your main code here, to run repeatedly:

}
