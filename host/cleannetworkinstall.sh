#!/bin/bash

rm -r cwh
mkdir cwh
cd cwh
wget https://github.com/WesGilster/Creation-Workshop-Host/raw/master/host/cwh-0.22.zip
unzip cwh-0.22.zip
chmod 777 start.sh
chmod 777 debug.sh
rm cwh-0.22.zip