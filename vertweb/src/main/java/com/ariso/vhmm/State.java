package com.ariso.vhmm;

import java.util.ArrayList;
 //隐状态
public class State {
    //这个用于  数据库
    public int    ID;
    // 状态名 人工建模
    public String State;
   // 初始概率（隐状态） 统计数据
    public float BeginProbability;
    // 迁移到下一个状态的可能概率 统计数据
    public ArrayList<NextProbaility> ProbailityJumpList;
    // 当前状态观察到的表象的可能性  统计数据
    public ArrayList<Observation> ObservationList;
}
