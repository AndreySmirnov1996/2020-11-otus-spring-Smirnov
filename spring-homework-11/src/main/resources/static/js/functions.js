
function getAllBooks() {
        $.get('/api/book').done(books => printAllBooks(books))
}

function printAllBooks(books) {
    if (typeof books !== 'undefined' && books.length > 0) {
        books.forEach(function (book) {
            $('tbody').append(`
                    <tr>
                        <td>${book.id}</td>
                        <td>${book.title}</td>
                        <td>${book.genreName}</td>
                        <td>${book.authors}</td>
                        <td>
                            <a href="/book/${book.id}/edit" class="w3-button w3-yellow">Edit</a>
                        </td>
                        <td>
                            <input id="deleteBook" type="button" value="Delete" class="w3-button w3-black" onclick="deleteBook('${book.id}')" />
                        </td>
                    </tr>
                `)
        });
    } else {
        $('tbody').append(`
                        <tr>
                            <td colspan="2"> No Books Available </td>
                        </tr>
                `)
    }
}


function deleteBook(id) {
    console.log("before delete id = " + id);
    $.ajax({
        type : "DELETE",
        url : "/api/book/" + id,
        success: function () {
            $('tbody').children().remove();
            getAllBooks();
        },
        error: function (e) {
        }
    });
}