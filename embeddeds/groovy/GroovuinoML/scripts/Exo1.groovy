sensor "button1" onPin 9
actuator "led" pin 12
actuator "buzzer" pin 8

mode "jour" analogsensor "name" threshold 1


state "on" means "led" becomes "high" and "buzzer" becomes "high"
state "off" means "led" becomes "low" and "buzzer" becomes "low"

initial "off"

from "on" to "off" when "button1" becomes "low"
from "off" to "on" when "button1" becomes "high"




//mode "jour" contains "analog sensor" with "threshold"
//make "jour" becomes "night"





export "Switch!"




