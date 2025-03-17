package edu.cuit.jead.demo2.callers;

import edu.cuit.jead.demo2.kernel.CompKernel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("d")
public class CallerD implements CallerIntf {


    private CompKernel kernel;

    public CallerD()
    {
        kernel = new CompKernel();
    }

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
