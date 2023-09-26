package businesslogic;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.cbor.CBORFactory;
import com.fasterxml.jackson.dataformat.cbor.CBORGenerator;
import dsu1glassfishatomic.workinterfaces.ProducedJacson;
import dsu1glassfishatomic.workinterfaces.ProducedJacsonCbor;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.ByteArrayOutputStream;
import java.util.Locale;
import java.util.TimeZone;

import static com.fasterxml.jackson.core.JsonFactory.Feature.USE_THREAD_LOCAL_FOR_BUFFER_RECYCLING;


@ApplicationScoped
@Named("generatorJackson")
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
            //factory._getBufferRecycler().allocByteBuffer(2048);
            //CBORFactory factory = new CBORFactory();
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
            mapperJackson.setSerializationInclusion(JsonInclude.Include.ALWAYS);
            mapperJackson.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            mapperJackson.enable(SerializationFeature.FLUSH_AFTER_WRITE_VALUE);
            mapperJackson .enable(SerializationFeature.INDENT_OUTPUT);
            mapperJackson.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

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

    // TODO: 23.03.2023  БИБЛИОТКЕ Jackson для созданиия И ПАРСИНГА JSON
    @Produces
    public CBORGenerator getGeneratorCBORJackson()   {
        CBORGenerator generator= null;
        try{
            //TODO Jacson парсинг JSON
            CBORFactory factory = new CBORFactory();
            factory._getBufferRecycler().allocByteBuffer(2048);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            generator=          factory.createGenerator(byteArrayOutputStream);
            System.out.println( " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " generator "+generator);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println( " ERROR  class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        }
        return  generator;

    }

}

