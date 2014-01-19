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
    <script type="text/javascript" src="./js/jquery-ui.js"></script>
    <link rel="stylesheet" href="./css/jquery-ui-1.10.3.custom.min.css"/>
</head>
<body>
    <h4>Тарифная сетка <%=tariffScale.getTitle()%></h4>
    <p><%=tariffScale.getDescription()%></p>


    <%--
        Этот кусок есть компонент поиска тарифа. Его нужно будет инкапсулировать в отдельную jsp или типа того
    --%>
    <script>

        function initRegions(){
            $.ajax({
                url: "rest/region/list",
                dataType: "json",
                data: {},
                success: function(data){

                    $( "input[name='region']" ).autocomplete("option","source",
                        $.map(data.rows, function(item){
                            return {
                                label: item.title,
                                value: item.title,
                                id: item.id
                            }
                        }));
                }
            })
        }

        function initCities(region_id){
            $.ajax({
                url: "rest/region/" + region_id + "/city/list",
                dataType: "json",
                data: {},
                success: function(data){

                    $( "input[name='city']" ).autocomplete("option","source",
                            $.map(data.rows, function(item){
                                return {
                                    label: item.title,
                                    value: item.title,
                                    id: item.id
                                }
                            }));
                }
            })
        }

        $(function() {
            $( "input[name='region']" ).autocomplete({
                select: function( event, ui ) {
                    initCities(ui.item.id);
                }
            });
            initRegions();
            $( "input[name='city']" ).autocomplete();


        });

    </script>

    <div class="ui-widget ui-widget-content ui-form" style="font-size:0.8em; width:500px; padding: 2px;">
        <div class="ui-widget-header">Поиск тарифа</div>
        <form>
            <div class="ui-widget">
                <table>
                    <tr>
                        <td><label>Регион: </label></td>
                        <td><input name="region" /></td>
                    </tr>
                    <tr>
                        <td><label>Город: </label></td>
                        <td><input name="city" /></td>
                    </tr>
                </table>

            </div>
        </form>

    </div>
</body>
</html>