$(document).scroll(function () {
    var y = $(this).scrollTop();
    if (y > 10) {
        $('.gototop').fadeIn();
    } else {
        $('.gototop').fadeOut();
    }
});

$('.gototop').on('click', function () {
    $('html, body').animate({scrollTop: 0}, 'slow');
});

$(document).ready(function () {
    setInterval(function () {
        myTimer()
    }, 10);

    $('input#input_text, textarea#textarea1').characterCounter();

    $('select').material_select();
});

function myTimer() {
    var date = new Date();
    document.getElementById("currentTime").innerHTML = date.toLocaleTimeString("bg");
}

$("body").on("click", "#log-in-btn", function (e) {
    e.preventDefault();
    var url = $(this).data('url');
    window.location.href = url;
});
