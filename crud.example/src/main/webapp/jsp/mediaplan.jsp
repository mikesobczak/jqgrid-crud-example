<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
    
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Media Plan</title>

<link rel="stylesheet" type="text/css" media="screen" href="js/jquery-ui-1.11.4.custom/jquery-ui.css" />
<link rel="stylesheet" type="text/css" media="screen" href="js/Guriddo_jqGrid_JS_5.2.1/css/ui.jqgrid.css" />

<style type="text/css">

/* For wrapping column header text */
th.ui-th-column div 
{
	white-space: normal !important;
	height: auto !important;
	padding: 2px;
}

body, th, td
{
	font-size: 0.9em !important;
	/*font-size: 10pt;*/
}
</style>

<script src="js/jquery-ui-1.11.4.custom/external/jquery/jquery.js" type="text/javascript"></script>
<script src="js/Guriddo_jqGrid_JS_5.2.1/js/i18n/grid.locale-en.js" type="text/javascript"></script>
<script src="js/Guriddo_jqGrid_JS_5.2.1/js/jquery.jqGrid.min.js" type="text/javascript"></script>

<script src="js/DateFormat.js" type="text/javascript"></script>

<script type="text/javascript">
$.urlParam = function(name, url) {
    if (!url) {
     url = window.location.href;
    }
    var results = new RegExp('[\\?&]' + name + '=([^&#]*)').exec(url);
    if (!results) { 
        return undefined;
    }
    return results[1] || undefined;
}

function dateFormatter(cellvalue, options, rowObject) {
	   
	   var date = new Date(cellvalue);
	   
	   //var value = DateFormat.format.date(date, 'MM/dd/yyyy');
	   var value = dateFormat(date, dateFormat.masks.shortDate);
	   
	   return value;
}

</script>

</head>
<body>
	
	<table id="jqGrid"></table>
    <div id="jqGridPager"></div>
    
<script type="text/javascript">    
    $(document).ready(function () {
	
	var mpId = $.urlParam('id');
	
	console.log('mpId: ' + mpId);

	$("#jqGrid").jqGrid({
	
		datatype: function(postdata) {
		    
	    	$('#' + 'load_' + 'jqGrid').show();
	    	
	    	//saveChanges();

			var rows = '' + postdata.rows;
			var page = '' + postdata.page;
			var sidx = postdata.sidx;
			var sord = postdata.sord;
			
			var pageSize = '' + postdata.rows;
			
			$.ajax({
		            async: false,
		            url: 'services/mediaplan',
		            type: 'GET',
		            dataType: 'json',
		            data: {'id' : mpId, 'page' : page, 'rows' : rows },
		            cache: false,
		            contentType: 'application/json',
		            error: function(jqXHR, textStatus, errorThrown) {
		            	var msg = 'textStatus = ' + textStatus + 
		            		'errorThrown = ' + errorThrown;
		            	alert ( msg ); 
		            	},
		            success: function(data, textStatus, jqXHR) { 
		            	var msg = 'textStatus = ' + textStatus;

	            		var json = data.payload.rows;
	            		
						var thegrid = $("#jqGrid")[0];
	                 	thegrid.addJSONData(json);
	                 	$('.loading').hide();
		            	}
		        });
	        
	    },
		
	page: 1, 
	 colModel: [

		{ label: 'ID', name: 'id', width: 50, 
			hidden: true,
			key: true, 
			editable: true, 
			sortable: false,
			editrules: { edithidden: true } },
			
		{ label: 'Product Name', name: 'productName', width: 150, 
				sortable: false },
				
		{ label: 'UOM', name: 'uom', width: 75, 
				sortable: false },
		
		{ label: 'Start Date', name: 'startDate', width: 75, 
				sortable: false,
				formatter: dateFormatter, align: 'center' },
				
		{ label: 'Media Plan Id', name: 'fkMediaPlanId', width: 100, 
			sortable: false,
			editable: true, 
			hidden: true,
			editrules: { edithidden: true } },
		
		{ label: 'Qty', name: 'qty', width: 55,
			sortable: false,
			formatter : 'number',
			formatoptions: { thousandsSeparator: ",", decimalPlaces: 0 },
			align: 'right' },
		
		{ label: 'Rate', name: 'rate', width: 50,
				sortable: false,
				formatter : 'currency',
				formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: "$ "},
				align: 'right' },
		
		{ label: 'Investment', name: 'investment', width: 100,
					sortable: false,
					formatter : 'currency',
					formatoptions:{decimalSeparator:".", thousandsSeparator: ",", decimalPlaces: 2, prefix: "$ "},
					align: 'right' }		
						
	],
	viewrecords: true,
	width: 500,
	height: 500,
	rowNum: 0,
	pager: "#jqGridPager",
	subGrid: false
	
});
	
	
});

    </script>
    
</body>
</html>