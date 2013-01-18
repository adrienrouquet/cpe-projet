#!/bin/bash

SOURCESPATH=$PWD

cd /tmp

echo "MISE A JOUR DES SOURCES"
apt-get update

echo "INTALLATION DE JAVA JRE7"
ARCH=$(uname -m)
if [ "$ARCH" = "x86_64" -o "$ARCH" = "amd64" ]
then wget --no-cookies --header "Cookie: gpw_e24=http%3A%2F%2Fwww.oracle.com%2F" http://download.oracle.com/otn-pub/java/jdk/7u11-b21/jre-7u11-linux-x64.tar.gz -O jre7.tar.gz
else
  if [ "$ARCH" = "i386" -o "$ARCH" = "i486" -o "$ARCH" = "i586" -o "$ARCH" = "i686" ]
    then wget --no-cookies --header "Cookie: gpw_e24=http%3A%2F%2Fwww.oracle.com%2F" http://download.oracle.com/otn-pub/java/jdk/7u11-b21/jre-7u11-linux-i586.tar.gz -O jre7.tar.gz
  fi
fi
mkdir -p /opt/jre7
tar xfs jre7.tar.gz --directory /opt/jre7 --strip 1
chmod 755 -R /opt/jre7

echo "INSTALLATION DE MYSQL SERVER"
DEBIAN_FRONTEND='noninteractive' command apt-get install mysql-server
while
  read -s -p "Set Password for mysql ROOT user: " PASSWORD
  echo
  read -s -p "Confirm Password: " CONFIRMPASSWORD
  echo
  [ -z "$PASSWORD" -o -z "$CONFIRMPASSWORD" -o "$PASSWORD" != "$CONFIRMPASSWORD" ]
do
  echo "$PASSWORD"
  echo "$CONFIRMPASSWORD"
  if [ -z $PASSWORD ]
    then echo "PASSWORD NULL"
  fi
  if [ "$PASSWORD" != "$CONFIRMPASSWORD" ]
    then echo "PASSWORDS DON'T MATCH..."
  fi
done
mysqladmin -uroot password $PASSWORD

echo "INTALLATION DE TOMCAT7"
wget http://apache.opensourcemirror.com/tomcat/tomcat-7/v7.0.34/bin/apache-tomcat-7.0.34.tar.gz -O tomcat7.tar.gz
mkdir -p /opt/tomcat7
tar xfs tomcat7.tar.gz --directory /opt/tomcat7 --strip 1
cd /opt/tomcat7
mv webapps BAK_webapps
mkdir webapps

echo "AJOUT DE L'UTILISATEUR tomcat"
groupadd tomcat
useradd -g tomcat -G sys -d /opt/tomcat7 tomcat
chown -R tomcat:tomcat /opt/tomcat7/
chmod 770 /opt/tomcat7/

echo "CONFIGURATION ET DEPLOIEMENT DE ROOT.WAR ET MYAM.WAR"
cd "$SOURCESPATH"
mysql -uroot -p$PASSWORD < setup_mysql.sql
cp -R ./sources/* / --backup=simple --suffix='.BAK'

echo "DEMARRAGE DE TOMCAT"
update-rc.d tomcat defaults
/etc/init.d/tomcat start