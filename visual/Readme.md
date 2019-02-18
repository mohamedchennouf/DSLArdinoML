# Serial output for ArduinoML

Le but est de lire les output serial spécifié par l'utilisateur.

J'ai choisi d'utiliser python pour lire l'output série de l'arduino. 
On utilise _serialPy_ afin de pouvoir l'utiliser sur tous les OS.

En premier lieu on change le kernel afin qu'il prépare le setup.  
Ensuite changer le kernel et le groovy afin de parser la déclaration de l'utilasateur, afin de déclarer les données à logger.  
Enfin, nous devons fournir un executable python (comprenant les librairy i.e. pySerial ...)     

**Todo**
Ajouter dans le modèle du domaine / kenel  + Basescript un moyen à l'utilisateur d'indiquer les infos qu'il veut print. 
Donner en paramètre à mon app python ce qu'elle va lire.
Exporter cette app et pouvoir la livrer.  


links :   
For pySerial
https://playground.arduino.cc/interfacing/python  
https://www.jujens.eu/posts/2014/Jan/11/communication-serie-facile-python/  
http://pyserial.sourceforge.net/  

For the Python GUI  
http://www.i3s.unice.fr/~menez/M1Miage/TP2/readserial.py
https://plot.ly/python/
https://medium.com/python-pandemonium/data-visualization-in-python-line-graph-in-matplotlib-9dfd0016d180
https://pythonprogramming.net/live-graphs-matplotlib-tutorial/


Pour développer vous pouvez installer les packet python suivants : 
```
pip install pyserial 
# On ubuntu 
pip install matplotlib
# On Windows

```
