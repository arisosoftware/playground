import numpy 
from scipy.fftpack import fft,ifft
import matplotlib.pyplot as pyplot
from matplotlib.pylab import mpl
 
mpl.rcParams['axes.unicode_minus']=False       #显示负号
myfont = mpl.font_manager.FontProperties(fname='/usr/share/fonts/opentype/noto/NotoSansCJK-Regular.ttc')
#mpl.rcParams['font.sans-serif'] = ['Noto Sans CJK SC']   #显示中文 在ubuntu下面 这个没有用,还是的直接指定字体
mpl.rcParams['axes.unicode_minus']=False       #显示负号
fig = mpl.pyplot.gcf()
fig.set_size_inches(16, 7.5)


#采样点选择1400个，因为设置的信号频率分量最高为600赫兹，根据采样定理知采样频率要大于信号频率2倍，所以这里设置采样频率为1400赫兹（即一秒内有1400个采样点，一样意思的）
 
N=700

x=numpy.linspace(0,1,N)      
 
#设置需要采样的信号，频率分量有200，400和600
y=5*numpy.sin(2*numpy.pi*200*x) + 8*numpy.sin(2*numpy.pi*400*x)+2*numpy.sin(2*numpy.pi*600*x)
 
fft_y=fft(y)                          #快速傅里叶变换

x = numpy.arange(N)             # 频率个数
half_x = x[range(int(N/2))]  #取一半区间
 
abs_y=numpy.abs(fft_y)                # 取复数的绝对值，即复数的模(双边频谱)
angle_y=numpy.angle(fft_y)            #取复数的角度
normalization_y=abs_y/N            #归一化处理（双边频谱）                              
normalization_half_y = normalization_y[range(int(N/2))]      #由于对称性，只取一半区间（单边频谱）
 
pyplot.subplot(231)
pyplot.plot(x,y)   
pyplot.title('原始波形', fontproperties=myfont )
 
pyplot.subplot(232)
pyplot.plot(x,fft_y,'black')
pyplot.title('双边振幅谱(未求振幅绝对值)', fontproperties=myfont,fontsize=9,color='black') 
 
pyplot.subplot(233)
pyplot.plot(x,abs_y,'r')
pyplot.title('双边振幅谱(未归一化)', fontproperties=myfont,fontsize=9,color='red') 
 
pyplot.subplot(234)
pyplot.plot(x,angle_y,'violet')
pyplot.title('双边相位谱(未归一化)', fontproperties=myfont,fontsize=9,color='violet')
 
pyplot.subplot(235)
pyplot.plot(x,normalization_y,'g')
pyplot.title('双边振幅谱(归一化)', fontproperties=myfont,fontsize=9,color='green')
 
pyplot.subplot(236)
pyplot.plot(half_x,normalization_half_y,'blue')
pyplot.title('单边振幅谱(归一化)', fontproperties=myfont,fontsize=9,color='blue')
 
pyplot.show()



fig.savefig('test2png.png', dpi=100)
