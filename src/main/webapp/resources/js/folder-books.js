$(document).ready(function () {
    var inProgress = $("#in-progress").val();
    if (inProgress == 'true') {
        $('#books-folder-div').addClass('display-none');
        $('#progress-div').removeClass('display-none');
        updateProgress(1);
    }
});


$("body").on("click", "#add-books-folder-btn", function (e) {
    var folder = $("#folder-path").val();
    if (folder.trim().length > 0) {
        $('#books-folder-div').addClass('display-none');
        $('#progress-div').removeClass('display-none');
        updateProgress();
        $.ajax({
            url: '/e-library/admin/create-books-folder',
            type: "POST",
            datatype: 'html',
            data: {
                folder: folder
            },
            success: function (data) {
                $("#books-folder-list").empty();
                $("#books-folder-list").append(data);
            },
            error: function (xhr, ajaxOptions, thrownError) {
                var jsonResponse = JSON.parse(xhr.responseText);
                $('#folder-error-msg').html(jsonResponse.message)
                $('#books-folder-div').removeClass('display-none');
                $('#progress-div').addClass('display-none');
                $('#error-modal').modal('open');
            }
        });
    }

});

function updateProgress(initial) {
    var interval = setInterval(function () {
        $.ajax({
            url: '/e-library/admin/get-books-upload-progress',
            type: "POST",
            datatype: 'html',
            data: {},
            success: function (data) {
                if (data.length < 1) {
                    $('#books-progress-bar').css('width', '0%');
                    clearInterval(interval);
                    if (initial === 1) {
                        $('#books-folder-div').removeClass('display-none');
                        $('#progress-div').addClass('display-none');
                    }
                } else {
                    $('#books-progress-bar').css('width', data + '%');
                    $('#progress-percent').html(data + '%');
                }
            },
            error: function (xhr, ajaxOptions, thrownError) {
                $('#error-modal').modal('open');
                clearInterval(interval);
            }
        });
    }, 100);
}