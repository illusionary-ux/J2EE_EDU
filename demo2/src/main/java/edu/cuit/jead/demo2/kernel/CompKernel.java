package edu.cuit.jead.demo2.kernel;

import org.springframework.stereotype.Component;

@Component
public class CompKernel {

    public static String shortName(String str) {
        return str.substring(str.lastIndexOf('.') + 1);
    }

    public void doIt(String callerInfo)
    {
        String ret = shortName(this.toString());
        //this.toString() 返回当前对象的字符串表示形式
        System.out.println(ret + " in " +shortName(callerInfo) + '\n');
    }
}
