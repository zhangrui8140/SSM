package com.Spring.Test.bean.OutPutGenerator;

public class FileNameGenerator {
    public String name;
    public String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void printFile(){
        System.out.println("FileName:"+name+"，Type:"+type);
    }
}