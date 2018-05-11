
$("body").on("click", "#clear-btn", function (e) {
    searchBookAjax();
    $('.books-filter').val('');
});

$("body").on("click", "#search-btn", function (e) {
    var title = $("#title-filter").val();
    var author = $("#author-filter").val();
    var yearFrom = $("#year-from-filter").val();
    var yearTo = $("#year-to-filter").val();
    var category = $("#category-filter").val();

    searchBookAjax(null, null, title, author, yearFrom, yearTo, category)
});

function searchBookAjax(sortColumn, sortOrder, title, author, yearFrom, yearTo, category) {
    $('.preloader').show();
    $.ajax({
        url: '/e-library/book-list-filter',
        type: "POST",
        datatype: 'html',
        data: {
            sortColumn: sortColumn,
            sortOrder: sortOrder,
            title: title,
            author: author,
            yearFrom: yearFrom,
            yearTo: yearTo,
            category: category
        },
        success: function (data) {
            $("#search-book-div").empty();
            $("#search-book-div").append(data);
            $('.preloader').hide();
        },
        error: function (xhr, ajaxOptions, thrownError) {
            $('.preloader').hide();
            $('#error-modal').modal('open');
        }
    });
}

$("body").on("click", "#search-book-div .table-sorter", function (e) {
    var sortColumn = $(this).data("sort");
    var sortOrder = $('#book-sort-order').val();
    if (sortOrder === 'ASC')
        sortOrder = 'DESC';
    else sortOrder = 'ASC';

    var title = $("#session-filter-title").val();
    var author = $("#session-filter-author").val();
    var yearFrom = $("#session-filter-yearFrom").val();
    var yearTo = $("#session-filter-yearTo").val();
    var category = $("#session-filter-category").val();

    searchBookAjax(sortColumn, sortOrder, title, author, yearFrom, yearTo, category)
});