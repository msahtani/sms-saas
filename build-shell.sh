#!/bin/bash

folders="account auth sms api-gateway"

for f in $folders
do
	cd $f
	pwd
	./mvnw package
done
