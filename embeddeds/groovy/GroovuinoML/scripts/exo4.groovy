sensor "button" onPin 9
actuator "led" pin 12
actuator "buzzer" pin 8

state "state1" means "buzzer" becomes "high"
state "state2" means "buzzer" becomes "low" and "led" becomes "high"
state "state3" means "led" becomes "low"

initial "state3"

transition "t1" from "state1" to "state2" when "button" becomes "high"
transition "t2" from "state2" to "state3" when "button" becomes "high"
transition "t3" from "state3" to "state1" when "button" becomes "high"


export "Switch!"