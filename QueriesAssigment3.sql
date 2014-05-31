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