sensor "button" onPin 9
actuator "led" pin 12

state "on" means "led" becomes "high"
state "off" means "led" becomes "low"

initial "off"

transition "t1" from "on" to "off" when "button" becomes "high"
transition "t2" from "off" to "on" when "button" becomes "high"


export "Switch!"