$("body").on("change", "#users-select", function (e) {
    var $userSelect = $(this);
    $('.preloader').show();
    $.ajax({
        url: '/e-library/admin/get-roles',
        type: 'POST',
        data: {
            email: $userSelect.val()
        },
        success: function (data) {
            $('#roles-table-div').empty();
            $('#roles-table-div').append(data);
            $('.preloader').hide();
        },
        error: function () {
            $('.preloader').hide();
            $('#error-modal').modal('open');
        }
    });
});

$("body").on("click", "#delete-role-btn", function (e) {
    var btn = $(this);
    var roleID = btn.data('role');
    var userID = btn.data('user');

    $('.preloader').show();
    $.ajax({
        url: '/e-library/admin/delete-role-from-user',
        type: 'POST',
        data: {
            role: roleID,
            user: userID
        },
        success: function (data) {
            $('#roles-table-div').empty();
            $('#roles-table-div').append(data);
            $('.preloader').hide();
        },
        error: function () {
            $('.preloader').hide();
            $('#error-modal').modal('open');
        }
    });
});

$("body").on("click", "#add-role-btn", function (e) {
    var btn = $(this);
    var roleID = btn.data('role');
    var userID = btn.data('user');

    $('.preloader').show();
    $.ajax({
        url: '/e-library/admin/add-role-to-user',
        type: 'POST',
        data: {
            role: roleID,
            user: userID
        },
        success: function (data) {
            $('#roles-table-div').empty();
            $('#roles-table-div').append(data);
            $('.preloader').hide();
        },
        error: function () {
            $('.preloader').hide();
            $('#error-modal').modal('open');
        }
    });
});
