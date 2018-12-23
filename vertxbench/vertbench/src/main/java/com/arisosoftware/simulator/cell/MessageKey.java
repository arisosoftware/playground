package com.arisosoftware.simulator.cell;

public interface MessageKey {
    String MSG_Step = "Step";
    String MSG_Report = "Report";    //Monitor ask for a report
    String MSG_NewVx = "Add";  //Add new VxCell
    String MSG_DelVx = "Del";  // Delete VxCell
     
    
}


//
//对于用是用interface定义常量还是使用class定义常量, 看个人喜好. 个人觉得interface定义常量更为优美, 
//
//1. 代码更简洁
//2. 生成的class文件更小, jvm不要考虑类的附加特性, 如方法重载等, 因而更为高效.  
//3. 避免被子类修改
//原文：https://blog.csdn.net/voo00oov/article/details/50433672 
 