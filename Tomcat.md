# Tomcat

* 下载安装
  * 绿色版，下载zip文件之后解压下载就完成了
    * 解压到没有中文没有空格的路径中
* 卸载
  * 删除解压后的整个文件，卸载就完成了

---

* 端口配置

  * 默认端口号8080

  * java程序大多使用8080端口，可能会发生端口冲突，结束本机正在运行的Java程序即可

  * 为tomcat配置端口号：

    * 修改conf/server.xml文件
    * 找到默认端口号并修改

  * ##### HTTP协议默认端口为80，如果将Tomcat端口号改为80，将来访问Tomcat时不再需要输入端口号

---

* 往Tomcat中部署应用程序
  * 把项目放在`webapps` 目录下即部署完成

---

# 基于springboot的web程序入门

* SpringBootWeb集成了Tomcat，因此独立下载的Tomcat程序很少被使用（在基于SpringBoot开发的过程中）
* 当启动类运行时，会自动启动内嵌的Tomcat服务器