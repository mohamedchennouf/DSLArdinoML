sensor "button1" onPin 9
actuator "led" pin 12
actuator "buzzer" pin 8

state "on" means "buzzer" becomes "high"
state "off" means "buzzer" becomes "low"

initial "off"

//we want this state to be emphisized signaling
signalstuff "on" with "led"

from "on" to "off" when "button1" becomes "low"
from "off" to "on" when "button1" becomes "high"

export "Switch!"



