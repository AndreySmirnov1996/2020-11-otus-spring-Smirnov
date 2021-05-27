
function getAllBooks() {
        $.get('/api/book').done(books => printAllBooks(books))
}

function printAllBooks(books) {
    if (typeof books !== 'undefined' && books.length > 0) {
        books.forEach(function (book) {
            $('tbody').append(`
                    <tr>
                        <td id="${book.id}">${book.id}</td>
                        <td contenteditable="true">${book.title}</td>
                        <td>${book.genre}</td>
                        <td>${book.authors}</td>
                        <td>
                            <button type="button" class="w3-button w3-yellow edit-book-btn">EditAjax</button>
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


function saveBook(book) {
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/book",
        data: JSON.stringify(book),
        success: function () {
            location.reload();
        },
        error: function (error) {
            alert("Error message:" + error.responseText);
        }
    });
}