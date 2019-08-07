[{
    $lookup: {
        from: 'authors',
        localField: 'author.id',
        foreignField: 'id',
        as: 'book_author'
    }
}, {
    $lookup: {
        from: 'genres',
        localField: 'genre.id',
        foreignField: 'id',
        as: 'book_genre'
    }
}, {
    $addFields: {
        "author._id": {
            $arrayElemAt: ["$book_author._id", 0]
        },
        "genre._id": {
            $arrayElemAt: ["$book_genre._id", 0]
        }
    }
}, {
    $project: {
        id: 0,
        "genre.id": 0,
        "genre._class": 0,
        "author.id": 0,
        "author._class": 0,
        book_author: 0,
        book_genre: 0
    }
}, {
    $out: 'books_oid'
}]