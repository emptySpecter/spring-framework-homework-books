@echo off
mongo.exe library --eval "printjson(db.genres_data.find().toArray().concat(db.authors_data.find().toArray()).concat(db.books_data.find().toArray()))" > _data
more +4 < _data > data.json
del _data