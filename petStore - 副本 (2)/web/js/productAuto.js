
$(function (){
    $('#keyword').on('keyup',function (){
        var keyword=$(this).val();
        if(keyword!==''&&keyword!==null&&keyword.length!==0){
            $.ajax({
                type:'GET',
                url :'/project_war_exploded/productAuto?keyword='+keyword,
                success:function (data){
                    console.log(data);
                    var productListHTML='';
                    for(var i=0;i<data.length;i++){
                        productListHTML+='<li class=\"productAutoItem\" data-productId="';
                        productListHTML+=data[i].productId;
                        productListHTML+='">';

                        productListHTML+=data[i].catagoryId;
                        productListHTML+=':';
                        productListHTML+=data[i].name;
                        productListHTML+='</li>';
                    }
                    $('#productAutoList').html(productListHTML);
                    $('#productAutoComplete').show();
                },
                error:function (errorMsg) {
                    console.log(errorMsg);
                }
            });

        }else{
            $('#productAutoComplete').hide();
        }
   });

    // $('.productAutoItem').on('click',function (){
    //
    // })
    $(document).on('click','.productAutoItem',function (){   //动态绑定
        var productId=$(this).data('productid');
        $('#productAutoComplete').hide();
        $('#keyword').val('');
        window.location.href='/project_war_exploded/product?productId='+productId;
    });
//注意区分mouseleave和mouseout的区别
    $('#productAutoComplete').on('mouseleave',function (){
        $(this).hide();
        $('#keyword').val('');
    })

});