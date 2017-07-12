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
<script src="js/jquery-ui-1.11.4.custom/jquery-ui.min.js" type="text/javascript"></script>

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
	
		/*
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
	    */
	    loadBeforeSend: function(jqXHR) {
            //jqXHR.setRequestHeader("Authorization", "Bearer " + sessionId);
            jqXHR.setRequestHeader("Content-Type", "application/json; charset=UTF-8");
            jqXHR.setRequestHeader("Accept", "application/json");
            jqXHR.setRequestHeader("VAL", "1");
        },

        url:'services/mediaplan',
        editurl: 'services/mediaplan/addupdateitem',
        
        datatype: 'json',
        mtype: 'GET',
        postData : {'id' : mpId },

        jsonReader : {
            root:"payload.rows",
            page: "payload.page",  // the current page
            total: "payload.total",  // the total # of pages
            records: "payload.records"  // the total # of records
        },
		
	page: 1, 
	 colModel: [

		{ label: 'ID', name: 'id', width: 50, 
			hidden: false,
			key: true, 
			editable: false, 
			sortable: false },
			
		{ label: 'Product Name', name: 'productName', width: 150, 
				sortable: false },
				
		{ label: 'UOM', name: 'uom', width: 75, 
				sortable: false,
				editable: false },
		
		{ label: 'Start Date', name: 'startDate', width: 75, 
				sortable: false,
				formatter: dateFormatter, 
				align: 'center',
				editable: true,
				edittype:"text",
				editrules : {required: true},
				editoptions: {
                    // dataInit is the client-side event that fires upon initializing the toolbar search field for a column
                    // use it to place a third party control to customize the toolbar
                    dataInit: function (element) {
                        $(element).datepicker({
                            id: 'orderDate_datePicker',
                            dateFormat: 'M/d/yy',
                            //minDate: new Date(2010, 0, 1),
                            maxDate: new Date(2020, 0, 1),
                            showOn: 'focus'
                        });
                    }
                }
		},
				
		{ label: 'Media Plan Id', name: 'fkMediaPlanId', width: 100, 
			sortable: false,
			editable: false, 
			hidden: true },
		
		{ label: 'Qty', name: 'qty', width: 55,
			sortable: false,
			formatter : 'number',
			formatoptions: { thousandsSeparator: ",", decimalPlaces: 0 },
			align: 'right',
			editable: true,
			editrules : {required: true, integer: true, minValue: 1}
			},
		
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
	height: 250,
	rowNum: 0,
	pager: "#jqGridPager",
	subGrid: false
	
});
	
	$('#jqGrid').navGrid('#jqGridPager',
            // the buttons to appear on the toolbar of the grid
            { edit: true, 
				add: true, 
				del: true, 
				search: false, 
				refresh: false, 
				view: false, 
				position: "left", 
				cloneToTop: false,
				mtype: 'POST',
				errorTextFormat: function (data) {
                    return 'Error: ' + data.responseText;
                	}
				},
            // options for the Edit Dialog
            {
                editCaption: "Edit Item",
                recreateForm: true,
				checkOnUpdate : true,
				checkOnSubmit : true,
                closeAfterEdit: true,
                errorTextFormat: function (data) {
                    return 'Error: ' + data.responseText;
                },
                ajaxEditOptions: {
		            beforeSend: function(jqXHR) {
		            	jqXHR.setRequestHeader("VAL", "1");
		            }
		        },
				editData: {
	        		mediaPlanId : mpId,
	        		productName : 'VDP + Email'
	        	}
	        	,
		        onclickSubmit: function (params, postdata){
		        	//alert('inside onclickSubmit');
		        	setPricingFields (params, postdata);
		        	
		        	return postdata;
		        }
            },
            // options for the Add Dialog
            {
                closeAfterAdd: true,
                recreateForm: true,
                errorTextFormat: function (data) {
                    return 'Error: ' + data.responseText;
                },
                ajaxEditOptions: {
		            beforeSend: function(jqXHR) {
		            	jqXHR.setRequestHeader("VAL", "1");
		            }
		        },
				editData: {
	        		mediaPlanId : mpId,
	        		productName : 'VDP + Email'
	        	},
		        onclickSubmit: function (params, postdata){
		        	//alert('inside onclickSubmit');

		        	setPricingFields (params, postdata);
		        	
		        	return postdata;
		        }
            },
            // options for the Delete Dailog
            {
                errorTextFormat: function (data) {
                	console.log('data:' + data);
                	//return 'Error: ' + data.responseText;
                    return 'Error: data not processed';
                },
                ajaxEditOptions: {
		            beforeSend: function(jqXHR) {
		            	jqXHR.setRequestHeader("VAL", "1");
		            }
		        },
				editData: {
	        		mediaPlanId : function() { return mpId; }
	        	}
            }
            
	);
	
	
});

    </script>
    
    
    <script type="text/javascript">
    
    var pricingInfo = {};
    
    pricingInfo.entries = [];
    
    var entry = {};
    entry.qty = 99999;
    entry.amount = 1250;
    entry.uom = 'FLAT';
    pricingInfo.entries.push(entry);
    
    entry = {};
    entry.qty = 250000;
    entry.amount = 25;
    entry.uom = 'CPM';
    pricingInfo.entries.push(entry);
    
    entry = {};
    entry.qty = 500000;
    entry.amount = 19;
    entry.uom = 'CPM';
    pricingInfo.entries.push(entry);
    
    entry = {};
    entry.qty = 750000;
    entry.amount = 15;
    entry.uom = 'CPM';
    pricingInfo.entries.push(entry);
    
    entry = {};
    entry.qty = 1000000;
    entry.amount = 14;
    entry.uom = 'CPM';
    pricingInfo.entries.push(entry);
    
    entry = {};
    entry.qty = 3000000;
    entry.amount = 13;
    entry.uom = 'CPM';
    pricingInfo.entries.push(entry);
    
    </script>
    
    <script type="text/javascript">
    
    function setPricingFields (params, postdata){
    	
    	for(var i = 0; i < pricingInfo.entries.length; i++) {
    		if(postdata.qty <= pricingInfo.entries[i].qty) {
    			params.editData.uom = pricingInfo.entries[i].uom;
    			params.editData.rate = pricingInfo.entries[i].amount;
    			params.editData.investment = (pricingInfo.entries[i].amount * postdata.qty) / 1000;
    		}
    	}
    	
    	if(params.editData.investment === undefined) {
    		var index = pricingInfo.entries.length - 1
    		params.editData.uom = pricingInfo.entries[index].uom;
			params.editData.rate = pricingInfo.entries[index].amount;
			params.editData.investment = (pricingInfo.entries[index].amount * postdata.qty) / 1000;
    	}
    	
    	return params;
    }
    </script>
    
</body>
</html>