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
    if(prevKeyCode == 13) {
        document.getElementById("expression").value = document.getElementById("result").value;
        document.getElementById("result").value = "";
    }
    var expression = document.getElementById("expression");
    expression.value += text;
    prevKeyCode = -1;
    document.getElementById("expression").focus();
}

var prevKeyCode;

function enter (event) {
    if(prevKeyCode == 13) {
        document.getElementById("expression").value = document.getElementById("result").value;
        document.getElementById("result").value = "";
    }

    if (event.keyCode == 13) {
        event.preventDefault();
        proceed();
    }
    prevKeyCode = event.keyCode;
}

function enter2 () {
    proceed();
    prevKeyCode = 13;
    document.getElementById("expression").focus();

}