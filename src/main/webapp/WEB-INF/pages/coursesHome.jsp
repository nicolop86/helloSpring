<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" href="resources/css/bootstrap.css"/>
<script type="text/javascript" src="resources/js/jquery.min.js"/></script>
<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="resources/js/dataTables.bootstrap.min.js"/></script>
<script type="text/javascript" src="resources/js/dataTables.responsive.js"/></script>
<script type="text/javascript" src="resources/js/jquery.dataTables.min.js"/></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Courses HOME</title>
</head>
<body>


<div class="panel panel-primary">
	<div class="panel-heading">
		<div class="row">
			<div class="col-xs-9">
				<i class="fa fa-car fa-5x primary"></i>
				<h1>Vehicle Collection</h1>

			</div>

			<div class="col-xs-3 text-right">

				<div class="huge">${listsize}</div>
				<div>Vehicles</div>

			</div>
		</div>
	</div>

</div>

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<button class="btn btn-primary" data-toggle="modal"
					data-target="#AddNewFormModal">Add New</button>
			</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<table width="100%" class="table table-bordered table-hover"
					id="courseTable">
					<thead>
						<tr>
							<th>id</th>
							<th>Title</th>
							<th>Teacher</th>
							<th>Credits</th>
							<th>Start Date</th>
						</tr>
					</thead>

					<tbody>
					</tbody>

				</table>
				<!-- /.table-responsive -->
				<div class="well" style="margin-top: 1rem;">
					<h4>DataTables Usage Information</h4>
					<p>
						DataTables is a very flexible, advanced tables plugin for jQuery.
						In SB Admin, we are using a specialized version of DataTables
						built for Bootstrap 3. We have also customized the table headings
						to use Font Awesome icons in place of images. For complete
						documentation on DataTables, visit their website at <a
							target="_blank" href="https://datatables.net/">https://datatables.net/</a>.
					</p>
					<a class="btn btn-default btn-lg btn-block" target="_blank"
						href="https://datatables.net/">View DataTables Documentation</a>
				</div>
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>


<div class="modal fade" id="performVehicleBooking" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel2" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel2">
					<h3>Vehicle Booking</h3>
				</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">

				<form method="POST"
					action="${pageContext.request.contextPath}/reservation/performBooking"
					role="form">

					<div class="form-group">
						<label style="color: gray;" for="brand">Vehicle ID</label> <input
							name="resourceid" id="resourceid" readonly="readonly"
							class="form-control" type="text" />
					</div>



					<div class="demo-section k-content">
						<label style="color: gray;" for="datetimepickerBegin">Begin
							Date/Time</label> <input id="datetimepickerBegin" name="begindatetime" style="width: 100%;" />
					</div><br>

					<div class="demo-section k-content">
						<label style="color: gray;" for="datetimepickerEnd">End
							Date/Time</label> <input id="datetimepickerEnd" name="enddatetime" style="width: 100%;" />
					</div><br>
					
					<input type="hidden" id="username" name="username" value="${pageContext.request.userPrincipal.name}" >

					<input type="submit" class="btn btn-success"
						value="Perform Booking" />

				</form>


			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>

			</div>
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		var oTable = $("#courseTable").DataTable({
			"processing" : true,
			"ajax" : {
				"url" : "/rest/loadCourses",
				"dataSrc" : "courseList",
				"type" : "GET"
			}
		});

		// create DateTimePickerBegin from input HTML element
		$("#datetimepickerBegin").kendoDateTimePicker({
			value : new Date()
		});
		// create DateTimePickerEnd from input HTML element
		$("#datetimepickerEnd").kendoDateTimePicker({
			value : new Date()
		});
	});

	$(document).on("click", ".bookButton", function(e) {

		e.preventDefault();

		var _self = $(this);

		var myBookId = _self.data('id');
		$("#resourceid").val(myBookId);

		$(_self.attr('href')).modal('show');
	});

	function deleteVehicle(id) {

		if (confirm("Are you sure you want to delete the selected vehicle?")) {

			$
					.ajax({
						url : "/BookingWeb/vehicle/delete?id=" + id,
						success : function(responseBody) {

							var $rowsNo = $('#vehicleTable tbody tr').filter(
									function() {
										return $.trim($(this).find('td').eq(0)
												.text()) === id.toString()
									}).toggle();

						},
						error : function(xhr) {
							alert("attenzione : Non è stato possibile eliminare il veicolo "
									+ id);
						}
					});

		} else {
			return false;
		}
	}
</script>


</body>
</html>