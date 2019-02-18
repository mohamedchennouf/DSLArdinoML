
//const int timeGeneral = 0;     // the number of the pushbutton pin
long timerOn = 0;
long time = 0;
long debounce = 200;
long sensorValue = 0;
int threshold1 = 60;
int threshold2 = 70;

void modeB(String currentStateName);
void state_1();
void state_2();
void state_3();
void state_4();

void setup() {
  // put your setup code here, to run once:
    pinMode(9, INPUT);  // button1 [Sensor]
    pinMode(12, OUTPUT); // led [Actuator]
    pinMode(2, INPUT); //analog sensor
    pinMode(11, OUTPUT);
  Serial.begin(9600);
}


    void state_1(){
      //Serial.println(" state : state1 ");

      digitalWrite(12,HIGH);
      if(digitalRead(9) == HIGH) {
        modeA("state_2");
    } else {
        modeA("state_1");
    }

    }

    void state_2(){
      digitalWrite(12,LOW);
      if(digitalRead(9) == HIGH) {
        modeA("state_1");
      } else {
        modeA("state_2");
      }
    }

    void state_3(){
      digitalWrite(11,HIGH);
      if(digitalRead(9) == HIGH) {
        modeB("state_4");
      } else {
        modeB("state_3");
      }
    }

     void state_4(){
      digitalWrite(11,LOW);
      if(digitalRead(9) == LOW) {
        modeB("state_3");
      } else {
        modeB("state_4");
      }
    }

    //we know thz analog sensors that r defined in this mode and therfor theirthresholdand it's pin
    void modeA(String currentStateName) {
     // Serial.println("mode : modeA ");
  //    Serial.println(analogRead(2));
     // boolean timeDifferenceThreshold = millis() - timeGeneral
      if (analogRead(2) > threshold1){ // trnasition to modeB
         modeB("state_3");
      /*} else if (analogRead(pinNb) < threshold2) { // trnasition to modeC
        modeC(null);*/
      } else{ //transition of states happens here

         if (strcmp(currentStateName.c_str(), "state_1") == 0){
               Serial.print("mode : modeA ; ");  //if logging the mode
               Serial.print("state : state_1 ; "); //if logging the state
               Serial.print("sensor : ");     // if logging the sensors
               Serial.print(analogRead(2)); // 3 lines for each sensor


               Serial.println(""); //needed for parsing
              state_1();
          }else if (strcmp(currentStateName.c_str(), "state_2") == 0){
               Serial.print("mode : modeA ; ");
               Serial.print("state : state_2 ; ");
               Serial.print("sensor : ");
               Serial.print(analogRead(2));
               Serial.println("");
              state_2();
          }
      }

    }

     void modeB(String currentStateName) {
           // boolean timeDifferenceThreshold = millis() - timeGeneral
      if (analogRead(2) < threshold1){ // trnasition to modeB
         modeA("state_1");
      } else{ //transition of states happens here
          if (strcmp(currentStateName.c_str(), "state_3") == 0){
               Serial.print("mode : modeB ; ");
               Serial.print("state : state_3 ; ");
               Serial.print("sensor : ");
               Serial.print(analogRead(2));

               Serial.println("");
              state_3();
          }else if (strcmp(currentStateName.c_str(), "state_4") == 0){
               Serial.print("mode : modeB ; ");
               Serial.print("state : state_4 ; ");
               Serial.print("sensor : ");
               Serial.print(analogRead(2));

               //   Serial.print("sensor " + analogRead(2) + "; ");
               Serial.println("");
              state_4();
          }
        }

    }

    //void modeC(String currentStateName) {}


void loop() {
     //timeGeneral = millis();
     modeA("state_1");

  // put your main code here, to run repeatedly:


}