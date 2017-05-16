/* jQuery INFORMATION
 - Project : shop.vydeal
 - Version : v1.0.0
 - Last Update : 19 November 2016
 - Copyright 2016-2017
 */

$(document).ready(function () {

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
    function isNumberKey(evt) {
        var charCode = (evt.which) ? evt.which : evt.keyCode;
        if (charCode > 31 && (charCode < 48 || charCode > 57))
            return false;
        return true;
    }

    // Clips
    $('.chips').material_chip();

    // Select Option Add Specification
    $("#selectedValue").click(function () {
        $("#resultView").empty();
        var e = $("#selectOption option:selected").val();
        return $("#resultView").append(e), $(".select-spec").last().clone().appendTo($(".more-specifications-view")).find("input").attr("name", function (e, t) {
            return t.replace(/\[(\d+)\]/, function (e, t) {
                return "[" + (+t + 1) + "]"
            })
        }), !1
    })


    // Tooltip
    $('.tooltipped').tooltip({delay: 50});

    $('.product-box').click(function () {
        $('#mainSection').load('product-view.html');
    });

    // Tabs
    $('div.tabs').tabs();


});

var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
            sURLVariables = sPageURL.split('&'),
            sParameterName,
            i;

    for (i = 0; i < sURLVariables.length; i++) {

        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sURLVariables[i].replace(sParam + "=", "");
        }
    }
};

function autoHide(id) {
    setTimeout(function () {
        $("#" + id).fadeOut('fast');
    }, 5000);
}

function logout() {
    $.post("../logout", {}, function (suc) {
        window.location.href = "../index.html";
    });
}

function getUpProfile() {
    $("#mainSection").html("<div class=\"col-6\"> <div class=\"personal-details form\"> <h1>Personal Details</h1><div id=\"updateErr\"></div> <form onsubmit=\"return updateProfile('ad')\"> <label> Name *</label><input type=\"text\" name=\"name\" id=\"mn\" required><label> Address 1 *</label><input id=\"madd1\" type=\"text\" name=\"address1\" required><label> Address 2 </label><input id=\"madd2\" type=\"text\" name=\"address2\"><label> Place *</label><input id=\"mpl\" type=\"text\" name=\"place\" required><label> Pin *</label><input id=\"mpin\" type=\"text\" name=\"pin\" required><label> City *</label><input id=\"mcity\" type=\"text\" name=\"city\" required><button type=\"submit\"  class=\"btn btn-bg waves-effect\"> Update </button></form></div></div>");
}

function getUpBankDetails() {
    $("#mainSection").html("<div class=\"col-6\"> <div class=\"personal-details form\"> <h1>Bank Details</h1><div id=\"updateErr\"></div> <form onsubmit=\"return updateBankDetails('ad')\"> <label> Pan Card Number </label><input type=\"text\" name=\"pan\" id=\"mpan\"><label> Account Number *</label><input id=\"macc\" type=\"text\" name=\"accNo\" required><label> Bank *</label><input id=\"bank\" type=\"text\" name=\"bank\" required><label> Branch *</label><input id=\"br\" type=\"text\" name=\"branch\" required><label> IFSC *</label><input id=\"ifsc\" type=\"text\" name=\"ifsc\" required><button type=\"submit\"  class=\"btn btn-bg waves-effect\"> Update </button></form></div></div>");
}

function updateProfile(page) {
    var name = $("#mn").val();
    var add1 = $("#madd1").val();
    var add2 = $("#madd2").val();
    var place = $("#mpl").val();
    var pin = $("#mpin").val();
    var city = $("#mcity").val();
    $.post("../updateProfile", {'name': name, 'add1': add1, 'add2': add2, 'place': place, 'pin': pin, 'city': city, 'pg': page}, function (suc, status, xhr) {
        var ct = xhr.getResponseHeader("content-type") || "";
        if (ct.indexOf('json') > -1) {
            // handle json here
            var err = "Some error occured. Please try again!";
            var succ = "Profile successfully updated";
            if (suc.status === "ok" && page === "ad") {
                window.location.href = "addDetails.html?d=3";
            } else if (suc.status === "ok" && page === "pu") {
                $("#updateErr").html("<div id=\"msgStatus\" class=\"msg-status success\">" + succ + "</div>");
                autoHide("msgStatus");
            } else if (suc.status === "failed") {
                $("#updateErr").html("<div id=\"msgStatus\" class=\"msg-status success\">" + err + "</div>");
                autoHide("msgStatus");
            } else {
                err = "";
                if (!(typeof suc.name === 'undefined' || suc.name === null)) {
                    err += "invalid name\n";
                }
                if (!(typeof suc.add1 === 'undefined' || suc.add1 === null)) {
                    err += "invalid address1";
                }
                if (!(typeof suc.add2 === 'undefined' || suc.add2 === null)) {
                    err += "invalid  address2";
                }
                if (!(typeof suc.place === 'undefined' || suc.place === null)) {
                    err += "invalid place";
                }
                if (!(typeof suc.pin === 'undefined' || suc.pin === null)) {
                    err += "invalid pin";
                }
                if (!(typeof suc.city === 'undefined' || suc.city === null)) {
                    err += "invalid city";
                }
                if (err === "") {
                    window.location.href = "../index.html?le=1";
                } else {
                    $("#updateErr").html("<div id=\"msgStatus\" class=\"msg-status error\">" + err + "</div>");
                    autoHide("msgStatus");
                }
            }
        }
    });
    return false;
}

function updateBankDetails(page) {
    var pan = $("#mpan").val();
    var acno = $("#macc").val();
    var bn = $("#bank").val();
    var br = $("#br").val();
    var ifsc = $("#ifsc").val();
    $.post("../updateBankDetails", {'pan': pan, 'acno': acno, 'bn': bn, 'br': br, 'ifsc': ifsc, 'pg': page}, function (suc, status, xhr) {
        var ct = xhr.getResponseHeader("content-type") || "";
        if (ct.indexOf('json') > -1) {
            // handle json here
            var err = "Some error occured. Please try again!";
            var succ = "Profile successfully updated";
            if (suc.status === "ok" && page === "ad") {
                window.location.href = "master.html";
            } else if (suc.status === "ok" && page === "pu") {
                $("#updateErr").html("<div id=\"msgStatus\" class=\"msg-status success\">" + succ + "</div>");
                autoHide("msgStatus");
            } else if (suc.status === "failed") {
                $("#updateErr").html("<div id=\"msgStatus\" class=\"msg-status success\">" + err + "</div>");
                autoHide("msgStatus");
            } else {
                err = "";
                if (!(typeof suc.bank === 'undefined' || suc.bank === null)) {
                    err += "invalid bank\n";
                }
                if (!(typeof suc.branch === 'undefined' || suc.branch === null)) {
                    err += "invalid branch";
                }
                if (!(typeof suc.pan === 'undefined' || suc.pan === null)) {
                    err += "invalid  pan";
                }
                if (!(typeof suc.accno === 'undefined' || suc.accno === null)) {
                    err += "invalid accno";
                }
                if (!(typeof suc.ifsc === 'undefined' || suc.ifsc === null)) {
                    err += "invalid pin";
                }
                if (!(typeof suc.mobile === 'undefined' || suc.mobile === null)) {
                    err += "invalid ifsc";
                }
                if (err === "") {
                    window.location.href = "../index.html?le=1";
                } else {
                    $("#updateErr").html("<div id=\"msgStatus\" class=\"msg-status error\">" + err + "</div>");
                    autoHide("msgStatus");
                }
            }
        }
    });
    return false;
}

function getProfile() {
    $.post("../getMyProfile", {}, function (suc, status, xhr) {
        var ct = xhr.getResponseHeader("content-type") || "";
        if (ct.indexOf('html') > -1) {
            //do something
            $("#profile").html(suc);
        }
        if (ct.indexOf('json') > -1) {
            window.location.href = "../index.html?le=1";
        }
    });
    return false;
}

function getBankDetails() {
    $.post("../getMemberBankDetails", {}, function (suc, status, xhr) {
        var ct = xhr.getResponseHeader("content-type") || "";
        if (ct.indexOf('html') > -1) {
            //do something
            $("#bankDetails").html(suc);
        }
        if (ct.indexOf('json') > -1) {
            window.location.href = "../index.html?le=1";
        }
    });
    return false;
}

function changePass() {
    var cp = $("#cp").val();
    var np = $("#np").val();
    $.post("../changePassword", {'cp': cp, 'np': np}, function (suc, status, xhr) {
        var ct = xhr.getResponseHeader("content-type") || "";
        if (ct.indexOf('html') > -1) {
            //do something
            $("#changePass").html(suc);
            autoHide("msgStatus");
        }
        if (ct.indexOf('json') > -1) {
            // handle json here
            var err = "";
            if (!(typeof suc.password === 'undefined' || suc.password === null)) {
                err += "invalid password\n";
            }
            if (err === "") {
                window.location.href = "../index.html?le=1";
            } else {
                $("#changePass").html("<div id=\"msgStatus\" class=\"msg-status error\">" + err + "</div>");
                autoHide("msgStatus");
            }
        }
    });
    return false;
}

function getReport() {
    $.post("../getMemberReport", {}, function (suc, status, xhr) {
        var ct = xhr.getResponseHeader("content-type") || "";
        if (ct.indexOf('html') > -1) {
            //do something
            $("#report").html(suc);
        }
        if (ct.indexOf('json') > -1) {
            window.location.href = "../index.html?le=1";
        }
    });
    return false;
}

function checkForParams() {
    var le = getUrlParameter("le");
    if (le === '1') {
        $("#indexErr").html("<div id=\"msgStatus\" class=\"msg-status error\">Login error!</div>");
        autoHide("msgStatus");
    }
}

function loginFN() {
    var user = $("#userName").val();
    var pass = $("#userPassword").val();
    $.post("login", {"uname": user, "pass": pass}, function (suc) {
        if (suc.status === "ok") {
            if (suc.us === "2") {
                window.location.href = suc.ut + "/master.html";
            } else {
                window.location.href = suc.ut + "/addDetails.html?d=" + suc.us;
            }
        } else {
            if (!(typeof suc.uname === 'undefined' || suc.uname === null)) {
                if (suc.uname.startsWith("not")) {
                    $("#indexErr").html("<div id=\"msgStatus\" class=\"msg-status error\"> Username not exists</div>");
                } else {
                    $("#indexErr").html("<div id=\"msgStatus\" class=\"msg-status error\"> Username invalid</div>");
                }
            } else if (!(typeof suc.password === 'undefined' || suc.password === null)) {
                $("#indexErr").html("<div id=\"msgStatus\" class=\"msg-status error\"> Password invalid</div>");
            }
            autoHide("msgStatus");
        }

    });
    return false;
}

function register() {
    var name = $("#name").val();
    var email = $("#email").val();
    var mob = $("#mob").val();
    alert("register");
    $.post("register", {'name': name, 'email': email, 'mob': mob}, function (suc) {
        $("#regErr").html(suc);
        autoHide("msgStatus");
    });
    return false;
}
