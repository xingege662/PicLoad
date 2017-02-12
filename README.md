# PicLoad

需求:
---
* 根据用户需求可以灵活配置（建造者模式）
* 支持高并发，图片加载的优先级 
* 支持可以选择不同的加载策略，对加载策略进行扩展
* 二级缓存  加载图片时内存中已经加载了，则从内存中加载，不存在去外置卡中加载，外置还不存在则从网络下载
* 并对缓存策略可以扩展
* 支持从加载过程中显示默认加载图片
* 支持加载失败时 显示默认错误图片
* 图片显示自适应。从网络加载下来的图片经最佳比例压缩后显示
* 不能失真变形
* 支持请求转发，下载

用到的模式：
---------
* 生产者 消费者模式
* 建造者模式
* 单例模式
* 模板方法模式
* 策略模式

用到的知识点
-----------
* 内存缓存 LruCache技术
* 硬盘缓存技术DiskLruCache技术
* 图片下载时请求转发

流程图如下
---------
![](https://github.com/xingege662/PicLoad/blob/master/Pic/picture/stream_graph.png)

大体步骤图
---------
![](https://github.com/xingege662/PicLoad/blob/master/Pic/picture/framework.png);

UML图
-----
![](https://github.com/xingege662/PicLoad/blob/master/Pic/picture/uml.png);

上图有些模糊，在工程目录下的picture文件夹下可以找到。<br>
