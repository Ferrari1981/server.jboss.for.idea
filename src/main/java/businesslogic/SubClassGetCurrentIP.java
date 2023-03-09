package businesslogic;


import dsu1.glassfish.atomic.SubClassWriterErros;

import java.net.InetAddress;
import javax.enterprise.context.Dependent;
import javax.servlet.ServletContext;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Produces;
@Dependent
public class SubClassGetCurrentIP {
    @Produces
    protected boolean МетодПолучениеСвоегоIPадресса(@NotNull ServletContext ЛОГ) {
        boolean РезультатКакойIPСвой=false;
        InetAddress  ip = null;
        try{
            ip = InetAddress.getLocalHost();
            String  Name = ip.getHostName();
            String  Address = ip.getHostAddress();
            ЛОГ.log(" Name" +Name + " Address " +Address  + " ip " +ip);
            //  if(	      Address.equalsIgnoreCase("192.168.254.40")){
            if(	      Name.equalsIgnoreCase("PcProgram")){
                РезультатКакойIPСвой=true;
                ЛОГ.log(" DEBUG INERT Name" +Name + " Address " +Address  + " ip " +ip);
            }
        } catch (Exception e) {
            new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, null,
                    this.getClass().getMethods().toString()+ " "+this.getClass().getCanonicalName().toString()
                            +" "+this.getClass().getDeclaredMethods().toString(),
                    Thread.currentThread().getStackTrace()[2], null, ip.toString());
        }
        return РезультатКакойIPСвой;
    }
}