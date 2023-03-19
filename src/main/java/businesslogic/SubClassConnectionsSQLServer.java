package businesslogic;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.crypto.NoSuchPaddingException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.sql.DataSource;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Produces;

@RequestScoped
@Named
public class SubClassConnectionsSQLServer  {
    @Inject
    SubClassWriterErros subClassWriterErros;
    @SuppressWarnings("null")
    @Produces
    public Connection МетодGetConnect(
            @NotNull ServletContext ЛОГ) throws ClassNotFoundException, SQLException,
            NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        Connection  conn = null ;
        try {
					javax.naming.InitialContext ctx =  new InitialContext();
            DataSource datasource = (DataSource) ctx.lookup("java:/ResourceGlassfishBedug");//TODO это подключение в Дебаг
            ЛОГ.log("  datasource  "+datasource+ " ctx.getEnvironment() "+ctx.getEnvironment());
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
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return conn;
    }
    ///TODO второй метод
    @Produces
    public Statement МетодGetSmtr(@NotNull Connection conn,
                                  @NotNull ServletContext ЛОГ) throws SQLException {
        Statement statement = null ;
        try {
            ЛОГ.log(" conn " +conn.getClientInfo());
            statement = conn.createStatement();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);///// TODO
        } catch (Exception e) {
            subClassWriterErros.
                    МетодаЗаписиОшибкиВЛог(e,
                            Thread.currentThread().
                                    getStackTrace(),
                            ЛОГ,"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
        return statement;
    }


}
