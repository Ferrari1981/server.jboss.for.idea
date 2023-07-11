package businesslogic;



import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @author moraru_pi
 *
 */
@ApplicationScoped
public class SubClassWriterErros {

    @SuppressWarnings("deprecation")
    public void МетодаЗаписиОшибкиВЛог(@NotNull  Exception e,
                                       @NotNull StackTraceElement[] ТекущийПотокВмоментВыполения,
                                       @NotNull ServletContext 	ЛОГ,
                                       @NotNull String ПутьНАхожденияФайлаЛогами) {
        e.printStackTrace();
        // TODO

        //String путьЗаписиЖурналаКудаЗаписовать ="C:\\SERVERS\\ErrorServletDSU1.txt";
        //String путьЗаписиЖурналаКудаЗаписовать ="C:\\JBOSS\\ErrorJbossServletDSU1.txt";
        //String путьЗаписиЖурналаКудаЗаписовать ="C:\\SERVERS\\glassfish-4.1.2\\glassfish4\\ErrorServletDSU1.txt";
        //TODO C:\SERVERS\glassfish-4.1.2\glassfish4   C:\\glassfish-4.1.2 dsu1glassfishatomic\\glassfish4\\ErrorServletDSU1.txt";/////ErrorServletDSU1
        String РЕальныйПутьСервера1 = ЛОГ.getRealPath("/");
        String РЕальныйПутьСервера2 = ЛОГ.getContextPath().toString();

        String САМАОШИБКАДЛЯЗАПИСИ = " Класс/CLASS :"
                + ТекущийПотокВмоментВыполения[2].getClassName() + "\n" + " Class/Класс :" + "\n"
                + ТекущийПотокВмоментВыполения[2].getMethodName() + "\n" + " Metod/Метод  :"
                + ТекущийПотокВмоментВыполения[2].getLineNumber() + "\n" + " Line/Линия  :"
                + ТекущийПотокВмоментВыполения[2].getFileName() + "\n" + " САМА ОШИБКА /GET ERROR ::: " + e.toString() + " Файл  :"
                + Thread.currentThread().getStackTrace()[2].getFileName() + "\n" + " САМА ОШИБКА/GET ERROR ::: " + e.toString()
                + "\n" + "  Класс  ОШИБКЕ   " + "\n" + e.getClass().getName() + "\n" + "  Класс  ОШИБКЕ /CLASS ERROR  " + "\n"
                + e.toString().toUpperCase().toString() + "\n" + "  метод  ОШИБКЕ /METOD ERROR  " + "\n"
                + e.fillInStackTrace().getMessage() + "\n" + "e.fillInStackTrace().getLocalizedMessage() "
                + e.fillInStackTrace().getLocalizedMessage() + "\n"+ "ПутьНАхожденияФайлаЛогами "
                +ПутьНАхожденияФайлаЛогами;
        ////// начало запись в файл
        System.err.println("public class ClassWriterErrorProjectDsu1 {  Метод : ERROR B SAMOM MOTODE ERROR GENERETOR "
                + e.toString());
        Object ЛогинПолученныйОтКлиента = Optional.ofNullable(ЛОГ.getAttribute("ЛогинПолученныйОтКлиента")).orElse("");
        Object IDДевайсаПолученныйОтКлиента = Optional.ofNullable(ЛОГ.getAttribute("АдуДевайсяКлиента")).orElse("");
            ЛОГ.log(
                    "\n"+" Inside Error.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                            " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"
                            + " ЛогинПолученныйОтКлиента " +ЛогинПолученныйОтКлиента+
             " IDДевайсаПолученныйОтКлиента "+ IDДевайсаПолученныйОтКлиента+" САМАОШИБКАДЛЯЗАПИСИ " +САМАОШИБКАДЛЯЗАПИСИ);
        try  (PrintWriter pw =
                      new PrintWriter(new OutputStreamWriter(new FileOutputStream(new File(ПутьНАхожденияФайлаЛогами),
                              true), StandardCharsets.UTF_8));) {
            //
            // перевод строки в байты
            pw.append("\n");
            pw.append("Время");
            pw.append("\n");
            pw.append(new Date().toGMTString().toString());
            pw.append("\n");
            pw.append("######################error start JBOSS EAP ########################### время " + new Date());
            pw.append("\n");
            pw.println(ПутьНАхожденияФайлаЛогами);
            pw.append("\n");
            pw.append("Логин чья ошибка");
            pw.append("\n");
            pw.append(ЛогинПолученныйОтКлиента.toString());
            pw.println("НАЗВАНИЕ ТАБЛИЦЫ ГДЕ ПРОИЗОШДА ОШИБКА//CURRENT TABLE ASYNC :: ");
            pw.append("\n");
            pw.append("ID Девайса Пользователя чья ошибка");
            pw.append("\n");
            pw.append(IDДевайсаПолученныйОтКлиента.toString());
            pw.println("НАЗВАНИЕ ТАБЛИЦЫ ГДЕ ПРОИЗОШДА ОШИБКА//CURRENT TABLE ASYNC :: ");
            pw.append("\n");
            pw.println(ПутьНАхожденияФайлаЛогами);
            pw.append("\n");
            pw.append("Дата ошибки");
            pw.append("\n");
            pw.append(" " + new SubClassGeneratorDate()
                    .ДатаВремяОперациисБезКовычекЗаписямиСервлета() + "  ");
            pw.append("\n");
            pw.append(САМАОШИБКАДЛЯЗАПИСИ);
            pw.append("\n");
            pw.append("#####################error end ############################ " + new Date());
            pw.append("\n");
            pw.append(	ЛОГ.getContextPath().toString());
            pw.append("\n");
            pw.append("		РЕальныйПутьСервера");
            pw.append("\n");
            pw.append(	РЕальныйПутьСервера2);
            pw.append("\n");
            /// todo close message send
            pw.flush();
            pw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println(
                    "\n"+"Inside Error.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                            " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n" + e.toString());
        }

    }

}
