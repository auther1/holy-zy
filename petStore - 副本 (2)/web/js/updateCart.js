function createXMLHttpRequest(){
    if (window.XMLHttpRequest) //非IE浏览器
    {
        xmlHttpRequest = new XMLHttpRequest();
    }
    else if (window.ActiveObject)//IE6以上版本的IE浏览器
    {
        xmlHttpRequest = new ActiveObject("Msxml2.XMLHTTP");
    }
    else //IE6及以下版本IE浏览器
    {
        xmlHttpRequest = new ActiveObject("Microsoft.XMLHTTP");
    }
}
function updateCart(index) {
    var quantity = document.getElementById("quantity"+index).value;
    sendRequest("updateCartJSServlet?quantity="+ quantity + "&index=" + index);
}
function sendRequest(url,index) {
    createXMLHttpRequest();
    xmlHttpRequest.open("GET", url, true);
    xmlHttpRequest.onreadystatechange = function (){
        processResponse(index);
    };
    xmlHttpRequest.send(null);
}

function processResponse(index) {
    if (xmlHttpRequest.readyState == 4) {
        if (xmlHttpRequest.status == 200) {
            var resp = xmlHttpRequest.responseText;
            var array = resp.split(",");
            var quantity = document.getElementById("quantity"+index);
            var total = document.getElementById("total");
            var subtotal = document.getElementById("subtotal");

            quantity.innerText = array[0];
            total.innerText = array[1];
            subtotal.innerText = array[2];
        }
    }
}