import serial
import sys
import time

import matplotlib.pyplot as plt
import matplotlib.animation as animation
from matplotlib import style
import matplotlib.ticker
import _thread


style.use('fivethirtyeight')

fig= plt.figure()
ax = fig.add_subplot(1,1,1)
ax.set_ylim([0,1000])

sensors = 1
mode = False
state = False

# user's choice of what to parse
if 'state' in sys.argv :
 state = True
if 'mode' in sys.argv :
 mode = True

logs = []
x = []
ymode = []
ystate = []
ysensor = []

ser = serial.Serial(sys.argv[1],sys.argv[2])


def animate(i):
 textstr =''.join((
   r'sensor : %s' % ysensor[-1]))
 if mode :
  textstr = '\n'.join((
   r'mode : %s' % ymode[-1],
   textstr))
 if state :
  print(state)
  textstr = '\n'.join((
   r'state : %s' % ystate[-1],
   textstr))

 ax.clear()

 ax.yaxis.set_major_locator(matplotlib.ticker.MultipleLocator(100))
 ax.text(0.5,0.95,textstr,fontsize=14,horizontalalignment='right',
         verticalalignment='center',transform=ax.transAxes,bbox=dict(facecolor='red', alpha=0.5))

 ax.plot(x,ysensor)



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

 ys = ser.readline()
 ys = ys.rstrip()
 ys = ys.decode("utf-8")
 logs = (ys.split(' ; '))

 for item in logs :
  val = item.split(' : ')
  if mode and val[0] == 'mode':
   ymode.append(val[1])
  if state and val[0] == 'state':
   ystate.append(val[1])
  if val[0] == 'sensor':
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





