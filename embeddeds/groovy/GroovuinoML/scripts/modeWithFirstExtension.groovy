sensor "button1" onPin 9
sensor "button2" onPin 10
analogsensor "analogsensor" onPin 1 echantillonage 1 by "s"
actuator "led" pin 12 echantillonage 1 by "s"
actuator "buzzer" pin 8 echantillonage 1 by "s"


state "on" means "led" becomes "high"
state "off" means "led" becomes "low"
state "on1" means "led" becomes "high" and "buzzer" becomes "high"
state "off1" means "led" becomes "low" and "buzzer" becomes "low"

transition "t1" from "on" to "off" when "button1" becomes "low"
transition "t2" from "off" to "on" when "button1" becomes "high"
transition "t3" from "on1" to "off1" when "button1" becomes "low"  and "button2"  becomes "high"
transition "t4" from "off1" to "on1" when "button1" becomes "high"  and "button2"  becomes "high"

mode "jour" states (["on", "off"]) transi (["t1","t2"]) init "off"
mode "nuit" states (["on1", "off1"]) transi (["t3","t4"]) init "on1"


from "jour" to "nuit" when "analogsensor" threshold "sup" at 1
from "nuit" to "jour" when "analogsensor" threshold "inf" at 1



//we want this state to be emphisized signaling
//signalstuff "on" with 3 bip "long" on "buzzer" onlymode "jour"
signalstuff "on" with 3 bip "short" on "buzzer" when "start"

initial "nuit"

export "Switch!"