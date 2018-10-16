package com.wxstc.dl.util;

import java.util.ArrayList;

public class MyArrayList extends ArrayList{
    public ArrayList arrayList;
    public MyArrayList(ArrayList arr){
        this.arrayList = arr;
    }
    public String getOrElse(int index){
        Object o = arrayList.get(index);
        if(o==null)
            return "";
        return o.toString();
    }

    public Object get(int index){
        return arrayList.get(index);
    }
}
