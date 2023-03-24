
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ page import="java.util.*, java.text.* ,java.net.ServerSocket , java.net.UnknownHostException, java.io.BufferedReader,java.io.*,java.util.stream.*,java.nio.charset.Charset"%>
<%@ page import="javax.inject.Inject" %>
<%@ page import="dsu1glassfishatomic.workinterfaces.ProducedCard" %>
<%@ page import="org.hibernate.SessionFactory" %>
<%@ page import="java.nio.file.Path" %>
<%@ page import="java.nio.file.Paths" %>
<%!
    @Inject
    @ProducedCard
    SessionFactory sessionSousJboss;

    String getFormattedDate()
    {



        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss.SSS");
        System.out.println( "Create Stranizi Sosdannoy DSU-1  JSP --page "+sdf.format(new Date()));
        return sdf.format(new Date());

    }





    String getSessions()
    {
        String ПолученныеОшибки=null;
        Charset cs1;
        Charset cs2;
        try {
            cs1=Charset.forName("Cp1251");
            cs2=Charset.forName("UTF-8");
            StringBuffer stringBufferВсеОшибки=new StringBuffer();
            //BufferedReader reader = new BufferedReader(new FileReader(new File("C:\\JBOSS\\ErrorJbossServletDSU1.txt")));
            BufferedReader reader = new BufferedReader(new FileReader(new File("ErrorsLogs/ErrorJbossServletDSU1.txt")));
            ПолученныеОшибки=  reader.lines().collect(Collectors.joining(System.lineSeparator()));
            stringBufferВсеОшибки.append("ErrorJbossServletDSU1").append("\n").append(ПолученныеОшибки).append("\n");
            // TODO: 19.03.2023 error2
             reader = new BufferedReader(new FileReader(new File("ErrorsLogs/ErrorJbossServletAuntification.txt")));
            ПолученныеОшибки=  reader.lines().collect(Collectors.joining(System.lineSeparator()));
            stringBufferВсеОшибки.append("ErrorJbossServletDSU1").append("\n").append(ПолученныеОшибки).append("\n");
            // TODO: 19.03.2023 error3
            reader = new BufferedReader(new FileReader(new File("ErrorsLogs/ErrorJbossServletRuntime.txt")));
            ПолученныеОшибки=  reader.lines().collect(Collectors.joining(System.lineSeparator()));
            stringBufferВсеОшибки.append("ErrorJbossServletRuntime").append("\n").append(ПолученныеОшибки).append("\n");
            // TODO: 19.03.2023 error4
            reader = new BufferedReader(new FileReader(new File("ErrorsLogs/ErrorJbossServletScanner.txt")));
            ПолученныеОшибки=  reader.lines().collect(Collectors.joining(System.lineSeparator()));
            stringBufferВсеОшибки.append("ErrorJbossServletScanner").append("\n").append(ПолученныеОшибки).append("\n");
            // TODO: 19.03.2023 error5
            reader = new BufferedReader(new FileReader(new File("ErrorsLogs/ErrorJbossServletUpdatePO.txt")));
            ПолученныеОшибки=  reader.lines().collect(Collectors.joining(System.lineSeparator()));
            stringBufferВсеОшибки.append("ErrorJbossServletUpdatePO").append("\n").append(ПолученныеОшибки).append("\n");

            System.out.println( "stringBufferВсеОшибки   "+stringBufferВсеОшибки.toString());
            if(stringBufferВсеОшибки.toString().length()==0){
                ПолученныеОшибки="DON'T ERROR SERVER";
            }else{
                ПолученныеОшибки=new String( stringBufferВсеОшибки.toString().getBytes(cs1), cs2);
            }
            reader.close();
        } catch (Exception e1) {
            // TODO Автоматически созданный блок catch
            e1.printStackTrace();
        }
        return ПолученныеОшибки;

    }




%>
<%@ page contentType="text/html; charset=UTF-8"  %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here dsu-1 glassfish 4.1</title>
</head>
<body style="background-color:grey;" >

<label for="range-1">Slider:</label>
<input name="range-1" id="range-1" data-highlight="true" min="0" max="100" value="40" type="range"/>
<br><i>---------------------------------------------------------------------------------------------------------</i><br>
<h2>Добро пожаловать я страница JSP Red Hat Jboss 7 на intellij idea </h2>
<h1> ООО "Союз Автодор Иваново "</h1>

<h1><i>Сегодня <%= getFormattedDate() %></i></h1>
<h1><i>Ошибки на Сервере: <%= getSessions() %></i></h1>
<h1><i> Статус Hibernate : <%= sessionSousJboss %></i></h1>
<h1><i> Request Context Path: <%= request.getContextPath() %></i></h1>
<h1><i> Request RequestURI: <%= request.getRequestURI() %></i></h1>
<h1><i> Request URI: <%= request.getContextPath() %></i></h1>
<h1><i> Request URL: <%= request.getRequestURL() %></i></h1>
<h1><i> Request getPathInfo: <%= request.getServletContext().getResourcePaths("") %></i></h1>
<h1><i> Request getRealPath: <%= request.getServletContext().getRealPath("/") %></i></h1>
<h1><i> Request getContextPath: <%= request.getServletContext().getContextPath() %></i></h1>
<h1><i> exists() update_android_dsu1/output-metadata.json: <%=   Paths.get("update_android_dsu1/output-metadata.json").toFile().exists() %></i></h1>
<h1><i> isFile() update_android_dsu1/output-metadata.json: <%=   Paths.get("update_android_dsu1/output-metadata.json").toFile().isFile() %></i></h1>
<br><i>----------------------------------------------------------------------------------------------------</i><br>
<h1><i> exists() update_android_dsu1/app-release.apk: <%=   Paths.get("update_android_dsu1/app-release.apk").toFile().exists() %></i></h1>
<h1><i> isFile() update_android_dsu1/app-release.apk: <%=   Paths.get("update_android_dsu1/app-release.apk").toFile().isFile() %></i></h1>
<br><i>----------------------------------------------------------------------------------------------------</i><br>
<h1><i> isFile() add-user.properties: <%=   Paths.get("C:\\JBOSS\\EAP-7.4.0\\bin\\add-user.properties").toFile().isFile() %></i></h1>
<h1><i> exists() add-user.properties: <%=   Paths.get("C:\\JBOSS\\EAP-7.4.0\\bin\\add-user.properties").toFile().exists() %></i></h1>
<br><i>----------------------------------------------------------------------------------------------------</i><br>

<br><i>----------------------------------------------------------------------------------------------------</i><br>
<h1><i> MOSKVA isFile() add-user.properties: <%=   Paths.get("C:\\RedHatJboss\\EAP-7.4.0\\bin\\add-user.properties").toFile().isFile() %></i></h1>
<h1><i> MOSKVA exists() add-user.properties: <%=   Paths.get("C:\\RedHatJboss\\EAP-7.4.0\\bin\\add-user.properties").toFile().exists() %></i></h1>
<br><i>----------------------------------------------------------------------------------------------------</i><br>


<h1><i> LOG exists() ErrorJbossServletDSU1.txt: <%=   Paths.get("ErrorsLogs/ErrorJbossServletDSU1.txt").toFile().exists() %></i></h1>
<h1><i>  LOG isFile() ErrorJbossServletDSU1.txt: <%=   Paths.get("ErrorsLogs/ErrorJbossServletDSU1.txt").toFile().isFile() %></i></h1>

<h2><i> Адрес: Проездная ул., 18, Иваново, Ивановская обл.</i></h2>
<h2><i>     <font size="6" color="#fa8e47" face="serif">"Версия 805  Hibernate and Jakson"</font> </i></h2>
<br><i>----------------------------------------------------------------------------------------------------</i><br>
<br/>
<a href="sous.jboss.tabel">Нажми Пинг dsu1.glassfish.atomic  </a>
<br><i>----------------------------------------------------------------------------------------------------</i><br>
<a href="sous.jboss.download">Нажми Пинг dsu1glassfishatomic.glassfish.download  </a>
<br><i>----------------------------------------------------------------------------------------------------</i><br>
<a href="sous.jboss.scanner">Нажми Пинг dsu1glassfishatomic.glassfish.scanner  </a>
<br><i>----------------------------------------------------------------------------------------------------</i><br>
<a href="sous.jboss.runtimejboss">Нажми Пинг dsu1glassfishatomic.glassfish.runtimejboss </a>
<br><i>----------------------------------------------------------------------------------------------------</i><br>
<a href="sous.jboss.authentication">Нажми Пинг dsu1glassfishatomic.glassfish.authentication  </a>
<br><i>----------------------------------------------------------------------------------------------------</i><br>
<input type="hidden" name="ipaddress" value="<%=request.getRemoteAddr()%>"/>
<br><i>----------------------------------------------------------------------------------------------------</i><br>
<form action="Dsu1glassfish" method="GET"></form>
</body>
</html>