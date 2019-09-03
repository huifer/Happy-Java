# spring boot 权限组件
## 基于 Oauth2.0
## 基于 spring-security

## 新增配置文件
```yaml
eureka:
  client:
    registryFetchIntervalSeconds: 5
    serviceUrl:
      defaultZone: ${EUREKA_SERVICE_URL:http://localhost:8761}/eureka/
  instance:
    health-check-url-path: /actuator/health

---
management:
  endpoints:
    web:
      exposure:
        include: "*"
  endpoint:
    health:
      show-details: ALWAYS
---
spring:
  boot:
    admin:
      client:
        url: "http://localhost:8084"
        username: "admin"
        password: "admin"
        instance:
          prefer-ip: true
```