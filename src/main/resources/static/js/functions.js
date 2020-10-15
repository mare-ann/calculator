function proceed () {
    var xmlHttp = new XMLHttpRequest();
    var cur = window.location;
    var expression = document.getElementById("expression").value;
    var theUrl = window.location.origin + "/calculate?q=" + encodeURIComponent(expression);
    xmlHttp.open( "GET", theUrl, false ); // false for synchronous request
    xmlHttp.send( null );
    document.getElementById("result").value = xmlHttp.responseText;
}

function clean () {
   document.getElementById("expression").value ="";
}

function append (text) {
    var expression = document.getElementById("expression");
    expression.value += text;
}