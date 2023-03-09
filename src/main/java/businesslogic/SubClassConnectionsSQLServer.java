package businesslogic;

import dsu1.glassfish.atomic.SubClassWriterErros;
import org.hibernate.SessionFactory;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.annotation.Resource;
import javax.crypto.NoSuchPaddingException;
import javax.enterprise.context.RequestScoped;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.servlet.ServletContext;
import javax.sql.DataSource;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Produces;


@RequestScoped
public class SubClassConnectionsSQLServer extends SubClassGetCurrentIP  {
    @SuppressWarnings("null")
    @Produces
    Connection   МетодПредворительногоПодключенияДляМетодаGETкодИзConnection(
            @NotNull ServletContext ЛОГ) throws ClassNotFoundException, SQLException,
            NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        Connection  conn = null ;
        try {
					/*Class.forName("com.microsoft.sqlserver.jdbc.SQLServerConnectionPoolDataSource");
						conn = DriverManager.getConnection(СтрокакКакомуСерверуБудетПодключениеДЛяSQlServer); //// ПОДКЛЮЧЕМСЯ
*/						javax.naming.InitialContext ctx =  new InitialContext();

            DataSource datasource =null;
            boolean КакойIP=  МетодПолучениеСвоегоIPадресса(ЛОГ);
            if(КакойIP==true){
                datasource = (DataSource) ctx.lookup("java:/ResourceGlassfishBedug");//TODO это подключение в Дебаг
            }else{
                datasource = (DataSource) ctx.lookup("java:/CoonectionAndroidBeanTimer");//TODO это подключение в РЕЛИЗ
            }
            ЛОГ.log("  КакойIP  "+КакойIP+ " datasource " +datasource);

            if (datasource != null) {
                conn = datasource.getConnection();
            }
            if (conn != null) {
                conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);//// устанавливаем
                conn.setHoldability(ResultSet.HOLD_CURSORS_OVER_COMMIT);
                ЛОГ.log(" SUCCESSS CONNECTION WITH DATABASE SOUS DSU1 "+ conn.getClientInfo());
            }else {
                ЛОГ.log(" ERROR  CONNECTION WITH DATABASE SOUS DSU1 "+ conn.getClientInfo());
            }
        } catch (Exception e) {
            new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, null,
                    "\n"+" Error.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                            " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n",
                    Thread.currentThread().getStackTrace()[2], null, conn.getClientInfo().toString());
        }
        return conn;
    }
    ///TODO второй метод
    @Produces
    Statement МетодПредворительногоПодключенияДляМетодаGETкодИзStatement ( @NotNull   Connection conn,
                                                                           @NotNull ServletContext ЛОГ) throws SQLException {
        Statement statement = null ;
        try {
            ЛОГ.log(" conn " +conn.getClientInfo());
            statement = conn.createStatement();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);///// TODO
        } catch (Exception e) {
            new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, null,
                    "		void МетодПредворительногоПодключенияДляМетодаGETкодИзКонструктора() throws ClassNotFoundException, SQLException,",
                    Thread.currentThread().getStackTrace()[2], null, conn.getClientInfo().toString());
        }
        return statement;
    }


}
