
var nav = document.getElementById('nav');

window.onload = function (event) {
	document.getElementById("navBtn").className = "navOpen";
	document.getElementById("listMenu").className = "listOpen";
	document.getElementById("shadowbox").className = "visible";
	nav.classList.toggle('active');
};

function menuToggle(){	
		
	if (document.getElementById("navBtn").className == "navOpen") {
		document.getElementById("navBtn").className = "";
		document.getElementById("listMenu").className = "";
		document.getElementById("shadowbox").className = "";
		nav.classList.toggle('active');
	} else {
		document.getElementById("navBtn").className = "navOpen";
		document.getElementById("listMenu").className = "listOpen";
		document.getElementById("shadowbox").className = "visible";
		nav.classList.toggle('active');
	}
	
}