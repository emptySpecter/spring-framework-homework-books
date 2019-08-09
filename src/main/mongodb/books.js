[{$lookup: {
  from: 'authors',
  localField: 'author.id',
  foreignField: 'id',
  as: 'book_author'
}}, {$lookup: {
  from: 'genres',
  localField: 'genre.id',
  foreignField: 'id',
  as: 'book_genre'
}}, {$addFields: {
  "author._id": {$arrayElemAt: ["$book_author._id",0]},
  "genre._id": {$arrayElemAt: ["$book_genre._id",0]}
}}, {$project: {
  id: 0, 
  "genre.id" : 0,
  "genre._class": 0,
  "author.id" : 0,
  "author._class": 0,
  book_author: 0,
  book_genre: 0
}}, {$project: {
  name: 1,
  points: 1,
  pagecount: 1,
  "genre._id": "$genre._id",
  "genre.name": "$genre.name",
  "author._id": "$author._id",
  "author.name": "$author.name",
  "author.surname": "$author.surname"
}}, {$out: 'books_oid'}]