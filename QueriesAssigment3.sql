-- QUERY H not correct I think
select distinct t2.name from
(select distinct area.id
from area, (select artist.areaid, count(distinct artist.name) as cnt
from artist
where artist.areaid is not null
group by artist.areaid
order by cnt DESC)
where cnt > 30) t1,
((select r.name , r.areaid from
( select t.artistid , count(*) as countRecordedTracks
  from artist_song t
  group by t.artistid
  order by countRecordedTracks desc
), artist r where r.id = artistid and r.gender = 'Male' and r.areaid is not null) UNION
(select r.name , r.areaid from
( select t.artistid , count(*) as countRecordedTracks
  from artist_song t
  group by t.artistid
  order by countRecordedTracks desc
), artist r where r.id = artistid and r.gender = 'Female' and r.areaid is not null) UNION
(select r.name , r.areaid from
( select t.artistid , count(*) as countRecordedTracks
  from artist_song t
  group by t.artistid
  order by countRecordedTracks desc
), artist r where r.id = artistid and r.type = 'Group' and r.areaid is not null)) t2
where t1.id = t2.areaid;

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

---------------------------------------------------------------------------------------------------------------------

-- QUERY O
select distinct releasename from (
select t1.releaseid from 
  (select releaseid,count(*) as counter from album
  group by album.releaseid
  order by counter desc) t1
  where counter = (select counter from
(select releaseid,count(*) as counter from album
  group by album.releaseid
  order by counter desc) where rownum = 1)) fat1, album
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