<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>JQgrid-таблицы</title>
        <script type="text/javascript" src="./js/jquery-1.9.0.min.js"></script>
        <script type="text/javascript" src="./js/i18n/grid.locale-ru.js"></script>
        <script type="text/javascript" src="./js/jquery.jqGrid.min.js"></script>

        <link rel="stylesheet" href="./css/ui.jqgrid.css"/>
        <link rel="stylesheet" href="./css/jquery-ui-1.10.3.custom.min.css"/>
    </head>
    <body>
        <script type="text/javascript">
            $(document).ready(function(){
                jQuery("#regions").jqGrid({
                    url: 'rest/region/list',
                    datatype: 'json',
                    colNames:['ID', 'Название', 'Заблокирован', 'Удалить'],
                    colModel:[
                        {name:'id', align: 'center', width: 100},
                        {name:'title', align: 'center', editable: true},
                        {name:'locked', formatter: 'checkbox', align: 'center', width: 100, editable: true, edittype: 'checkbox'},
                        {name:'delete', align: 'center'}
                    ],
                    pager: '#navDiv',
                    viewrecords: true,
                    caption: 'Регионы',
                    rowNum: 10,
                    height: '100%',
                    width: 600,
                    editurl: 'rest/region/add'
                });
                jQuery("#regions").jqGrid('navGrid',"#navDiv",{edit:false,add:false,del:false,search: false});
                jQuery("#regions").jqGrid('inlineNav',"#navDiv");
            })

            <%--
                TODO: сделать регионы add\edit\delete на верные URL (неважно, насколько кривой будет JS)
                города и ТС будут работать аналогично
            --%>

        </script>

        <table id="regions"></table>
        <div id="navDiv"></div>
    </body>
</html>