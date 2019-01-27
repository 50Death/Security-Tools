# Security Tools

Security Tools 顾名思义是与信息安全相关的工具

## 工具列表
1. [键盘记录器](https://github.com/50Death/Security-Tools/blob/master/SecurityTools/src/main/java/com/lyc/security/recorder/KeyboardRecord.java)
2. [鼠标记录器](https://github.com/50Death/Security-Tools/blob/master/SecurityTools/src/main/java/com/lyc/security/recorder/MouseRecord.java)
3. [进程记录器](https://github.com/50Death/Security-Tools/blob/master/SecurityTools/src/main/java/com/lyc/security/recorder/ProcessRecord.java)
4. [屏幕截图](https://github.com/50Death/Security-Tools/blob/master/SecurityTools/src/main/java/com/lyc/security/recorder/Screenshot.java)

## 免责声明
本仓库所涉及一切程序, 代码等元素均为学习所用, 程序设计者即本人对使用者在使用过程中所造成的一切后果不承担任何责任, 下载或查阅或参考任何代码或文件即表示您同意一切后果由使用者承担

I do not take any responsibility of the troubles you may faced. Using, even seeing my code means all the consequences is up to you

## 使用方式
### 1. 将SPY.java放至一个隔离的环境，以免对您的信息造成泄露
警告：双击SPY.java就会执行本程序，记录全程静默任务管理器无法看到，如您不小心打开了该程序，您可以观察程序根目录是否生成了log或screenshot文件夹，如果有表示此程序已运行，请使用命令行杀死进程或者重新启动计算机
### 2. 设置Settings.txt
此文件必须在主程序根目录下，为了保证您自身安全没有此文件将会无法运行
```
MouseRecord=false#NO        这里设置是否打开鼠标记录器，#后必须跟随NO
KeyboardRecord=false#NO     这里设置是否打开键盘记录器，#后必须
ProcessRecord=false#60      这里设置是否打开进程记录器，#后跟记录频率，单位为秒
Screenshot=false#600        这里设置是否打开屏幕截图  ，#后跟截图频率，单位为秒
```
### 3. 在安全合法隔离的环境下运行本程序
本程序一旦运行只能直接杀死进程才能关闭，或者重启，本程序没有开机启动功能！

### 4. 运行结束
假如您打开了所有功能，那么程序根目录下会生成log文件夹和screenshot文件夹

log文件夹存储了记录器记录的结果

#### 鼠标记录器记录文件
每行分别记录了【时间】【操作】【位置】
打好的包内已设置了点击或使用滚轮才记录
操作代码对应操作如下，以后会更新在Finalize里
```java
    public static final int WM_MOUSEMOVE = 512;
    public static final int WM_LBUTTONDOWN = 513;
    public static final int WM_LBUTTONUP = 514;
    public static final int WM_RBUTTONDOWN = 516;
    public static final int WM_RBUTTONUP = 517;
    public static final int WM_MBUTTONDOWN = 519;
    public static final int WM_MBUTTONUP = 520;
```
位置信息记录了点击时鼠标指针所处的坐标，在多显示器的PC当中记录正常

TXT文件每10秒写入一次，因此当程序意外退出时无法记录临近10秒内的操作，这是为了在用户快速操作时可以及时响应

开启本功能会对计算机性能造成小幅影响

鼠标记录在用户打开并选中任务管理器时无法继续记录

#### 键盘记录器
每行分别记录了【时间】【键盘】【操作】

其中操作在初始记录文件内为操作码记录，可调用Finalize类来实现翻译，或在Release中下载对应表

对应表尚未补全需要大家帮助！！！

记录器在用户打开任务管理器并选中它的时候无法继续记录

键盘记录在用户使用组合键且按的比较快的时候无法记录完全

目前还有很多操作码不清楚是哪个按键

开启本功能对计算机性能影响可忽略

同鼠标记录器，键盘记录TXT写入也是10秒写入一次，意外关闭将无法记录最近10秒的内容，可在源代码中修改

#### 进程记录器
通过向cmd.exe发送 tasklist 并截获输出，然后输出到TXT上并记录时间

对计算机性能影响甚微可完全忽略，在等待时线程处于睡眠状态不影响性能

#### 屏幕截图
对主显示器进行全屏截图，并保存在根目录screenshot文件夹里，无法对多显示器的非主屏幕进行截图

截图时对计算机性能影响较大，不截图时处于睡眠状态无影响

## 使用要求
Java运行环境
Windows操作系统

## 使用到的Maven依赖
```xml
        <!-- https://mvnrepository.com/artifact/net.java.dev.jna/jna -->
        <dependency>
            <groupId>net.java.dev.jna</groupId>
            <artifactId>jna</artifactId>
            <version>5.2.0</version>
        </dependency>


        <!-- https://mvnrepository.com/artifact/net.java.dev.jna/jna-platform -->
        <dependency>
            <groupId>net.java.dev.jna</groupId>
            <artifactId>jna-platform</artifactId>
            <version>5.2.0</version>
        </dependency>
```
## 请捐赠打赏投食！！！
![图片加载失败](https://github.com/50Death/CipheredSocketChat/blob/master/Pictures/%E6%94%AF%E4%BB%98%E5%AE%9D%E7%BA%A2%E5%8C%85.jpg)

## Support Me on Patreon
(https://www.patreon.com/user?u=16747470)
