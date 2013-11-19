<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>JQgrid-таблицы</title>
        <script type="text/javascript" src="./js/jquery-1.9.0.min.js"></script>
        <script type="text/javascript" src="./js/i18n/grid.locale-ru.js"></script>
        <script type="text/javascript" src="./js/jquery.jqGrid.min.js"></script>


        <link rel="stylesheet" href="./css/jquery-ui-1.10.3.custom.min.css"/>
        <link rel="stylesheet" href="./css/ui.jqgrid.css"/>
    </head>
    <body>

    <%-- не работают красивые ddUrl /rest/region/REGION_ID/city/edit/CITY_ID--%>

        <script type="text/javascript">

            var REGIONS_URL = 'rest/region';

            $(document).ready(function(){
                jQuery("#regions").jqGrid({
                    url: REGIONS_URL + '/list',
                    datatype: 'json',
                    colNames:['ID', 'Название', 'Заблокирован', 'Удалить'],
                    colModel:[
                        {name:'id', align: 'center', width: 100},
                        {name:'title', align: 'center', editable: true},
                        {name:'locked', formatter: 'checkbox', align: 'center', width: 100, editable: true, edittype: 'checkbox', editoptions: { value:"true:false" }},
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

                function handleResponse(response, postdata){
                    var respObj = $.parseJSON(response.responseText);
                    return [respObj.success == "true", respObj.message, (respObj.id != undefined)?respObj.id:0];
                }

                <%--
                    Спасибо этому дядечке !
                    http://www.javacodegeeks.com/2011/07/jqgrid-rest-ajax-spring-mvc-integration.html
                --%>
                var editOptions = {
                  onclickSubmit: function(params, postdata) {
                    params.url = REGIONS_URL + '/edit/' + postdata.regions_id;
                  },
                  afterSubmit: handleResponse,
                  reloadAfterSubmit: false
                };
                var addOptions = {
                    mtype: "POST",
                    onclickSubmit: function(params, postdata) {
                        params.url = REGIONS_URL + '/add'
                    },
                    afterSubmit: handleResponse,
                    reloadAfterSubmit: false
                };
                var delOptions = {
                    onclickSubmit: function(params, postdata) {
                        params.url = REGIONS_URL + '/delete/' + postdata;
                    },
                    afterSubmit: handleResponse,
                    reloadAfterSubmit: false
                };

                jQuery("#regions").jqGrid('navGrid',"#navDiv",{edit:true,add:true,del:true,search:false},editOptions, addOptions, delOptions);


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
                        {name:'locked', formatter: 'checkbox', align: 'center', width: 100, editable: true, edittype: 'checkbox', editoptions: { value:"true:false" }},
                        {name:'delete', align: 'center'}
                    ],
                    pager: '#cNavDiv',
                    viewrecords: true,
                    caption: 'Города',
                    rowNum: 10,
                    height: '100%',
                    width: 600
                });

                var editCityOptions = {
                  onclickSubmit: function(params, postdata) {
                    params.url = REGIONS_URL + '/' + getCurrentRegion() + '/city/edit/' + postdata.cities_id;
                  },
                  afterSubmit: handleResponse,
                  reloadAfterSubmit: false
                };
                var addCityOptions = {
                    mtype: "POST",
                    onclickSubmit: function(params, postdata) {
                        params.url = REGIONS_URL + '/' + getCurrentRegion() + '/city/add';
                    },
                    afterSubmit: handleResponse,
                    reloadAfterSubmit: false
                };
                var delCityOptions = {
                    onclickSubmit: function(params, postdata) {
                        params.url = REGIONS_URL + '/' + getCurrentRegion() + '/city/delete/' + postdata;
                    },
                    afterSubmit: handleResponse,
                    reloadAfterSubmit: false
                };


                jQuery("#cities").jqGrid('navGrid',"#cNavDiv",{edit:true,add:true,del:true,search:false},editCityOptions, addCityOptions, delCityOptions);

            })

        </script>

        <table id="regions"></table>
        <div id="navDiv"></div>

        <br/>

        <table id="cities"></table>
        <div id="cNavDiv"></div>

    </body>
</html>