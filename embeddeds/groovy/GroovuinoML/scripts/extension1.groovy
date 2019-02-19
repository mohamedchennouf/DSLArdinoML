sensor "button" onPin 9
actuator "led" pin 12
actuator "buzzer" pin 8

state "started" means "buzzer" becomes "high"
state "middle" means "buzzer" becomes "low" and "led" becomes "high"
state "stop" means "led" becomes "low"

initial "started"

transition "t1" from "started" to "middle" when "button" becomes "high"
transition "t2" from "middle" to "stop" when "button" becomes "high"
transition "t3" from "stop" to "started" when "button" becomes "high"


//extension1 when start of mode "started"
//signalstuff "started" with 3 bip "short" on "buzzer" when "start"

//extension1 when end of mode "middle"
signalstuff "middle" with 2 bip "long" on "buzzer" when "end"


export "Switch!"