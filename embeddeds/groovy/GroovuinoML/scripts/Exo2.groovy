sensor "button1" onPin 9
sensor "button2" onPin 10
actuator "led" pin 12
actuator "buzzer" pin 8

state "on" means "buzzer" becomes "high"
state "off" means "buzzer" becomes "low"

initial "off"

from "on" to "off" when "button1"  becomes "low" and "button2"  becomes "high"
from "off" to "on" when "button1"  becomes "high" and "button2"  becomes "high"

export "Switch!"



