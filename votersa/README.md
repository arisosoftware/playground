# aes-rsa-java AES+RSA结合应用java示例

## 介绍

随着Internet网的广泛应用，信息安全问题日益突出，以数据加密技术为核心的信息安全技术也得到了极大的发展。
目前的数据加密技术根据加密密钥类型可分私钥加密（对称加密）系统和公钥加密（非对称加密）系统。对称加密算法是较传统的加密体制，
通信双方在加/解密过程中使用他们共享的单一密钥，鉴于其算法简单和加密速度快的优点，目前仍然是主流的密码体制之一。
最常用的对称密码算法是数据加密标准（DES）算法，但是由于DES密钥长度较短，已经不适合当今分布式开放网络对数据加密安全性的要求。
最后，一种新的基于Rijndael算法对称高级数据加密标准AES取代了数据加密标准DES。
非对称加密由于加/解密钥不同（公钥加密，私钥解密），密钥管理简单，也得到广泛应用。RSA是非对称加密系统最著名的公钥密码算法。

## 加解密流程

1. 服务器端(server)和客户端(client)分别生成自己的密钥对
2. server和client分别交换自己的公钥
3. client生成AES密钥(aesKey)
4. client使用自己的RSA私钥(privateKey)对请求明文数据(params)进行数字签名
5. 将签名加入到请求参数中，然后转换为json格式
6. client使用aesKey对json数据进行加密得到密文(data)
7. client使用sever的RSA公钥对aesKey进行加密(encryptkey)
8. 分别将data和encryptkey作为参数传输给服务器端
 
服务器端进行请求响应时将上面流程反过来即可

## 文章详解

[开放接口的安全验证方案(AES+RSA)](http://wubaoguo.com/2015/08/21/%E5%BC%80%E6%94%BE%E6%8E%A5%E5%8F%A3%E7%9A%84%E5%AE%89%E5%85%A8%E9%AA%8C%E8%AF%81%E6%96%B9%E6%A1%88(AES+RSA)/)

##参考消息
电子投票系统综述 - Holden Wu的文章 - 知乎
https://zhuanlan.zhihu.com/p/89069693
泰国在选举首度使用区块链+IPFS支持的电子投票系统！ - 星季云矿机的文章 - 知乎
https://zhuanlan.zhihu.com/p/51029303

# 功能设计

## 原因：因为有的论坛如zhihu等，可以随意删贴。这样不好。

## 提供rsa中心 public key 管理, 系统仅仅保留public key， privatekey用户自己保存

## 消息均为明文，但有签名RSA,确保帖子文章是发帖人所有。
## 用户应该可以过滤特定用户的消息。
## 用户自己有群管理，缺省的群是黑名单群。
## 用户啦黑一个用户，可以填入原因，为什么啦黑。是否第3人可见，或者仅有关注她的人可见。
## 用户关注的人若啦黑某人，会收到消息。可以选择是否一起啦黑。
## 被啦黑的人可以有3次机会解释。
## 用户随时可以解除啦黑。
## 啦黑的数据不会下载到客户端
## 被网站屏蔽的内容自动进入黑文章库，若有id企图再次发表会被警告。连续数次后将删除用户。
## 文章的点赞功能，有用户的rsa签名，确保没有机器人瞎搞。
## 




  
  ! UUID      主键
  ! user nick name  候选键
	  * user nick name, last 10 history
	  * user email
	  * RSA public key
	  * UUID Links --可以吧其他的帐号关联起来。
	  * 
  ! GROUP UUID
  	  * 保存一个群的public key快照。
  	  	  
用户在自己机器上生成一个key pair, 然后上传 public key到系统， 
用户也可以委托系统代为生成。

系统返回
1）一个唯一的uudi标识
2）一段用public key加密的数据。要求用户用自己吨private key解密，然后发送到系统

系统 check , if the encrypted data is match the pending verify data. then user register success

outcome:

1) User UUID
2) User public key
3) User profile data such as full name , address ...

## 系统通过多个中心节点（任何人都可以搭建节点，类是minecraft的玩法,节点可以加入广告）

1）创世节点

2) 普通节点， 
	新增节点接受。。。 
	删除不响应或者被投诉的节点
	
3）普通用户
		接受创世节点的广播。
		接受普通节点吨广播
		注册节点：就是把用户uuid,publickey 发到主机。
		寻找最快的节点，
		和选择的节点进行通讯。因为p2p实际上不容易，因此只是p2p硬盘存储，网络还是多中心化的
4) 数据同步和新内容提交
	A-  同步过程设计
			用户发送同步请求，
			用户住车



5) 应该有4-5个p2p中心节点提供稳定的存储。

客户机能够检测同步的速度。类是迅雷所为


也许能直接利用迅雷下载历史数据库？

能否压缩传输，增量压缩？