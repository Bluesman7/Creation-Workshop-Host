Creation-Workshop-Host
======================
Where are the instructions for installing on the Raspberry Pi?  
-------------------------------------------------------------------------------  
[Here](https://github.com/area515/Creation-Workshop-Host/wiki/Raspberry-Pi-Manual-Setup-Instructions)


Do you want to install the latest stable build?
-------------------------------------------------------------------------------
```
sudo wget https://github.com/area515/Creation-Workshop-Host/raw/master/host/bin/start.sh
sudo chmod 777 start.sh
sudo ./start.sh
```

Do you want to install the latest unstable daily development build?
-------------------------------------------------------------------------------
```
sudo wget https://github.com/WesGilster/Creation-Workshop-Host/raw/master/host/bin/start.sh
sudo chmod 777 start.sh
sudo ./start.sh WesGilster
```

Do you want to install under Windows?
------------------------------------------
* Download the latest version from: 
* https://github.com/area515/Creation-Workshop-Host/blob/master/host/cwh-X.XX.zip
 or
* https://github.com/WesGilster/Creation-Workshop-Host/blob/master/host/cwh-X.XX.zip
* Unzip the zip file into the directory of your choice.
* Double click on start.bat.

Do you want to use your web browser to automatically navigate to the running printer host without knowing anything about how your network is setup?
----------------------------------------------------------------------
* Download the latest version from:
https://github.com/area515/Creation-Workshop-Host/blob/master/host/cwhClient-X.XX.zip
 or
https://github.com/WesGilster/Creation-Workshop-Host/blob/master/host/cwhClient-X.XX.zip
* Unzip the zip file into the directory of your choice.
* If you are in Linux run this:
````````
	sudo browseprinter.sh
````````
If you are in windows double click this:
````````
	browseprinter.bat
````````
