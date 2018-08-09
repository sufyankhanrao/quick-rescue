/*This function is invoked whenever Document is loaded and ready
	---Start of Ready function---
*/
$(document).ready(function () {
	//Login button Event Handler
	$(".btn-signin").click(function () {
		if (!isEmptyField('input[name="username"]') & !isEmptyField('input[name="password"]')) {
			console.log("not Empty");
			var data = {
				"username": $('input[name="username"]').val(),
				"password": $('input[name="password"]').val()
			};
			//Making API Call for Login
			$.ajax({
				url: 'http://localhost:8080/qrapi/api/v1/accounts/login',
				type: 'POST',
				data: JSON.stringify(data),
				contentType: "application/json; charset=utf-8",
				dataType: "json",
				success: function (response) {
					if (response.output == "failure") {
						updateStatus("failure");
						window.localStorage.loggedIn = false;
					}
					else {
						window.localStorage.accountID = response.output;
						window.localStorage.loggedIn = true;
						console.log("id: " + window.localStorage.accountID);
						updateStatus("success");
					}
				},
				error: function (result) {
					$('.status').show()
						.html('<div class="alert alert-danger">Server is not <strong>Responding!</strong></div>')
						.fadeIn(2000);
					resetForm('.login-form');
				}
			});
		} else {
			console.log("Empty");
			$('.status').show()
				.html('<div class="alert alert-warning">No input Field can be<strong> Empty!</strong></div>')
				.fadeIn(2000);
		}
	});
	//Add Contact button Event Handler
	$('.btn-add').click(function () {
		if (!isEmptyField('input[name="firstname"]') & !isEmptyField('input[name="firstname"]') & !isEmptyField('input[name="lastname"]') & !isEmptyField('input[name="emailID"]') & !isEmptyField('input[name="phoneNo"]') & !isEmptyField('input[name="street"]') & !isEmptyField('input[name="city"]') & !isEmptyField('input[name="state"]') & !isEmptyField('input[name="country"]')) {
			var data = {
				"firstName": $('input[name="firstname"]').val(),
				"lastName": $('input[name="lastname"]').val(),
				"emailID": $('input[name="emailID"]').val(),
				"gender": $('.add-gender').val(),
				"phoneNo": $('input[name="phoneNo"]').val(),
				"status": $('.add-status').val(),
				"address": {
					"street": $('input[name="street"]').val(),
					"city": $('input[name="city"]').val(),
					"state": $('input[name="state"]').val(),
					"country": $('input[name="country"]').val()
				}
			};
			//Making API Call for Adding new contacts
			$.ajax({
				url: 'http://localhost:8080/qrapi/api/v1/account/' + window.localStorage.getItem('accountID') + '/contacts/add',
				type: 'POST',
				data: JSON.stringify(data),
				contentType: "application/json; charset=utf-8",
				dataType: "json",
				success: function (response) {
					if (response.output == "success") {
						if (response.alert == "true") {
							$('#headerID').html('Alert Sent!');
							$('#alert-body').html('<p><strong>Contact Added! </strong>An Alert has been sent to recently added Contact!</p>');
							$('#alertModal').modal('show');
							setTimeout(function () {
								$('#alertModal').modal('hide');
							}, 2000);
							resetForm('.add-form');
						} else {
							$('#headerID').html('Alert Not Sent!');
							$('#alert-body').html('<p><strong>Contact Added! </strong>Recently added Contact is not eligible for alerts!</p>');
							$('#alertModal').modal('show');
							setTimeout(function () {
								$('#alertModal').modal('hide');
							}, 2000);
						}
						$(".add-notice")
							.show()
							.html('<div class="alert alert-success">Contact Addition<strong> succeeded!</strong></div>')
							.toggle(2000);
						resetForm('.add-form')
					}
					else {
						$(".add-notice")
							.show()
							.html('<div class="alert alert-warning">Contact Addition<strong> Failed!</strong></div>')
							.toggle(2000);
					}
				},
				error: function (result) {
					console.log(result);
				}
			});
		} else {
			$(".add-notice")
				.show()
				.html('<div class="alert alert-warning">No input Field can be<strong> Empty!</strong></div>')
				.fadeIn(2000);
		}
	});
	//Update button Event Handler
	$('.btn-update').click(function () {
		//Making API Call for updating already existing Contact
		if (!isEmptyField('input[name="firstname"]') & !isEmptyField('input[name="firstname"]') & !isEmptyField('input[name="lastname"]') & !isEmptyField('input[name="emailID"]') & !isEmptyField('input[name="phoneNo"]') & !isEmptyField('input[name="street"]') & !isEmptyField('input[name="city"]') & !isEmptyField('input[name="state"]') & !isEmptyField('input[name="country"]')) {
			$.ajax({
				url: 'http://localhost:8080/qrapi/api/v1/contacts/update/' + window.localStorage.getItem('contactID') + '/' + $('input[name="firstname"]').val() + '/' + $('input[name="lastname"]').val() + '/' + $('input[name="emailID"]').val() + '/' + $('.update-gender').val() + '/' + $('input[name="phoneNo"]').val() + '/' + $('.update-status').val() + '/' + $('input[name="street"]').val() + '/' + $('input[name="city"]').val() + '/' + $('input[name="state"]').val() + '/' + $('input[name="country"]').val(),
				type: 'PUT',
				success: function (response) {
					if (response.output == "success") {
						$(".update-notice")
							.show()
							.html('<div class="alert alert-warning">Contact Update<strong> succeeded!</strong></div>')
							.toggle(5000);
						resetForm('.update-form')
					}
					else {
						$(".update-notice")
							.show()
							.html('<div class="alert alert-success">Contact Update<strong> Failed!</strong></div>')
							.toogle(5000);
						resetForm('.update-form');
					}
				}
			});
		} else {
			$(".update-notice")
				.show()
				.html('<div class="alert alert-warning">No input Field can be<strong> Empty!</strong></div>')
				.fadeIn(2000);
		}
	});

	//If User has logged in then Table data is loaded
	if (window.localStorage.getItem("loggedIn")) {
		generateTable();
	}

	//Search field handler
	$('.search-table').keyup(function () {
		value = $('input[name="search"]').val();
		searchTable(value);
	});

});
//---END OF READY FUNCTION---



//---Utilities functions---
//To check whether an input field in empty or not
function isEmptyField(element) {
	if ($(element).val() == "") {
		console.log($(element).val());
		return true;
	}
	return false;
}

//updates Status class for Login result and redirect url
function updateStatus(data) {
	if (data == "failure") {
		$(".status")
			.show()
			.html('<div class="alert alert-warning">Signing In <strong>Failed!</strong></div>')
			.fadeIn(3000);
		resetForm('.login-form');
	}
	if (data == "success") {
		$(".status")
			.show()
			.html('<div class="alert alert-success">Signing In <strong>Succeeded!</strong></div>')
			.fadeIn(3000);
		window.open("contacts.html", "_self");
	}
}

//Reset values of forms
function resetForm(form) {
	$(form).trigger("reset");
}

//returns ID of current ID selected for deletion or Editing
function getRowID() {
	var id = $(event.target).data("id");
	return id;
}

//generate URL and Loads update page
function loadupdatePage() {
	window.localStorage.contactID = getRowID();
	window.location.href = "http://" + window.location.host + "/editcontacts.html?id=" + window.localStorage.getItem('contactID');
}

//Generating URL with ID concatinated
$.extend({
	getUrlVars: function () {
		var vars = [], hash;
		var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
		for (var i = 0; i < hashes.length; i++) {
			hash = hashes[i].split('=');
			vars.push(hash[0]);
			vars[hash[0]] = hash[1];
		}
		return vars;
	},
	getUrlVar: function (name) {
		return $.getUrlVars()[name];
	}
});
//---END OF UTILITIES FUNCTIONS

//Table Operation functions
//Search table for a given keyword and show records accordingly
function searchTable(value) {
	$('.contact-table .table-row').each(function () {
		var found = false;
		$(this).each(function () {
			if ($(this).text().toLowerCase().indexOf(value.toLowerCase()) >= 0) {
				found = true;
			}
		});
		if (found == true) {
			$(this).show();
		} else {
			$(this).hide();
		}
	});
}

//generateing Table
function generateTable() {
	$(".table_body").empty();
	$.get('http://localhost:8080/qrapi/api/v1/contacts/all?accountID=' + window.localStorage.getItem("accountID"), function (response, status) {
		if (response.length == 0) {
			$('.map-link').addClass('disabled');
			$('.records').html("<h6 style='text-align: center;'>No Contact Found</h6>");
		} else {
			$('.table_body').html(generateHTMLforTable(response));
			console.log($('table tr td:nth-child(Name)'));
		}
	});
}

//Form and return HTML data with values
function generateHTMLforTable(data) {
	tableHTML = "";
	for (var i = 0; i < data.length; i++) {
		tableHTML += '<tr class="table-row">';
		tableHTML += '<td class="align-baseline">' + (i + 1) + '</td>';
		tableHTML += '<td class="align-baseline">' + data[i]['firstName'] + ' ' + data[i]['lastName'] + '</td>';
		tableHTML += '<td class="align-baseline">' + data[i]['emailID'] + '</td>';
		tableHTML += '<td class="align-baseline">' + data[i]['gender'] + '</td>';
		tableHTML += '<td class="align-baseline">' + data[i]['phoneNo'] + '</td>';
		tableHTML += '<td class="align-baseline">' + data[i]['status'] + '</td>';
		tableHTML += '<td class="align-baseline">' + data[i]['address']['street'] + ',' + data[i]['address']['city'] + ',' + data[i]['address']['state'] + ',' + data[i]['address']['country'] + '</td>';
		tableHTML += '<td class="align-baseline">' + '<a href="#" class="btn btn-success btn-sm" id="edit" data-id=' + data[i]['id'] + ' onClick="loadupdatePage()">Edit</a>' + ' ' + '<a href="#" id="delete" class="btn btn-danger btn-sm" data-id=' + data[i]['id'] + ' onClick="deleteContact()">Delete</a>' + '</td>';
		tableHTML += '</tr>';
	}
	return tableHTML;
}
//---END OF TABLE OPERATIONS

//--Update Page Operations
//Loads data when Update page is loaded
function loadData() {
	var id = ($).getUrlVars('id');
	$.get('http://localhost:8080/qrapi/api/v1/contacts/search?contactID=' + id['id'], function (response, status) {
		$('#fname').val(response["firstName"]);
		$('#lname').val(response["lastName"]);
		$('#emailID').val(response["emailID"]);
		$(".gender-select").val(response["gender"]);
		$('#phoneNo').val(response["phoneNo"]);
		$(".status-select").val(response["status"]);
		$('#street').val(response["address"]["street"]);
		$('#city').val(response["address"]["city"]);
		$('#state').val(response["address"]["state"]);
		$('#country').val(response["address"]["country"]);
	});
}

//Delete Operation
//Delete contact by calling Delete API
function deleteContact() {
	var contactID = getRowID();
	$.ajax({
		type: "DELETE",
		url: "http://localhost:8080/qrapi/api/v1/contacts/delete/" + contactID,
		success: function (data) {
			$(".notice")
				.show()
				.html('<div class="alert alert-warning">Contact deleted<strong> Successfully!</strong></div>')
				.slideDown(1000, function () {
					generateTable();
				}).slideUp(1000, function () {
				});
		}
	});
}

//---MAP API---
//Initializes Map and place markers using Geocoding and javascript MAP API provided by GOOGLE
function initMap() {
	var addresses = [];
	$.get('http://localhost:8080/qrapi/api/v1/contacts/addresses/all?accountID=' + window.localStorage.getItem("accountID"), function (response, status) {
		if (response.length == 0) {
		} else {
			for (var x = 0; x < response.length; x++) {
				addresses.push('"' + response[x]['street'] + ' ' + response[x]['city'] + ' ' + response[x]['state'] + ' ' + response[x]['country'] + '"');
			}

			var options = {
				zoom: 4,
				center: { lat: 33.6967, lng: 73.0501 }
			}
			var map = new google.maps.Map(document.getElementById('map'), options);

			for (var x = 0; x < addresses.length; x++) {
				console.log(addresses[x]);
				$.get('https://maps.googleapis.com/maps/api/geocode/json?address=' + addresses[x], function (response, status) {
					console.log(response);
					var pos = response['results'][0]['geometry']['location'];
					var lat_lng = new google.maps.LatLng(pos.lat, pos.lng);
					new google.maps.Marker({
						position: lat_lng,
						map: map
					});
				});
			}
		}
	});
}
