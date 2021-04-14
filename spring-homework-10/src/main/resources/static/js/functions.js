
function getAllBooks() {
    if(window.booksGlobal.length === 0) {
        $.get('/api/book').done(function (books) {
            books.forEach(function(book) {
                window.booksGlobal.push(book)
            })
            console.log("books size = " + booksGlobal.length)

        })
    }
    printAllBooks()
}

function printAllBooks() {
    console.log("printAllBooks booksSize = " + booksGlobal.length)
    if (typeof booksGlobal !== 'undefined' && booksGlobal.length > 0) {
        booksGlobal.forEach(function (book) {
            $('tbody').append(`
                    <tr>
                        <td>${book.id}</td>
                        <td>${book.title}</td>
                        <td>${book.genreName}</td>
                        <td>${book.authors}</td>
                        <td>
                            <a href="/book/${book.id}/comment" class="w3-button w3-deep-purple">Comments</a>
                        </td>
                        <td>
                            <a href="/book/${book.id}/edit" class="w3-button w3-yellow">Edit</a>
                        </td>
                        <td>
                            <form action="/book/delete" method="post">
                                <input type="hidden" name="id" value="${book.id}" />
                                <input type="submit" value="Delete" class="w3-button w3-black"/>
                            </form>
                        </td>
                        <td>
                            <input id="deleteBook" type="button" value="Delete" class="w3-button w3-black" onclick="deleteBook(${book.id}, books);" />
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
        success: function (result) {
        },
        error: function (e) {
        }
    });

    // remove book from global array

    printAllBooks();
}