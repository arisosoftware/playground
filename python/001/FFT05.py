import numpy as np
from scipy.fftpack import fft,ifft
import matplotlib.pyplot as plt
from matplotlib.pylab import mpl
 
 #  这个不好用,在ubuntu下面  mpl.rcParams['font.sans-serif'] = ['SimHei']   #显示中文 
mpl.rcParams['axes.unicode_minus']=False       #显示负号
myfont = mpl.font_manager.FontProperties(fname='/usr/share/fonts/opentype/noto/NotoSansCJK-Regular.ttc')
mpl.rcParams['font.sans-serif'] = ['Noto Sans CJK SC']   #显示中文 在ubuntu下面
mpl.rcParams['axes.unicode_minus']=False       #显示负号

#https://blog.csdn.net/qq_27825451/article/details/88553441
#采样点选择1400个，因为设置的信号频率分量最高为600赫兹，根据采样定理知采样频率要大于信号频率2倍，所以这里设置采样频率为1400赫兹（即一秒内有1400个采样点，一样意思的）
x=np.linspace(0,1,1400)      
 
#设置需要采样的信号，频率分量有200，400和600

y=7*np.sin(2*np.pi*200*x) + 5*np.sin(2*np.pi*400*x)+3*np.sin(2*np.pi*600*x)

plt.figure()
plt.subplot(311)
plt.title('原始波形', fontproperties=myfont )
plt.plot(x,y)   
plt.grid(linestyle=':')
plt.subplot(312)  
plt.plot(x[0:50],y[0:50])   
plt.title('原始部分波形（前50组样本）', fontproperties=myfont )
plt.show()
plt.grid(linestyle=':')
plt.legend()
plt.tight_layout()
plt.show()
