$(document).ready(function(){
	
	
// # ===============================
// # = Nombre des pharmacies
// # ===============================
	$.ajax({
		url : 'pharmacies/count',
		data : '',
		type : 'GET',
		success : function(data) {
			$('#pharmacie').html(data);
		},
		error : function(jqXHR, textStatus,
				errorThrown) {
			console.log(textStatus);
		}
	});
	$.ajax({
		url:'http://localhost:9071/villes/all',
		type:'GET',
		success : function(data) {
			var option = '';
			data.forEach(e=>{
				option += '<option value ='+e.id+'>'+e.nom+'</option>';
			});

			$('#ville').append(option);

		},
		error : function(jqXHR, textStatus,
						 errorThrown) {
			console.log(textStatus);
		}

	});
	$( "#ville" ).change(function() {
		const ctx = document.getElementById('myChart2');
		// Check input( $( this ).val() ) for validity here
		var ville = $( "#ville" ).val();
		console.log(ville)
		$.ajax({
			url : 'http://localhost:9071/zones/NbrPharmacieZone/ville='+ville,
			contentType : "application/json",
			dataType : "json",
			data : '',
			type : 'GET',
			async : false,
			success : function(data) {
				var labels = new Array();
				var dt = new Array();


				(data).forEach(key => {

					labels.push(key[0] ); // returns the keys in an object
					console.log(key[1])
					dt.push(key[1] );  // returns the appropriate value
				});
				try {
					ch.destroy();
				}
				catch(err) {

				}




				ch = new Chart(ctx, {
					type: 'bar',
					data: {
						labels: labels,
						datasets: [{
							label: 'Nombre de pharmacies par ville',
							data: dt,
							backgroundColor:
								'rgba(255, 99, 132, 0.2)',

							borderColor:
								'rgb(255, 99, 132)',
							borderWidth: 1,

						},]
					},
					options: {
						scales: {
							y: {
								beginAtZero: true
							}
						}
					}
				});
			},
			error : function(jqXHR, textStatus,
							 errorThrown) {
				console.log(textStatus);
			}

		});
	});

	
// # ===============================
// # = Nombre des zones par ville
// # ===============================
	$.ajax({
		url : 'http://localhost:9071/villes/NbrPharmacie',
		contentType : "application/json",
		dataType : "json",
		data : '',
		type : 'GET',
		async : false,
		success : function(data) {
			var labels = new Array();
			var dt = new Array();


			 (data).forEach(key => {

				   labels.push(key[0] ); // returns the keys in an object
				console.log(key[1])
				   dt.push(key[1] );  // returns the appropriate value
			});


			const ctx = document.getElementById('myChart');

			new Chart(ctx, {
				type: 'bar',
				data: {
					labels: labels,
					datasets: [{
						label: 'Nombre de pharmacies par ville',
						data: dt,
						backgroundColor:'rgba(54, 162, 235, 0.2)',
						borderColor: 'rgb(75, 192, 192)',
						borderWidth: 1,

					},]
				},
				options: {
					scales: {
						y: {
							beginAtZero: true
						}
					}
				}
			});
		},
		error : function(jqXHR, textStatus,
				errorThrown) {
			console.log(textStatus);
		}

	});

 
// # ===============================
// # = Nombre des pharmacies par zone
// # ===============================

});
