package edu.cuit.jead.demo2.gateway;

import edu.cuit.jead.demo2.callers.CallerD;
import edu.cuit.jead.demo2.callers.CallerE;
import edu.cuit.jead.demo2.callers.CallerIntf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Scanner;

@Component
public class CallerGateway {

    @Resource
    private CallerIntf a;

    @Autowired
    private CallerIntf b;

    @Autowired
    @Qualifier("xc")
    private CallerIntf c;

    private CallerIntf d;
    private CallerIntf e;

    public CallerGateway()
    {
        d = new CallerD();
        e = new CallerE();
    }

    public char getSymbol()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input method type");
        return scanner.next().charAt(0);
    }

    public void doSome()
    {
        char inChar = getSymbol();
        while (inChar != 'x')
        {
            switch (inChar){
                case 'a':
                    a.callKernel();
                    break;
                case 'b':
                    b.callKernel();
                    break;
                case 'c':
                    c.callKernel();
                    break;
                case 'd':
                    d.callKernel();
                    break;
                case 'e':
                    e.callKernel();
                    break;
            }
            inChar = getSymbol();
        }

    }
}
