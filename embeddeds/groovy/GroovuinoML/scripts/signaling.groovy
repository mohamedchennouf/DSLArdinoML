sensor "button1" onPin 9
actuator "led" pin 12
actuator "buzzer" pin 8

state "on" means "buzzer" becomes "high"
state "off" means "buzzer" becomes "low"

initial "off"

transition "t1" from "on" to "off" when "button1" becomes "low"
transition "t2" from "off" to "on" when "button1" becomes "high"

//we want this state to be emphisized signaling
signalstuff "on" with 3 bip "long" on "led"

export "Switch!"