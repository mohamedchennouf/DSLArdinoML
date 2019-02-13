sensor "button1" onPin 9
sensor "button2" onPin 10
actuator "led" pin 12
actuator "buzzer" pin 8
analogsensor "analogsensor1" threshold 1
analogsensor "analogsensor2" threshold 3

state "on" means "led" becomes "high" and "buzzer" becomes "high"
state "off" means "led" becomes "low" and "buzzer" becomes "low"
state "on1" means "led" becomes "high" and "buzzer" becomes "high"
state "off1" means "led" becomes "low" and "buzzer" becomes "low"

initial "off"

transition "t1" from "on" to "off" when "button1" becomes "low"  and "button2"  becomes "high"
transition "t2" from "off" to "on" when "button1" becomes "high"  and "button2"  becomes "high"
transition "t3" from "on1" to "off" when "button1" becomes "low"  and "button2"  becomes "high"
transition "t4" from "off" to "on" when "button1" becomes "high"  and "button2"  becomes "high"

mode "jour" states (["on", "off"]) transi (["t1","t2"]) init "off"
mode "nuit" states (["on1", "off", "on"]) transi (["t3","t4"]) init "on1"


from ""




export "Switch!"




