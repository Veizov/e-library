$(document).ready(function() {
    $('select').material_select();
});

$("body").on("change", "#image-file", function (e) {
    var $coverImageErrorMsg = $('#image-file-error-msg');
    var $uploadFile = $(this);
    var $coverImageLabel = $('#cover-image-label');

    $.ajax({
        url: '/e-library/admin/validate-uploaded-file',
        type: 'POST',
        data: {
            fileName: $uploadFile.val()
        },
        success: function (data) {
            if (data.length > 0) {
                $uploadFile.val('');
                $coverImageErrorMsg.empty();
                for (var i = 0; i < data.length; i++) {
                    var msg = "<div class='message-error'>" + data[i] + "</div>";
                    $coverImageErrorMsg.append(msg);
                }
            } else {
                $coverImageErrorMsg.empty();

                $coverImageLabel.addClass('remove-pb');
                $coverImageLabel.empty();
                $coverImageLabel.append('<img id="cover-image" src=""  class="cover-image" />');
                previewFile($uploadFile.get(0), $('#cover-image'));
            }
        },
        error: function () {
            $uploadFile.val('');
        }
    });
});


$("body").on("change", "#book-file", function (e) {
    var $fileErrorMsg = $('#book-file-error-msg');
    var $uploadFile = $(this);
    var $bookFileLabel = $('#book-file-label');

    $.ajax({
        url: '/e-library/admin/validate-uploaded-book-file',
        type: 'POST',
        data: {
            fileName: $uploadFile.val()
        },
        success: function (data) {
            if (data.length > 0) {
                $uploadFile.val('');
                $fileErrorMsg.empty();
                for (var i = 0; i < data.length; i++) {
                    var msg = "<div class='message-error'>" + data[i] + "</div>";
                    $fileErrorMsg.append(msg);
                }
            } else {
                $fileErrorMsg.empty();

                $bookFileLabel.addClass('remove-pb');
                $bookFileLabel.empty();

                var fullFileName = $uploadFile.val();
                var originalFileName = fullFileName.substring(fullFileName.lastIndexOf("\\")+1,fullFileName.length);

                $bookFileLabel.append('<img src="/e-library/resources/images/if_pdf_65920.png" />');
                $bookFileLabel.append('<p>' + originalFileName + '</p>');
            }
        },
        error: function () {
            $uploadFile.val('');
        }
    });
});

function previewFile(file, img) {
    if (file.files && file.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            img.attr('src', e.target.result);
        };
        reader.readAsDataURL(file.files[0]);
    }
}