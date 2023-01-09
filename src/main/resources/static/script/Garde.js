$(document)
		.ready(
				function() {

					table = $('#tgarde')
							.DataTable({
										ajax : {
											url : "http://localhost:9071/gardes/all",
											dataSrc : ''
										},
										columns : [
												{
													data : "idGarde"
												},

												
												{
													data : "type"
												},
												

												{
													"render" : function() {
														return '<button type="button" class="btn btn-outline-danger supprimer">Supprimer</button>';
													}
												},
												{
													"render" : function() {
														return '<button type="button" class="btn btn-outline-secondary modifier">Modifier</button>';
													}
												} ]

									});
										$.ajax({
						url:'http://localhost:9071/pharmacies/all',
						type:'GET',
						success : function(data) {
							var option = '';
							data.forEach(e=>{
								option += '<option value ='+e.id+'>'+e.nom+'</option>';
							});
							
						$('#pharmacie').append(option);
						},
						error : function(jqXHR, textStatus,
								errorThrown) {
							console.log(textStatus);
						}
						
					});

					$('#btn').click(
							function() {
								var datedebut = $("#datedebut");
								
								var type = $("#nom");
								
								
							    var pharmacie = $("#pharmacie");
								if ($('#btn').text() == 'Ajouter') {
									var p = {
										type : type.val(),
									};

									$.ajax({
										url : 'http://localhost:9071/gardes/add',
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
											"./page/Garde.html");
								}
							});

					$('#table-content')
							.on(
									'click',
									'.supprimer',
									function() {

										var id = $(this).closest('tr').find(
												'td').eq(0).text();
										var oldLing = $(this).closest('tr')
												.clone();
										var newLigne = '<tr style="position: relative;" class="bg-light" ><th scope="row">'
												+ id
												+ '</th><td colspan="4" style="height: 100%;">';
										newLigne += '<h4 class="d-inline-flex">Voulez vous vraiment supprimer cette pharmacie de garde ? </h4>';
										newLigne += '<button type="button" class="btn btn-outline-primary btn-sm confirmer" style="margin-left: 25px;">Oui</button>';
										newLigne += '<button type="button" class="btn btn-outline-danger btn-sm annuler" style="margin-left: 25px;">Non</button></td></tr>';

										$(this).closest('tr').replaceWith(
												newLigne);
										$('.annuler').click(
												function() {
													$(this).closest('tr')
															.replaceWith(
																	oldLing);
												});
										$('.confirmer')
												.click(
														function(e) {
															e.preventDefault();
															$
																	.ajax({
																		url : 'http://localhost:9071/gardes/delete/'
																				+ id,
																		data : {},
																		type : 'DELETE',
																		async : false,
																		success : function(
																				data,
																				textStatus,
																				jqXHR) {
																			if (data
																					.includes("error") == true) {
																				$(
																						"#error")
																						.modal();
																			} else {
																				table.ajax
																						.reload();
																			}
																		},
																		error : function(
																				jqXHR,
																				textStatus,
																				errorThrown) {
																			$(
																					"#error")
																					.modal();
																		}
																	});

														});

									});

					$('#table-content').on(
							'click',
							'.modifier',
							function() {
								var btn = $('#btn');
								var id = $(this).closest('tr').find('td').eq(0)
										.text();
								;
								var datedebut = $(this).closest('tr').find('td').eq(
										1).text();
										
								var type = $(this).closest('tr').find('td')
										.eq(2).text();
								var pharmacie= $(this).closest('tr').find('td').eq(
										3).text();
								
								
								btn.text('Modifier');
								$("#datdebut").val(datedebut);
							
								$("#type").val(atype);
								$("#id").val(id);
								
								var op = $('#pharmacie option').filter(function () { return $(this).html() == pharmacie; }).val();
								$("#pharmacie").val(op);
								$("#id").val(id);
								
								
								btn.click(function(e) {
									e.preventDefault();
									var p = {
										id : $("#id").val(),
										datedebut : $("#datedebut").val(),
									
										type : $("#type").val(),
										
										pharmacie : {
											id : $("#pharmacie").val()
											
										}
									};
									if ($('#btn').text() == 'Modifier') {
										$.ajax({
											url : 'http://localhost:9071/gardes/save',
											contentType : "application/json",
											dataType : "json",
											data : JSON.stringify(p),
											type : 'POST',
											async : false,
											success : function(data,
													textStatus, jqXHR) {
												table.ajax.reload();
												$("#datedebut").val('');
											
												$("#type").val('');
												btn.text('Ajouter');
											},
											error : function(jqXHR, textStatus,
													errorThrown) {
												console.log(textStatus);
											}
										});
										$("#main-content").load(
												"./page/Garde.html");
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
