今天学习的是快速傅立叶变化
Today I am learning Fast Fourier Transformation
using python, because it's fast and simple. 
first is need to run in python3, but default setting is python2.
so let's fix it by install virtual python3
after run the python. I got an error:
fix by running this code
 python3 -m pip install --upgrade pip
try to install pylab...

actully there's no pylab, only hav matplotlib.
    pip3 install matplotlib

it said it's already install, then run the testing python. it works!

but...
chinese font not show!?!
refer by this article , 
https://blog.csdn.net/u012150360/article/details/72921855

 fc-list :lang=zh

then change the script:
from
plt.title('低频转移中心')
to
plt.title(u'低频转移中心', fontproperties=myfont )
all done.


今天我正在学习快速傅立叶变换
使用python，因为它既快速又简单。
首先需要在python3中运行，但默认设置为python2。
所以让我们通过安装虚拟python3来修复它
运行python之后。 我收到一个错误：
通过运行此代码修复
python3 -m pip install --upgrade pip
尝试安装pylab ...

实际上，没有pylab，只有hap matplotlib。
     pip3安装matplotlib

它说它已经安装，然后运行测试python。 有用！

但...
中文字体不显示！？！

通过本文引用，
https://blog.csdn.net/u012150360/article/details/72921855

  fc-list：lang = zh

然后更改脚本：
从
plt.title('低频转移中心')
to
plt.title(u'低频转移中心', fontproperties=myfont )
全做完了。