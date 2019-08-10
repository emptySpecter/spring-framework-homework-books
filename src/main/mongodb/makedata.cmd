@echo off
mongo.exe library --eval "printjson(db.genres.find().toArray().concat(db.authors.find().toArray()).concat(db.books.find().toArray()))" > _data
more +4 < _data > data.json
del _data