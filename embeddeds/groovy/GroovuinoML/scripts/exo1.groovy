sensor "button1" onPin 9
actuator "led" pin 12
actuator "buzzer" pin 8

state "on" means "led" becomes "high" and "buzzer" becomes "high"
state "off" means "led" becomes "low" and "buzzer" becomes "low"

initial "off"

transition "t1" from "on" to "off" when "button1" becomes "low"
transition "t2" from "off" to "on" when "button1" becomes "high"

export "Switch!"