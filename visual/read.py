import serial

#from time import gmtime, strftime

import matplotlib.pyplot as plt
import matplotlib.animation as animation
from matplotlib import style
import matplotlib.ticker
import _thread
import time


style.use('fivethirtyeight')

fig= plt.figure()
ax = fig.add_subplot(1,1,1)
ax.set_ylim([0,1000])
sensors = 1
mode = True
state = True

logs = []
x = []
ymode = []
ystate = []
ysensor = []



#ser = serial.Serial('/dev/tty.usbserial',9600)#,serial.PARITY_NONE,serial.STOPBITS_ONE,serial.EIGHTBITS,1)
ser = serial.Serial('COM7',9600)#,serial.PARITY_NONE,serial.STOPBITS_ONE,serial.EIGHTBITS,1)
#ys = ser.readline()
#ys = ser.readline()
#figure
##ax1 = fig.add_subplot(1,1,1)

##mettre une intervalle de temps


# modeName = []
# stateName = []

def animate(i):


 textstr = '\n'.join((
  r'mode : %s' % ymode[-1],
  r'state : %s'% ystate[-1],
  r'sensor : %s' % ysensor[-1]))

 ax.clear()
 #dt.datetime.now().strftime('%S')
 #print (x)
 #print (ysensor)
# ax.plot(dt.datetime.strptime("%H:%M:%S").now(),gmtime()),ysensor)
 ax.yaxis.set_major_locator(matplotlib.ticker.MultipleLocator(100))
 ax.text(0.5,0.95,textstr,fontsize=14,horizontalalignment='right',
         verticalalignment='center',transform=ax.transAxes,bbox=dict(facecolor='red', alpha=0.5))

 ax.plot(x,ysensor)
# ax.set_ylim([0,1000])
 # x.clear()
 # ysensor.clear()



def parse():
 global x
 global ysensor
 global ystate
 global ymode

 ysensor=ysensor[-250:]
 x=x[-250:]
 ymode=ymode[-250:]
 ystate=ystate[-250:]

 if not x and ysensor:
  x.append(0)

 if x.__len__()  < ysensor.__len__() :
  x.append(x[-1]+1)


 # elif x:
 #  x.append(x[-1]+1)

 ys = ser.readline()
 ##print ('parse')
 ys = ys.rstrip()
 ys = ys.decode("utf-8")
 #print (ys)
 logs = (ys.split(' ; '))
 #print (logs)
 for item in logs :
  val = item.split(' : ')
  if mode and val[0] == 'mode':
   #print (val[1])
   ymode.append(val[1])
  if state and val[0] == 'state':
   #print (val[1])
   ystate.append(val[1])
  if val[0] == 'sensor':
   #print (val[1])
   ysensor.append(val[1])


## MAIN

def serial_read():
 while 1:
  parse()
def paint():
 ani = animation.FuncAnimation(fig, animate, interval = 500)
 plt.show()

def reset_data():
 time.sleep(20)
 x.clear()
 ysensor.clear()


parse()
parse()
parse()
parse()

_thread.start_new_thread(serial_read,())

paint()





