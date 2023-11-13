var xhr = ''

var cart = document.getElementById('cartTable');

cart.addEventListener('blur', function (e) {
    var cartItem = e.target.parent,
        itemID   = cartItem[0],
        quantity = cartItem[4];
    console.log(itemID);
    console.log(quantity);
    /*if(e.target.matches('text')){
        var cartItem = e.target.parent,
            itemID   = cartItem[0],
            quantity = cartItem[4];
        console(itemID);
        console(quantity);
    }*/
} )