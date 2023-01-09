$('a').removeClass('active');
$('a:contains(Statistiques)').addClass('active');
$("#main-content").load("page/statistiques.html");

function show(page) {
	if (page == 'pharmacies') {
		$('a').removeClass('active');
		$('a:contains(Pharmacies)').addClass('active');
		$("#main-content").load("page/pharmacie.html");

		event.preventDefault();
	}
	if (page == "statistiques") {
		$('a').removeClass('active');
		$('a:contains(Statistiques)').addClass('active');
		$("#main-content").load("page/statistiques.html");
		event.preventDefault();
	}
	if (page == "villes") {
		$('a').removeClass('active');
		$('a:contains(Villes)').addClass('active');
		$("#main-content").load("page/ville.html");
		event.preventDefault();
	}
	if (page == "zones") {
		$('a').removeClass('active');
		$('a:contains(Zones)').addClass('active');
		$("#main-content").load("page/zone.html");
		event.preventDefault();
	}
	if (page == "gardes") {
		$('a').removeClass('active');
		$('a:contains(Pharmacie de garde)').addClass('active');
		$("#main-content").load("page/garde.html");
		event.preventDefault();
	}
}
