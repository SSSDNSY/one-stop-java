# HTTP 基本知识

HTTP 是超文本传输协议，用来定义客户端与服务器数据传输的规范。

- HTTP 服务端默认端口为 80，HTTPS 默认端口为 443，客户端的端口是动态分配的。
- HTTP 请求方式一共有 9 种，分别为 POST 、GET 、HEAD、PUT 、PATCH 、 OPTIONS 、DELETE 、CONNECT 、 TRACE 。其中前三种 POST 、GET 、HEAD 是 HTTP 1.0 定义的，后六种 PUT 、PATCH 、 OPTIONS 、DELETE 、CONNECT 、 TRACE 是 HTTP 1.1 定义的。

## 一、POST 请求

POST：表示向指定资源提交数据，数据包含在请求头中。有可能导致新的资源建立或原有资源修改。 POST 请求是 HTTP 请求中使用最多的一种请求方式。

## 二、GET 请求

GET：表示请求指定的页面信息，并返回实体内容。

## 三、HEAD 请求

HEAD：类似于 GET，只不过返回的响应体中没有具体内容，只有报文头，用于获取报文头。

## 四、PUT 请求

PUT：从客户端向服务器传送的数据取代指定的内容，即向指定的位置上传最新的内容。

## 五、PATCH 请求

PATCH：对 PUT 方法的补充，用来对已知资源进行局部更新。

## 六、OPTIONS 请求

OPTIONS：返回服务器针对特殊资源所支持的 HTML 请求方式 或 允许客户端查看服务器的性能。

## 七、DELETE 请求

DELETE：请求服务器删除 Request-URL 所标识的资源。

## 八、CONNECT 请求

CONNECT：HTTP 1.1 中预留给能够将连接改为管道方式的代理服务器。

## 九、TRACE 请求

TRACE：回显服务器收到的请求，主要用于测试和诊断。

## 十、POST 请求与 GET 请求区别

1. **本质区别**
    - POST: 向服务器传送数据
    - GET: 向服务器获取数据

2. **请求参数形式**
    - POST: 附在正文中
    - GET: 拼接在URL上，多个参数之间用 & 隔开，如果参数是中文值，则会转换成诸如%ab%12的加密16进制码。

3. **请求数据大小限制**
    - POST: 正文没有长度限制，表单所能处理的长度在100k（不同协议不同浏览器不一样）
    - GET: URL长度有限制，在 1024K 左右（不同协议不同浏览器不一样）

4. **安全性**
    - POST: 相对 GET 安全，但是如果不使用 HTTPS，报文正文仍是明文，容易被人截获读取。
    - GET: URL上可见传输参数，所以安全性极低。一般用来传输一些公开的参数信息，解析也方便。

5. **浏览器后退，刷新是否重新请求**
    - POST: 是
    - GET: 否

6. **是否能被收藏为书签、是否被缓存、参数是否被保留浏览器历史**
    - POST: 否
    - GET: 是

7. **对数据类型的限制**
    - POST: 没有限制，也允许二进制数据。
    - GET: 只允许 ASCII 字符

8. **编码类型**
    - POST: application/x-www-form-urlencoded 或 multipart/form-data
    - GET: application/x-www-form-urlencoded
      (application/x-www-form-urlencoded 是浏览器默认的编码格式)

---

**版权声明**：本文为博主原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接和本声明。

**原文链接**：[https://blog.csdn.net/weixin_44135121/article/details/99670225](https://blog.csdn.net/weixin_44135121/article/details/99670225)
你可以将上述 Markdown 源码复制到支持 Markdown 的编辑器或平台中查看效果。