# zookeeper
## 安装
- tar -zxvf apache-zookeeper-3.5.5-bin.tar.gz
- cd apache-zookeeper-3.5.5-bin/conf
- cp zoo_sample.cfg zoo.cfg
- vim /etc/profile 
```aidl
export ZK_HOME=/home/huifer/dev/apache-zookeeper-3.5.5-bin
export PATH=${ZK_HOME}/bin:$PATH         
```
source /etc/profile
kServer.sh start 