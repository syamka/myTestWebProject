<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>Настройки</title>
        <script type="text/javascript" src="./js/jquery-1.9.0.min.js"></script>
        <script type="text/javascript" src="./js/i18n/grid.locale-ru.js"></script>
        <script type="text/javascript" src="./js/jquery.jqGrid.min.js"></script>


        <link rel="stylesheet" href="./css/jquery-ui-1.10.3.custom.min.css"/>
        <link rel="stylesheet" href="./css/ui.jqgrid.css"/>
        <style type="text/css">
            .ui-jqgrid .ui-jqgrid-btable { cursor : pointer; }
            .ui-jqgrid tr.jqgrow td {
                word-wrap: break-word; /* IE 5.5+ and CSS3 */
                white-space: pre-wrap; /* CSS3 */
                white-space: -moz-pre-wrap; /* Mozilla, since 1999 */
                white-space: -pre-wrap; /* Opera 4-6 */
                white-space: -o-pre-wrap; /* Opera 7 */
                overflow: hidden;
                height: auto;
                vertical-align: middle;
                padding-top: 3px;
                padding-bottom: 3px
            }
        </style>
    </head>
    <body>

        <script type="text/javascript">

            <!-- РЕГИОНЫ -->
            var REGIONS_URL = 'rest/region';

            $(document).ready(function(){
                jQuery("#regions").jqGrid({
                    url: REGIONS_URL + '/list',
                    datatype: 'json',
                    colNames:['ID', 'Название', 'Заблокирован'],
                    colModel:[
                        {name:'id', align: 'center', width: 100},
                        {name:'title', align: 'center', editable: true},
                        {name:'locked', formatter: 'checkbox', align: 'center', width: 100, editable: true, edittype: 'checkbox', editoptions: { value:"true:false" }}
                    ],
                    pager: '#navDiv',
                    viewrecords: true,
                    caption: 'Регионы',
                    sortname: 'id',
                    sortorder: 'asc',
                    rowNum: 10,
                    height: '100%',
                    width: 600,

                    onSelectRow: function(ids) {
                        if(ids != null){
                            jQuery("#cities").jqGrid('setGridParam',{url:"rest/region/" + ids + "/city/list",page:1, datatype:'json'}).trigger('reloadGrid');
                            currentRegion = ids;
                        }
                    }

                });

                function handleResponse(response, postdata){
                    var respObj = $.parseJSON(response.responseText);
                    return [respObj.success, respObj.message, (respObj.id != undefined)?respObj.id:0];
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

                <!-- ГОРОДА -->
                jQuery("#cities").jqGrid({
                    datatype: 'json',
                    colNames:['ID', 'Название', 'Заблокирован'],
                    colModel:[
                        {name:'id', align: 'center', width: 100},
                        {name:'title', align: 'center', editable: true},
                        {name:'locked', formatter: 'checkbox', align: 'center', width: 100, editable: true, edittype: 'checkbox', editoptions: { value:"true:false" }}
                    ],
                    pager: '#cNavDiv',
                    viewrecords: true,
                    pgbuttons: false,
                    pgtext: null,
                    sortname: 'id',
                    sortorder: 'asc',
                    loadonce: true,
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


                jQuery("#cities").jqGrid('navGrid',"#cNavDiv",{edit:true,add:true,del:true,search:false, refresh:false},editCityOptions, addCityOptions, delCityOptions);

                <!-- ТАРИФНЫЕ СЕТКИ -->
                var TARIFF_SCALES_URL = 'rest/scale'

                jQuery("#tariffScales").jqGrid({
                    datatype: 'json',
                    url: TARIFF_SCALES_URL + "/list",
                    colNames:['ID', 'Название', 'Описание', 'Главная'],
                    colModel:[
                        {name:'id', align: 'center', width: 50},
                        {name:'title', align: 'center', editable: true},
                        {name:'description', align: 'left', editable: true, width: 500, edittype: "textarea", editoptions: {rows:"6",cols:"100"}},
                        {name:'main', formatter: 'checkbox', align: 'center', width: 100, editable: true, edittype: 'checkbox', editoptions: { value:"true:false" }}
                    ],
                    pager: '#tsNavDiv',
                    viewrecords: true,
                    sortname: 'id',
                    sortorder: 'asc',
                    caption: 'Тарифные сетки',
                    rowNum: 10,
                    height: '100%',
                    width: 1000
                });

                var editTsOptions = {
                    onclickSubmit: function(params, postdata) {
                        params.url = TARIFF_SCALES_URL + '/edit/' + postdata.tariffScales_id;
                    },
                    afterSubmit: handleResponse,
                    reloadAfterSubmit: false,
                    width: 600,
                    height: 230,
                    beforeShowForm: function (formid)
                    {
                        $("#tr_main",formid).show();
                    }
                };
                var addTsOptions = {
                    mtype: "POST",
                    onclickSubmit: function(params, postdata) {
                        params.url = TARIFF_SCALES_URL + '/add';
                    },
                    afterSubmit: handleResponse,
                    reloadAfterSubmit: false,
                    width: 600,
                    height: 230,
                    beforeShowForm: function (formid)
                    {
                        $("#tr_main",formid).hide();
                    }
                };
                var delTsOptions = {
                    onclickSubmit: function(params, postdata) {
                        params.url = TARIFF_SCALES_URL + '/delete/' + postdata;
                    },
                    afterSubmit: handleResponse,
                    reloadAfterSubmit: false
                };

                jQuery("#tariffScales").jqGrid('navGrid',"#tsNavDiv",{edit:true,add:true,del:true,search:false, refresh:true},editTsOptions, addTsOptions, delTsOptions);

            })

        </script>
        <div align="center">
            <table id="regions"></table>
            <div id="navDiv"></div>

            <br/>

            <table id="cities"></table>
            <div id="cNavDiv"></div>

            <br />

            <table id="tariffScales"></table>
            <div id="tsNavDiv"></div>

        </div>

    </body>
</html>