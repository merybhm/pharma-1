$(document)
		.ready(
				function() {
					table3 = $('#tpharmacieRff')
						.DataTable({
							ajax : {
								url : "http://localhost:9071/pharmacies/allRefus",
								dataSrc : ''
							},
							columns : [
								{
									data : "id"
								},
								{
									data : "nom"
								},
								{
									data : "adresse"
								},
								{
									data : "lat"
								},
								{
									data : "log"
								},
								{
									data : "zone.nom"
								},
								{
									defaultContent: " <span class=\"badge bg-danger\">Reffuser</span>"
								}


								 ]

						});
					table2 = $('#tpharmacieAtt')
						.DataTable({
							ajax : {
								url : "http://localhost:9071/pharmacies/allEnAttente",
								dataSrc : ''
							},
							columns : [
								{
									data : "id"
								},
								{
									data : "nom"
								},
								{
									data : "adresse"
								},
								{
									data : "lat"
								},
								{
									data : "log"
								},
								{
									data : "zone.nom"
								},
								{
									defaultContent: " <span class=\"badge bg-warning text-dark\">En attente</span>"
								},
								{
									"render" : function() {
										return '<button type="button" class="btn btn-outline-primary acc" >Accecpter</button>';
									}
								},
								{
									"render" : function() {
										return '<button type="button" class="btn btn-outline-danger refu" >Refuser</button>';
									}
								} ]

						});

					table = $('#tpharmacie')
							.DataTable({
										ajax : {
											url : "http://localhost:9071/pharmacies/allAccepte",
											dataSrc : ''
										},
										columns : [
												{
													data : "id"
												},
												{
													data : "nom"
												},
												{
													data : "adresse"
												},
												{
													data : "lat"
												},
												{
													data : "log"
												},
												{
													data : "zone.nom"
												},
											{
												defaultContent: " <span class=\"badge bg-success\">Accepte</span>"
											},
												{
													"render" : function() {
														return '<button type="button" class="btn btn-outline-primary supprimer" data-bs-toggle="modal" data-bs-target="#location">Localisation</button>';
													}
												},
												{
													"render" : function() {
														return '<button type="button" class="btn btn-outline-secondary modifier" data-bs-toggle="modal" data-bs-target="#historique">Historique</button>';
													}
												} ]

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
					$.ajax({
						url:'http://localhost:9071/api/v1/all',
						type:'GET',
						success : function(data) {
							var option = '';
							data.forEach(e=>{
								option += '<option value ='+e.user_id+'>'+e.first_name+' '+e.last_name+'</option>';
							});

							$('#user').append(option);
						},
						error : function(jqXHR, textStatus,
										 errorThrown) {
							console.log(textStatus);
						}

					});
										$.ajax({
						url:'http://localhost:9071/zones/all',
						type:'GET',
						success : function(data) {
							var option = '';
							data.forEach(e=>{
								option += '<option value ='+e.id+'>'+e.nom+'</option>';
							});
							
						$('#zone').append(option);
						},
						error : function(jqXHR, textStatus,
								errorThrown) {
							console.log(textStatus);
						}
						
					});

					$('#btn').click(
							function() {
								var nom = $("#nom");
								var adresse = $("#adresse");
								var latitude= $("#latitude");
								var longitude = $("#longitude");

							    var zone = $("#zone");
								var user = $("#user");
								if ($('#btn').text() == 'Ajouter') {
									var p = {
										nom : nom.val(),
										adresse : adresse.val(),
										lat : latitude.val(),
										lon : longitude.val(),
										zone : {
											id :zone.val()
										},
										user : {
											id :user.val()
										}
									};

									$.ajax({
										url : 'http://localhost:9071/pharmacies/add/'+user.val(),
										contentType : "application/json",
										dataType : "json",
										data : JSON.stringify(p),
										type : 'POST',
										async : false,
										success : function(data, textStatus,
												jqXHR) {
											table.ajax.reload();
										},
										error : function(jqXHR, textStatus,
												errorThrown) {
											console.log(textStatus);
										}
									});
									$("#main-content").load(
											"./page/pharmacie.html");
								}
							});

					$('#table-contentAtt')
						.on(
							'click',
							'.acc',
							function() {
								var myModal = new bootstrap.Modal(document.getElementById('acc'), {
									keyboard: false
								})
								var id = $(this).closest('tr').find('td').eq(0)
									.text();
								;


								myModal.show()
								$('#saveAcc').click(
									function() {

										$.ajax({
											url : 'http://localhost:9071/pharmacies/acceptePharmacie/id='+id,
											contentType : "application/json",
											dataType : "json",

											type : 'POST',
											async : false,
											success : function(data, textStatus,
															   jqXHR) {
												table3.ajax.reload();
												table2.ajax.reload();
												table.ajax.reload();
												myModal.hide()
											},
											error : function(jqXHR, textStatus,
															 errorThrown) {
												console.log(textStatus);
											}
										});
									})
								$('.hide').click(
									function() {
										myModal.hide()

									})
							});
					$('#table-contentAtt')
						.on(
							'click',
							'.refu',
							function() {
								var myModal = new bootstrap.Modal(document.getElementById('refu'), {
									keyboard: false
								})
								var id = $(this).closest('tr').find('td').eq(0)
									.text();
								myModal.show()
								$('#saveRefu').click(
									function() {
										$.ajax({
											url : 'http://localhost:9071/pharmacies/refusPharmacie/id='+id,
											contentType : "application/json",
											dataType : "json",
											type : 'POST',
											async : false,
											success : function(data, textStatus,
															   jqXHR) {
												table3.ajax.reload();
												table2.ajax.reload();
												table.ajax.reload();
												myModal.hide()
											},
											error : function(jqXHR, textStatus,
															 errorThrown) {
												console.log(textStatus);
											}
										});
									})
								$('.hide').click(
									function() {
										myModal.hide()

									})
							});


					$('#table-content')
							.on(
									'click',
									'.supprimer',
									function() {

										var lat = $(this).closest('tr').find('td').eq(3)
											.text();
										;
										var lon = $(this).closest('tr').find('td').eq(4)
											.text();
										;
										var nom = $(this).closest('tr').find('td').eq(1)
											.text();
										;
										console.log(nom);

										var map = L.map('map', {
											center: [lat, lon],
											zoom: 13
										});

										var osm = L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
											attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
										})
										osm.addTo(map);

										var mar = L.marker([lat,lon]).addTo(map)



										mar.bindPopup(nom).openPopup();



									});


					$('#table-content').on(
							'click',
							'.modifier',
							function() {

								var id = $(this).closest('tr').find('td').eq(0)
										.text();
								;
										$.ajax({
											url : 'http://localhost:9071/pharmaciesDeGarde/Historique/id='+id,
											contentType : "application/json",
											dataType : "json",
											type : 'GET',
											async : false,
											success : function(data,
													textStatus, jqXHR) {
												var item = '';
												data.forEach(e=>{
													item += '<li> <strong> date debut : </strong> '+e.pharmacieDeGardeRelation.dateDebut.slice( 0, 10 ) +'  <strong> type de garde : </strong> '+e.garde.type+' <strong> date fin :</strong> '+e.dateFin.slice( 0, 10 )+'</li>';
												});

												$('#historiquelist').append(item);
												var map = L.map('map', {
													center: [51.505, -0.09],
													zoom: 13
												});

												var osm = L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
													attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
												})
												osm.addTo(map);

												L.marker([e.lat,e.lon]).addTo(map)
													.bindPopup(e.nom)
													.openPopup();
											},
											error : function(jqXHR, textStatus,
													errorThrown) {
												console.log(textStatus);
											}
										});



							});

					// function remplir(data) {
					// var contenu = $('#table-content');
					// var ligne = "";
					// for (i = 0; i < data.length; i++) {
					// ligne += '<tr><th scope="row">' + data[i].id + '</th>';
					// ligne += '<td>' + data[i].nom + '</td>';
					// ligne += '<td>' + data[i].adresse + '</td>';
					// ligne += '<td>' + data[i].latitude + '</td>';
					// ligne += '<td>' + data[i].longitude + '</td>';
					// ligne += '<td><button type="button" class="btn
					// btn-outline-danger
					// supprimer">Supprimer</button></td>';
					// ligne += '<td><button type="button" class="btn
					// btn-outline-secondary
					// modifier">Modifier</button></td></tr>';
					// }
					// contenu.html(ligne);
					// }

					// $.ajax({
					// url: 'pharmacies/all',
					// data: {op: ''},
					// type: 'GET',
					// async: false,
					// success: function (data, textStatus, jqXHR) {
					// console.log(data);
					// remplir(data);
					// },
					// error: function (jqXHR, textStatus, errorThrown) {
					// console.log(textStatus);
					// }
					// });
				});
