
//As a conceptual musician, Melania wants to define a state machine
//that,
// according to the activation of the buttons B1 and B2,
// follows different path in the state
//machine, producing different chunk of melody according to the user inputs.


sensor "button1" onPin 9
sensor "button2" onPin 10

actuator "led" pin 12
actuator "buzzer" pin 8

state "state1" means "led" becomes "high"
state "state2" means "buzzer" becomes "high"
state "state3" means "led" becomes "low"


state "off" means "buzzer" becomes "low" and "led" becomes "low"


initial "off"

//we want this state to be emphisized signaling
signalstuff "state1" with "buzzer"

from "state1" to "state3" when "button1" becomes "high"

from "off" to "state1" when "button1" becomes "high" and "button2" becomes "high"
from "off" to "state2" when "button1" becomes "high"
from "off" to "state3" when "button2" becomes "high"

from "state3" to "state3" when "button2" becomes "high"

from "off" to "state1" when "button1" becomes "low" and "button2" becomes "low"

export "Donald"