/**
 *一个不同的类似区块链的东西.

 * KDC 公钥分发中心
 *     用户ID 就是其公钥的MD5.
 *     用户消息 = 用户ID+随机密码(RSAJ加密了)+加密的数据包
 *     用户消息秘押链 = 其他用户ID+用户消息的MD5
 *     
 *     
 *     消息是这样发表的
 *     用户A , 写了一篇文章,  在文章后面有电子签名
 *     电子签名的结构
 *     SHA1RSA签名 文章+用户名+时间戳
 *     
 *  文章可以是HTML+图片结构. 其中签名是用类似2纬码的图片来表达的   
 *  整个文章集合用sqlite db3格式. 
 *  可以导出为ZIP包.
 *     图片,文字均用MD5,来去重复. 签名等放在关系表中.以便查阅.
 *     加为好友,就可以得到公钥.
 *  
 *     客户端应该是类似电子书那样. 
 *     
 *     
 *     
 *  经济:
 *  用户可以购买代币,也可以赎回代币. 
 *  用户之间可以转账,转账均有签名, 支持第3方电脑加入成为见证者,每在线1分钟给1分钱 每一次验证和记账都可以收到代币.
 *  每个转账的见证者不能超过500人.见证是广播到交易中心. 随机选择.
 *  系统是有 中心的 .
    
 *     
 *     
 *     用户用私钥加密AES随机秘钥,然后发表,这个AES加密的数据包.
 *     其他用户用这个用户的公钥解密AES秘钥,进而读取数据包.
 *     其他用户觉得这个数据包不错,可以点击赞,然后这个数据包就被计算MD5MAC,然后加载如数据包尾巴.
 *          点赞数据流结构
 *              点赞者ID
 *              迄今为止(1分钟之前)的所有点赞数据流MD5
 *              区块链?这而?
 *     弱分布式数据库结构.
 *     SQLITE为主要数据库
 *     没有点赞的文章将被垃圾回收.
 *     
 *     数据包尾巴的数据,其他用户可以直接丢弃,如果是非法的点击.
 *      比如世界时间不对.
 *     
 *     
 * 
 */
 
package com.ariso.blockchain;