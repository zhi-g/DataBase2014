DataBase2014
============
Oracle tables 

Album(id: integer, format: string, releasename: string, releaseid: integer)
Area(id: integer, name: string, type: string)
Atrist(id: integer, name: string, type: string, gender: string, areaid: integer)
Genre(id: integer, name: string)
Song(id: integer, name: string, length: integer)
Artist_Genre(artistid: integer, genreid: integer)
Artist_Song(artistid: integer, songid: integer) -- ici songid = trackid in Track
Track(recordingid: integer,  mediumid: integer, position: integer) -- recordingid = id in Song, meduimid=id in Album
