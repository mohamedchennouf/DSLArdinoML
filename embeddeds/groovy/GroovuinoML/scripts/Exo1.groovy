sensor "button1" onPin 9
sensor "button2" onPin 10
actuator "led" pin 12
actuator "buzzer" pin 8
analogsensor "analogsensor1" threshold 1
analogsensor "analogsensor2" threshold 3


mode "jour"


inside "jour" state "on" means "led" becomes "high" and "buzzer" becomes "high"
inside "jour" state "off" means "led" becomes "low" and "buzzer" becomes "low"

initial "off"

from "on" to "off" when "button1" becomes "low"  and "button2"  becomes "high"
from "off" to "on" when "button1" becomes "high"  and "button2"  becomes "high"


mode "night"

inside "night" state "on1" means "led" becomes "high"
inside "night" state "off1" means "led" becomes "low"

initial "off"

from "on1" to "off1" when "button1" becomes "low"
from "off1" to "on1" when "button1" becomes "high" and "button2"  becomes "high"


from "jour" to "night" when "button1" becomes "low"
from "night" to "jour" when "button1" becomes "high"



//we define the same transition for states and modes
//adding the condition inside of BAaescript to define wheather it is mode or state
//from "mode1" to "mode2" when "anlogsensorXXXX" becomes 345


//contraint pour que deux modes ne peuvent pas avoir les meme nom des états



export "Switch!"




