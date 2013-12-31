<%@ page import="java.math.BigInteger" %>
<%@ page import="com.syamka.hibernate.entity.tariff.TariffScale" %>
<%@ page import="com.syamka.hibernate.Util" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    /**
     *  TODO временное решение: нужна ведь обработка битых/несуществующих id и т.п..., я еще не решила, как буду это отруливать
     */
    TariffScale tariffScale = Util.getEm().find(TariffScale.class, new BigInteger(request.getParameter("id")));

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title><%=tariffScale.getTitle()%></title>
    <script type="text/javascript" src="./js/jquery-1.9.0.min.js"></script>
    <link rel="stylesheet" href="./css/jquery-ui-1.10.3.custom.min.css"/>
</head>
<body>
    <h4>Тарифная сетка <%=tariffScale.getTitle()%></h4>
    <p><%=tariffScale.getDescription()%></p>


    <%--
        Этот кусок есть компонент поиска тарифа. Его нужно будет инкапсулировать в отдельную jsp или типа того
    --%>
    <script>
        $(function() {
            var availableTags = [
              "Москва",
              "Питер",
              "Asp",
              "BASIC",
              "C",
              "C++",
              "Clojure",
              "COBOL",
              "ColdFusion",
              "Erlang",
              "Fortran",
              "Groovy",
              "Haskell",
              "Java",
              "JavaScript",
              "Lisp",
              "Perl",
              "PHP",
              "Python",
              "Ruby",
              "Scala",
              "Scheme"
            ];
            $( "input[name='region']" ).autocomplete({
              source: availableTags
            });
          });

    </script>

    <div class="ui-widget ui-widget-content ui-form" style="font-size:0.8em; width:500px; padding: 2px;">
        <div class="ui-widget-header">Поиск тарифа</div>
        <form>
            <div class="ui-widget">
                <label>Регион: </label> <input name="region" />
            </div>
        </form>

    </div>
</body>
</html>