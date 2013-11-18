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

    <%-- не работают красивые ddUrl /rest/region/REGION_ID/city/edit/CITY_ID--%>

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

                    onSelectRow: function(ids) {
                        if(ids != null){
                            jQuery("#cities").jqGrid('setGridParam',{url:"rest/region/" + ids + "/city/list",page:1}).trigger('reloadGrid');;
                            currentRegion = ids;
                        }
                    }

                });
                <%-- Переопределяем кучу всего, собственно, хотим юзать Rest урлы --%>
                jQuery("#regions").jqGrid('navGrid',"#navDiv",{edit:false,add:false,del:false,search: false});
                jQuery("#regions").jqGrid('inlineNav',"#navDiv",{
                       edit: true,
                       editicon: "ui-icon-pencil",
                       add: true,
                       addicon:"ui-icon-plus",
                       save: true,
                       saveicon:"ui-icon-disk",
                       cancel: true,
                       cancelicon:"ui-icon-cancel",
                       addParams : {
                           keys: true,
                           url: "rest/region/add"
                       },
                       editParams : {
                           keys: true,
                           oneditfunc: function(id){
                               $("#regions").setGridParam({editurl:"rest/region/edit/" + id});
                           }
                       }
                });

                var currentRegion = 0;
                function getCurrentRegion(){
                    return currentRegion;
                }

                jQuery("#cities").jqGrid({
                    datatype: 'json',
                    colNames:['ID', 'Название', 'Заблокирован', 'Удалить'],
                    colModel:[
                        {name:'id', align: 'center', width: 100},
                        {name:'title', align: 'center', editable: true},
                        {name:'locked', formatter: 'checkbox', align: 'center', width: 100, editable: true, edittype: 'checkbox'},
                        {name:'delete', align: 'center'}
                    ],
                    pager: '#cNavDiv',
                    viewrecords: true,
                    caption: 'Города',
                    rowNum: 10,
                    height: '100%',
                    width: 600
                });
                <%-- Переопределяем кучу всего, собственно, хотим юзать Rest урлы --%>
                jQuery("#cities").jqGrid('navGrid',"#cNavDiv",{edit:false,add:false,del:false,search: false});
                jQuery("#cities").jqGrid('inlineNav',"#cNavDiv",{
                       edit: true,
                       editicon: "ui-icon-pencil",
                       add: true,
                       addicon:"ui-icon-plus",
                       save: true,
                       saveicon:"ui-icon-disk",
                       cancel: true,
                       cancelicon:"ui-icon-cancel",
                       addParams:{
                           addRowParams: {
                                keys: true,
                                url: "rest/region/add",
                                oneditfunc: function(id){
                                    $("#cities").setGridParam({editurl:"rest/region/"+getCurrentRegion()+"/city/add"});
                                }
                            }
                       },
                       editParams : {
                           keys: true,
                           oneditfunc: function(id){
                               $("#cities").setGridParam({editurl:"rest/region/"+getCurrentRegion()+"/city/edit/" + id});
                           }
                       }
                });




            })

        </script>

        <table id="regions"></table>
        <div id="navDiv"></div>

        <br/>

        <table id="cities"></table>
        <div id="cNavDiv"></div>

    </body>
</html>