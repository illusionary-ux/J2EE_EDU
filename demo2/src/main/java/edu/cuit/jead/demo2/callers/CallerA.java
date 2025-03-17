package edu.cuit.jead.demo2.callers;

import edu.cuit.jead.demo2.kernel.CompKernel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("a")
public class CallerA implements CallerIntf {

    @Autowired
    private CompKernel kernel;

    @Override
    public void callKernel()
    {
        try {
            String callerInfo = this.toString();
            kernel.doIt(callerInfo);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
