$(document).ready(function() {
	var oTable = $('#vehicleTable').DataTable({
		"processing" : true,
		"ajax" : {
			"url" : "/BookingWeb/vehicle/list",
			"dataSrc" : "vehicleList",
			"type" : "GET"
		}
	});
});