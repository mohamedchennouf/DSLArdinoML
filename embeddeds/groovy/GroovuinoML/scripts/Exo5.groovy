
//As a conceptual musician, Melania wants to define a state machine
//that,
// according to the activation of the buttons B1 and B2,
// follows different path in the state
//machine, producing different chunk of melody according to the user inputs.


sensor "button1" onPin 9
sensor "button2" onPin 10

actuator "led" pin 12
actuator "buzzer" pin 8

state "sound1" means "buzzer" becomes "F1"
state "sound2" means "buzzer" becomes "B1"
state "sound3" means "buzzer" becomes "C1"
state "sound4" means "buzzer" becomes "E1"
//state "chunk2" means "buzzer" becomes B1 during 10 then C1
//state "A1" means "buzzer" becomes 55 then 110
//state "B1" means "buzzer" becomes B1
state "off" means "buzzer" becomes "low"

//state "state1" means "buzzer" becomes "high"
//state "state2" means "buzzer" becomes "low" and "led" becomes "high"
//state "state3" means "led" becomes "low"


initial "sound2"

play "sound1" then "sound2"

from "sound2" to "sound3" when "button1" becomes "high"
from "sound3" to "sound4" when "button2" becomes "high"
from "sound4" to "sound4" when "button1" becomes "high"

export "Melody"