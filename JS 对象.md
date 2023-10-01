# JS 对象

* 数组

  * 定义

    *  var arr = new Array(1,2,3,4);
      
    * ```js
      var arr = [1,2,3,4];
      ```
    
  * 访问

    * ```js
      arr[10] = "hello";
      ```
  
  * 常见属性和方法

    * 属性
      * length
    * 方法
      * forEach()
        * 遍历每个有值的元素并调用一次传入的函数
      * push()
        * 将新元素添加到数组的末尾，并返回新的长度
      * splice()
        * 从数组中删除元素

---

* String

  * ```js
    var str = new String("Hello String");
    ```

  * ```js
    var str = "Hello String";
    ```

  * ```js
    var str = 'Hello String';
    ```

  * 属性

    * length

  * 方法

    * charAt()	
      * 返回在指定位置的字符
    * indexOf() 
      * 检索字符串
    * trim()
      * 去除字符串两边的空格
    * subString()
      * 提取字符串两个指定的索引号之间的字符

---

* JSON

  * Java自定义对象

    * ```js
      var user = {
          name:"TOM",
          age:20,
          gender:"male",
          eat(){
              alert("eat~");
          }
      }
      //在大括号里写属性和方法
      ```

    * ```js
      user.name;
      ```

    * ```js
      user.eat();
      ```

  * JSON对象

    * JSON中要求所有的key值必须用双引号括起来
    * JSON数据本质是一个字符串

  ---

  * BOM：浏览器对象模型，将浏览器的各个组成部分封装为对象
    * 五个主要对象
      * # Window：浏览器窗口对象
        
        * 如何获取
          * 直接使用
        * 属性
          * history
          * # location
            
            * `window.location.属性`
            * 属性
              * href：设置或返回完整的URL
          * navigator
        * 方法
          * alert（）
          * confirm（）
          * **setInterval()** 定时器
            * 周期性地执行某个函数
            * 参数
              * 参数1：传递一个函数
              * 参数2：传递一个数值
                * 例如2000代表每隔2秒执行一次
          * **setTimeout()**
      * Navigator：浏览器对象
      * Screen：屏幕对象
      * History：历史记录对象
      * Location：地址栏对象

---

# DOM

将标记语言的各个组成部分封装成对应的对象

* Document
* Element
* Attribute
* Text
* Comment

## DOM 树![image-20231001193554478](https://raw.githubusercontent.com/sandeulllll/blog-img/main/img/202310011935539.png)

通过DOM对象：

* 改变HTML元素的内容
* 改变HTML元素的样式
* 对事件做出反应
* 添加和删除HTML元素

## 获取Element元素

* ```js
  doucument.getElementById("id1");
  ```

* ```js
  document.getElementByTagName("div");
  //返回数组
  ```

* ```js
  document.getElementByName("hobby");
  //返回一个element数组
  ```

* ```js
  document.getElementByClass("cls");
  //返回数组
  ```

  