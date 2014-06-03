--For each area with more than 10 groups, list the 5 male artists that have recorded the highest number of tracks. 

--give areaID For each area with more than 10 groups:
select distinct area.id
from area, (select artist.areaid,count(distinct artist.name) as cnt
from artist
where artist.areaid is not null and artist.type = 'Group' 
group by artist.areaid
order by cnt DESC) t
where t.cnt > 10;

-- to verify upper result
select artist.areaid, count(distinct artist.name) as cnt
from artist
where artist.areaid is not null and artist.type = 'Group' and artist.areaid = 6
group by artist.areaid
order by cnt DESC;

--list the 5 male artists that have recorded the highest number of tracks. 
select *   from (
  select id, areaid from
    (select t.artistid , count(*) as countRecordedTracks from artist_song t
      group by t.artistid
      order by countRecordedTracks desc
    ) t1, artist r 
  where r.id = t1.artistid and r.gender = 'Male' ) 
where rownum <=5;