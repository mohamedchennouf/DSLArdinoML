
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
     // boolean timeDifferenceThreshold = millis() - timeGeneral
      if (analogRead(2) > threshold1){ // trnasition to modeB
         modeB("state_3");
      /*} else if (analogRead(pinNb) < threshold2) { // trnasition to modeC
        modeC(null);*/
      } else{ //transition of states happens here

         if (strcmp(currentStateName.c_str(), "state_1") == 0){
              state_1();
          }else if (strcmp(currentStateName.c_str(), "state_2") == 0){
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
              state_3();
          }else if (strcmp(currentStateName.c_str(), "state_4") == 0){
              state_4();
          }
        }
    }

    //void modeC(String currentStateName) {}


void loop() {
     //timeGeneral = millis();
     modeA("state_1");

    Serial.println(analogRead(2));
  // put your main code here, to run repeatedly:

}
