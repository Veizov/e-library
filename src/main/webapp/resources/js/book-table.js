$("body").on("click", "#clear-btn", function (e) {
    searchBookAjax();
    $('.books-filter').val('');
});

$("body").on("click", "#search-btn", function (e) {
    var title = $("#title-filter").val();
    var author = $("#author-filter").val();
    var dateFrom = $("#date-from-filter").val();
    var dateTo = $("#date-to-filter").val();
    var category = $("#category-filter").val();
    var language = $("#language-filter").val();
    var isbn = $("#isbn-filter").val();
    var yearFrom = $("#year-from-filter").val();
    var yearTo = $("#year-to-filter").val();
    var numberOfPagesFrom = $("#page-number-from-filter").val();
    var numberOfPagesTo = $("#page-number-to-filter").val();

    searchBookAjax(null, null, title, author, dateFrom, dateTo, category, language, isbn, yearFrom, yearTo, numberOfPagesFrom, numberOfPagesTo)
});

$("body").on("click", "#delete-book-btn", function (e) {
    var bookID = $(this).data('id');
    $('#delete-book-confirm-btn').data('id',bookID);
    $('#delete-book-modal').modal('open');
});

$("body").on("click", "#delete-book-confirm-btn", function (e) {
    var bookID = $(this).data('id');
    var page = $("#request-page").val();
    var pageSize = $("#request-page-size").val();

    $('.preloader').show();
    $.ajax({
        url: '/e-library/delete-book',
        type: "POST",
        datatype: 'html',
        data: {
            bookID: bookID,
            page: page,
            pageSize: pageSize
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
});

function searchBookAjax(sortColumn, sortOrder, title, author, dateFrom, dateTo, category, language, isbn, yearFrom, yearTo, numberOfPagesFrom, numberOfPagesTo) {
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
            dateFrom: dateFrom,
            dateTo: dateTo,
            category: category,
            language: language,
            isbn: isbn,
            yearFrom: yearFrom,
            yearTo: yearTo,
            numberOfPagesFrom: numberOfPagesFrom,
            numberOfPagesTo: numberOfPagesTo
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
    var language = $("#session-filter-language").val();
    var dateFrom = $("#session-filter-dateFrom").val();
    var dateTo = $("#session-filter-dateTo").val();
    var isbn = $("#session-filter-isbn").val();
    var numberOfPagesFrom = $("#session-filter-page-number-from").val();
    var numberOfPagesTo = $("#session-filter-page-number-to").val();

    searchBookAjax(sortColumn, sortOrder, title, author, dateFrom, dateTo, category, language, isbn, yearFrom, yearTo, numberOfPagesFrom, numberOfPagesTo)
});


$('.datepicker').pickadate({
    max: true,
    selectMonths: true, // Creates a dropdown to control month
    selectYears: 15, // Creates a dropdown of 15 years to control year,
    today: 'Днес',
    clear: 'Изчисти',
    close: 'ОК',
    format: 'dd.mm.yyyy',
    closeOnClear: true,
    closeOnSelect: true, // Close upon selecting a date,
    labelMonthNext: 'Следващ месец',
    labelMonthPrev: 'Предишен месец',
    labelMonthSelect: 'Изберете месец',
    labelYearSelect: 'Изберете година',
    monthsFull: [ 'Януари', 'Февруари', 'Март', 'Април', 'Май', 'Юни', 'Юли', 'Август', 'Септември', 'Октомври', 'Ноември', 'Декември' ],
    monthsShort: [ 'Яну', 'Фев', 'Мар', 'Апр', 'Май', 'Юни', 'Юли', 'Авг', 'Сеп', 'Окт', 'Ное', 'Дек' ],
    weekdaysFull: [ 'Неделя', 'Понеделник', 'Вторник', 'Сряда', 'Четвъртък', 'Петък', 'Събота' ],
    weekdaysShort: [ 'Нед', 'Пон', 'Вто', 'Сря', 'Чет', 'Пет', 'Съб' ],
    weekdaysLetter: [ 'Н', 'П', 'В', 'С', 'Ч', 'П', 'С' ],
    onClose: function() {
        $(document.activeElement).blur();
    }
});