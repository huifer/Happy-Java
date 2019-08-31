# redis
## 安装

`sudo apt-get install redis-server`



## 配置

`sudo vim /etc/redis/redis.conf`
- 注释#  bind 127.0.0.1 ::1
- 密码 requirepass huifer  

登录 redis-cli
```
127.0.0.1:6379> set a "a"
(error) NOAUTH Authentication required.
127.0.0.1:6379>
# 密码登录
127.0.0.1:6379> AUTH huifer
OK
127.0.0.1:6379> set a "a"
OK
127.0.0.1:6379> get a
"a"
127.0.0.1:6379>
                        
```
