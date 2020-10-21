## web.xml
 基于servlet2规范的j2EE【它定义了动态Web页面功能（Servlet和Jsp）
 、商业组件（EJB）、异步消息传输机制（JMS）、名称和目录定位服务（JNDI）、数据库访问（JDBC）
 、与子系统的连接器（JCA）和安全服务等 [2]  。】系统，从web.xml出发:
 
-  context-param -> listener -> filter -> servlet

启动 hessianServer ,初始化系统全局配置，注册服务，数据库连接池，

 ```xml
<filter>
		<filter-name>service</filter-name>
		<filter-class>com.ailk.service.server.hessian.Hessian2Server</filter-class>
	</filter>
	<filter-mapping>
		<filter-name>service</filter-name>
		<url-pattern>/service/*</url-pattern>
	</filter-mapping>
```
 
 第一次访问：ApplicationServlet > AbstractEngine > HomeService>  IRequestCycle >  IPage > AbstractPage > BizLoginPage.login()
  ```xml
	<servlet>
		<servlet-name>order</servlet-name>
		<servlet-class>com.ailk.web.servlet.PageServlet</servlet-class>
		<load-on-startup>0</load-on-startup>
	</servlet>
	<servlet-mapping>
		<servlet-name>order</servlet-name>
		<url-pattern>/order</url-pattern>
	</servlet-mapping>
	<servlet-mapping>
		<servlet-name>order</servlet-name>
		<url-pattern>/preload</url-pattern>
	</servlet-mapping>
 ```

## 服务调用
