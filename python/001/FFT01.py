import numpy as np
import matplotlib.pyplot as plt
from scipy.fftpack import fft,ifft
from matplotlib.pylab import mpl

myfont = mpl.font_manager.FontProperties(fname='/usr/share/fonts/opentype/noto/NotoSansCJK-Regular.ttc')
mpl.rcParams['font.sans-serif'] = ['Noto Sans CJK SC']   #显示中文 这个不好用,在ubuntu下面
mpl.rcParams['axes.unicode_minus']=False       #显示负号

N = 256 #采样点选择256
x = np.linspace(-10, 10, N)
y = np.linspace(-10, 10, N)
X,Y = np.meshgrid(x,y) #重复填充矩阵
Z = np.sin(0.5 * np.pi * X)     #光栅

#二维傅里叶变换在图像处理中经常用到，为了更好理解python中的fft2。这里生成了二维正弦条纹，然后进行快速傅里叶变换


Z_fft2 = np.fft.fft2(Z)  #二维傅里叶变换
Z_fft2_A = abs(np.fft.fftshift(Z_fft2)) #将低频移动到中心

plt.figure(figsize=(8,8))#设置子图大小
plt.subplot(2,2,1) #添加画布，先设置第一个子图的内容
plt.imshow(Z)
plt.title('原始信号', fontproperties=myfont )

plt.subplot(2,2,2)#设置第2个子图的内容
plt.imshow(abs(Z_fft2))
plt.title('二维傅里叶变换后', fontproperties=myfont )

plt.subplot(2,2,3)#设置第3个子图的内容
plt.imshow(Z_fft2_A) #将低频移动到图像中心的显示出来
plt.title(u'低频转移中心', fontproperties=myfont )

plt.subplot(2,2,4)#设置第4个子图的内容
plt.plot(Z_fft2_A[128,:])# 等于plt.plot(Z_fft2_sh[128,1:255])
plt.title('x = 128')
plt.show()
