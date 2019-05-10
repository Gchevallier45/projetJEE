var map;
var marker;
var latitude;
var longitude;
var days = new Array( "Mon", "Tues", "Wed", "Thu", "Fri", "Sat", "Sun");

function initmap() {
    // set up the map
    map = new L.Map('map');

    // create the tile layer with correct attribution
    var osmUrl='http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png';
    var osmAttrib='Données cartographiques © <a href="http://openstreetmap.org">OpenStreetMap</a>';
    var osm = new L.TileLayer(osmUrl, {minZoom: 5, maxZoom: 19, attribution: osmAttrib});

    // start the map in Tours
	latitude = 47.3833;
	longitude = 0.6833;
	addressName = "Tours";
    map.setView(new L.LatLng(latitude, longitude),12);
    map.addLayer(osm);

	// init the geocoder API with the search bar
	L.Control.geocoder({
        defaultMarkGeocode: false
    })
    .on('markgeocode', function(e) {
        showMarker(e.geocode.center);
		map.setView(e.geocode.center,12);
    })
    .addTo(map);
	
	// when user clicks on the map, manageClick function is called
	map.on('click',manageClick);
        
    // show marker from last page
    var latlng = {
            lat:document.getElementsByName("latitude")[0].value,
            lng:document.getElementsByName("longitude")[0].value
    };

    if((latlng.lat != "") && (latlng.lng != "")){
            showMarker(latlng);
    }
}

function manageClick(e){
	// search the address corresponding to the latitude and longitude selected by user
	L.Control.Geocoder.nominatim().reverse(e.latlng, map.options.crs.scale(19), function(results) {
		var r = results[0];
		if (r) {
		  showMarker(e.latlng);
		}
	});
}

function showMarker(latlng){
	// if a marker is already on the map, it is removed
	if(marker != null){
		map.removeLayer(marker);
	}
	
	// update location variables
	latitude = latlng.lat;
	longitude = latlng.lng;	
	
	// show the marker on the map
	marker = new L.marker(latlng).addTo(map).bindPopup("<b>Coordonnées :</b><br/>(" + latitude + "," + longitude +")").openPopup();
	
	document.getElementsByName("latitude")[0].value =latitude;
	document.getElementsByName("longitude")[0].value = longitude;
}

    
    
    function closed(day) {
        var fromDay = document.getElementsByName('from'+days[day])[0];
        var toDay = document.getElementsByName('to'+days[day])[0];
        var closedDay = document.getElementsByName('closed'+days[day])[0];
        var is24hrsDay = document.getElementsByName('24hrs'+days[day])[0];
        
        if(closedDay.checked  == true) // is close
        {
            is24hrsDay.checked=false;
            fromDay.value="--:--";
            fromDay.disabled=true;
            toDay.value="--:--";
            toDay.disabled=true;
        }
        else{
            fromDay.disabled=false;
            toDay.disabled=false;
        }
        
    }
    function is24hrsDay(day) {
        var fromDay = document.getElementsByName('from'+days[day])[0];
        var toDay = document.getElementsByName('to'+days[day])[0];
        var closedDay = document.getElementsByName('closed'+days[day])[0];
        var is24hrsDay = document.getElementsByName('24hrs'+days[day])[0];
        
        if(is24hrsDay.checked  == true) // is open 24/24H
        {
            closedDay.checked=false;
            fromDay.value="--:--";
            fromDay.disabled=true;
            toDay.value="--:--";
            toDay.disabled=true;
        }
        else{
            fromDay.disabled=false;
            toDay.disabled=false;
        }
    }
    
    function hoursIsAvailabled(day) {
        var fromDay = document.getElementsByName('from'+days[day])[0];
        var toDay = document.getElementsByName('to'+days[day])[0];
        var closedDay = document.getElementsByName('closed'+days[day])[0];
        var is24hrsDay = document.getElementsByName('24hrs'+days[day])[0];
        
        if(is24hrsDay.checked  == true) // is open 24/24H
        {
            closedDay.checked=false;
            fromDay.value="--:--";
            fromDay.disabled=true;
            toDay.value="--:--";
            toDay.disabled=true;
        }
        if(closedDay.checked  == true) // is close
        {
            is24hrsDay.checked=false;
            fromDay.value="--:--";
            fromDay.disabled=true;
            toDay.value="--:--";
            toDay.disabled=true;
        }
    }

   function init() {
       initmap();
       for(var indiceDay = 0; indiceDay < 7; indiceDay++) { 
           hoursIsAvailabled(indiceDay);
       }
   }
   
   init();