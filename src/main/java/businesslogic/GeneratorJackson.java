package businesslogic;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import dsu1glassfishatomic.workinterfaces.ProducedJacson;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;


@ApplicationScoped
@ProducedJacson
public class GeneratorJackson {
    @Inject
    SubClassWriterErros subClassWriterErros;
    // TODO: 23.03.2023  ПАСРСИНГ  JSON JSCSON
// TODO: 23.03.2023  БИБЛИОТКЕ Jackson для созданиия И ПАРСИНГА JSON
    @Produces
    public ObjectMapper getGeneratorJackson()   {
        ObjectMapper mapperJackson = null;
        try{
            //TODO Jacson парсинг JSON
            JsonFactory factory = new JsonFactory();
            mapperJackson = new ObjectMapper(factory);
            mapperJackson.writerWithDefaultPrettyPrinter();
            mapperJackson.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
            //df.setTimeZone(TimeZone.getTimeZone("Russia/Moscow"));
      /*      DateFormat df = new SimpleDateFormat("yyyy-MM-dd", new Locale("ru"));
            df.setTimeZone(TimeZone.getTimeZone("Russia/Moscow"));
            mapperJackson.setDateFormat(df);*/
          // mapperJackson.setTimeZone(TimeZone.getTimeZone("UTC+3"));
            mapperJackson.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
            mapperJackson.setLocale(new Locale("ru"));
            mapperJackson.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            mapperJackson.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            mapperJackson.enable(SerializationFeature.FLUSH_AFTER_WRITE_VALUE);
            mapperJackson .enable(SerializationFeature.INDENT_OUTPUT);

          System.out.println( " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " mapperJackson "+mapperJackson);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println( " ERROR  class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " mapperJackson "+mapperJackson);
        }
        return  mapperJackson;

    }

}

