Creation-Workshop-Host
======================

Raspberry Pi Installation
-------------------------
1. Install _2015-02-16-raspbian-wheezy.img_ onto an SD card. Do NOT use an older version!
2. Start your Raspberry Pi and type the following at the command line:
```
sudo raspi-config
```
3. Choose and enable the following options from the Raspberry Pi Software Configuration Tool:
 * Expand Filesystem
 * Enable Camera
4. Choose to reboot the Raspberry Pi when you are prompted by the configuration tool.
5. After the Raspberry Pi has completed rebooting, execute the following commands at the command line:
```
  sudo wget https://github.com/area515/Creation-Workshop-Host/raw/master/host/bin/start.sh
  sudo chmod 777 start.sh
  sudo ./start.sh
```
6. Browse to the Raspberry Pi with cwhClient, or your favorite browser.

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
