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