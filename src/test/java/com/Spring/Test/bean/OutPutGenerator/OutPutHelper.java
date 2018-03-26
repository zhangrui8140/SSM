package com.Spring.Test.bean.OutPutGenerator;

import com.Spring.Test.interf.IOutPutGenerator;

public class OutPutHelper {
    IOutPutGenerator outPutGenerator;

    public void generateOutPut(){
        outPutGenerator.generateOutPut();
    }

    public void setOutPutGenerator(IOutPutGenerator inOutPutGenerator){
        this.outPutGenerator=inOutPutGenerator;
    }
}
