这是一个系统仿真程序

利用vertx来实现，actor,就是一个vertx.

功能有

1） 统计，时间事件发生器，

第一个故事就是 细菌在有限的，纯洁无天敌的培养基里面的数目和时间，
功能1： 给定初始细菌数目，培养基方块大小，细菌的初始投放点，设定细菌每隔几秒出一代新的，每秒需要消耗多少培养基深度。细菌消亡秒数。


比如说，给定1个细菌，每隔1秒吃1格培养基，其移动能力是每秒5格，当细菌吃够10格，就能分裂成为2个细菌，细菌寿命是100秒。
求细菌数目和时间的曲线图。

##第一个版本性能有问题，cpu一直100%，然后越来越慢，出现超时警告。说明用法出现了初级错误。

第2版，verticle 粒度加大，加一个area作为cell的容器，cell变为life





 