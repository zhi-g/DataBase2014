-- QUERY H 
--print top female for each area that has more than 30 artists
SELECT m1.name,
       m2.name
FROM (SELECT
               b.name,
               a.areaid,
               a.crt,
               rank() over (partition by a.areaid order by a.crt desc) rnk
          FROM artist b,
              (select artid, areaid, crt
              from 
               (select t1.artistid as artid, count(*) as crt
                 from artist_song t1
                 group by t1.artistid
                 order by crt DESC), artist
              where artid = id ) a
         WHERE b.gender = 'Female' and a.artid = b.id) m1, area m2
 WHERE m1.areaid= m2.id and m1.rnk = 1 and areaid in
                      (select distinct areaid
                      from (select artist.areaid as areaid, count(distinct artist.name) as cnt
                            from artist
                            where artist.areaid is not null
                            group by artist.areaid)
                      where cnt > 29);
                      
--print top male for each area that has more than 30 artists
SELECT m1.name,
       m2.name
FROM (SELECT
               b.name,
               a.areaid,
               a.crt,
               rank() over (partition by a.areaid order by a.crt desc) rnk
          FROM artist b,
              (select artid, areaid, crt
              from 
               (select t1.artistid as artid, count(*) as crt
                 from artist_song t1
                 group by t1.artistid
                 order by crt DESC), artist
              where artid = id ) a
         WHERE b.gender = 'Male' and a.artid = b.id) m1, area m2
 WHERE m1.areaid= m2.id and m1.rnk = 1 and areaid in
                      (select distinct areaid
                      from (select artist.areaid as areaid, count(distinct artist.name) as cnt
                            from artist
                            where artist.areaid is not null
                            group by artist.areaid)
                      where cnt > 29);
                      
--print top groups for each area that has more than 30 artists
SELECT m1.name,
       m2.name
FROM (SELECT
               b.name,
               a.areaid,
               a.crt,
               rank() over (partition by a.areaid order by a.crt desc) rnk
          FROM artist b,
              (select artid, areaid, crt
              from 
               (select t1.artistid as artid, count(*) as crt
                 from artist_song t1
                 group by t1.artistid
                 order by crt DESC), artist
              where artid = id ) a
         WHERE b.type = 'Other' and a.artid = b.id) m1, area m2
 WHERE m1.areaid= m2.id and m1.rnk = 1 and areaid in
                      (select distinct areaid
                      from (select artist.areaid as areaid, count(distinct artist.name) as cnt
                            from artist
                            where artist.areaid is not null
                            group by artist.areaid)
                      where cnt > 29);
--
---------------------------------------------------------------------------------------------------------------------

-- QUERY K
--List all genres that have no female artists, all genres that have no male artists and all genres that have no groups. 

-- no female
select g.name 
from genre G , artist A, artist_genre AG
where a.id = ag.artistid and ag.genreid = g.id and a.gender != 'Female';

-- no male
select g.name 
from genre G , artist A, artist_genre AG
where a.id = ag.artistid and ag.genreid = g.id and a.gender != 'Male';

-- no Group
select g.name 
from genre G , artist A, artist_genre AG
where a.id = ag.artistid and ag.genreid = g.id and a.type != 'Group';

--Alternative for Query K: 
-- no female
select distinct g1.name
from genre g1
where g1.id not in
(select distinct g.id
from genre G , artist A, artist_genre AG
where a.id = ag.artistid and ag.genreid = g.id and a.gender = 'Female');

-- no male
select distinct g1.name
from genre g1
where g1.id not in
(select distinct g.id
from genre G , artist A, artist_genre AG
where a.id = ag.artistid and ag.genreid = g.id and a.gender = 'Male');

-- no Group
select distinct g1.name
from genre g1
where g1.id not in
(select distinct g.id
from genre G , artist A, artist_genre AG
where a.id = ag.artistid and ag.genreid = g.id and a.type = 'Group');

---------------------------------------------------------------------------------------------------------------------

-- QUERY O
select distinct releasename from (
select t1.releaseid from 
  (select releaseid,count(*) as counter from album
  group by album.releaseid
  order by counter desc) t1
  where counter = (select max(counter) from
(select releaseid,count(*) as counter from album
  where releaseid is not null
  group by album.releaseid))) fat1, album
  where fat1.releaseid = album.releaseid;

---------------------------------------------------------------------------------------------------------------------

  -- QUERY I (not sur if result is correct)
  select name from (
Select distinct song.name , count(*) as counter
from track, song, artist, artist_song ass, album
where ass.artistid = artist.id and ass.trackid = track.id and artist.name = 'Metallica' and track.mediumid = album.id
and track.recordingid = song.id
group by song.name
order by counter desc) where rownum <=25;
