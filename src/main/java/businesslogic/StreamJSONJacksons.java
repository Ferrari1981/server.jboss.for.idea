package businesslogic;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.dataformat.cbor.CBORFactory;
import com.fasterxml.jackson.dataformat.cbor.CBORGenerator;
import dsu1glassfishatomic.workinterfaces.ProducedJacson;
import dsu1glassfishatomic.workinterfaces.ProducedStreamJacsons;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.io.ByteArrayOutputStream;
import java.io.DataOutput;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


@ApplicationScoped
public  class StreamJSONJacksons{
    @Inject
    ObjectMapper getGeneratorJackson;
        // TODO ГЕНЕРАЦИЯ JSON ПО  НОВОМУ Jackson
   public byte[] getStreamJacksons(@javax.validation.constraints.NotNull List<?> listОтHiberideДляГенерации)
            throws SQLException, SecurityException {
       byte[] БуферСозданогоJSONJackson = new byte[0];
        try {
            ObjectWriter writer = getGeneratorJackson.writerWithDefaultPrettyPrinter();
           // String Сгенерированыйjson = 	  writer.writeValueAsString(listОтHiberideДляГенерации);
           БуферСозданогоJSONJackson = 	  writer.writeValueAsBytes(listОтHiberideДляГенерации );
            System.out.println( " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " БуферСозданогоJSONJackson "+ БуферСозданогоJSONJackson.toString());
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println( " ERROR  class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        }
        return БуферСозданогоJSONJackson;
    }

}