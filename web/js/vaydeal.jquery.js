/* jQuery INFORMATION
   - Project : shop.vydeal
   - Version : v1.0.0
   - Last Update : 19 November 2016
   - Copyright 2016-2017
*/

$(document).ready(function() {

    // Modal
    $('.modal').modal();

    $('.dropdown-notification').dropdown({
        inDuration: 300,
        outDuration: 225,
        constrainWidth: false, 
        hover: false, 
        gutter: 0,
        belowOrigin: false, 
        alignment: 'left',
        }
    );

    // Select Option
    $('select').material_select();

    // input number
    function isNumberKey(evt){
        var charCode = (evt.which) ? evt.which : evt.keyCode;
        if (charCode > 31 && (charCode < 48 || charCode > 57))
            return false;
        return true;
    }

    // Clips
    $('.chips').material_chip();

    // Select Option Add Specification
    $("#selectedValue").click(function() {
        $("#resultView").empty();
        var e = $("#selectOption option:selected").val();
        return $("#resultView").append(e), $(".select-spec").last().clone().appendTo($(".more-specifications-view")).find("input").attr("name", function(e, t) {
            return t.replace(/\[(\d+)\]/, function(e, t) {
                return "[" + (+t + 1) + "]"
            })
        }), !1
    })


    // Tooltip
    $('.tooltipped').tooltip({delay: 50});

    $('.product-box').click(function() {
        $('#mainSection').load('product-view.html');
    });
    
    // Tabs
    $('div.tabs').tabs();
    
    
});