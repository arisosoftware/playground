package com.ariso.blockchain;

import java.util.ArrayList;

public class Message {
    public String GUID;
    
    
    public ArrayList<String> BlockListMD5Key;
     
}


class Block
{
    public String MD5Key;
    public boolean IsCompress;
    public byte[] Data;    
}