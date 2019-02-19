# Serial output for ArduinoML

Le but est de lire les output serial spécifié par l'utilisateur.

Nous avons choisi d'utiliser python pour lire l'output série de l'arduino. 
On utilise _serialPy_ afin de pouvoir l'utiliser sur tous les OS.
On utilise également matplotlib afin de créer la figure "UI" représentant les données logués. 

En premier lieu on change le kernel afin qu'il prépare le setup.  
Ensuite changer le kernel et le groovy afin de parser la déclaration de l'utilasateur, afin de déclarer les données à logger.  
Enfin, nous devons fournir un executable python (comprenant les librairy i.e. pySerial ...)     
Pour ce dernier point nous fournissons un exécutable .exe sur ce lien :   
https://www.dropbox.com/sh/rho9esrf0hkbfzu/AADwlvgVDi4Bcn4y-b-qSpw4a    

L'exécutable a été construit avec pyInstaller afin de permettre a l'utilisateur d'utiliser le produit sans avoir besoin d'installer les dépendances.

## Lancer le python 

Soit vous avez besoin d'intaller les dépendances (`pySerial et matplotlib `), puis lancer la commande suivante : 
```  bash
python read.py <Votre port arduino> <baude rate> [mode] [state] 
```
Ou vous pouvez également utiliser **l'exécutable** _(avec les mêmes paramètres ...)_

Il est nécessaire que du côté d'arduino les données mode/state soit _'loggées'_ si vous souhaiter les afficher.  
`mode` : permet d'afficher le nom mode courant.   
`state` : permet d'afficher le nom de l'état courant. 


links :   
For pySerial
https://playground.arduino.cc/interfacing/python  
https://www.jujens.eu/posts/2014/Jan/11/communication-serie-facile-python/  
http://pyserial.sourceforge.net/  
For the Python GUI  
https://plot.ly/python/
https://medium.com/python-pandemonium/data-visualization-in-python-line-graph-in-matplotlib-9dfd0016d180
https://pythonprogramming.net/live-graphs-matplotlib-tutorial/

Pour développer vous pouvez installer les packet python suivants : 
```
pip install pyserial 
# On ubuntu 
pip install matplotlib
# Or install them on Windows using anaconda
```
