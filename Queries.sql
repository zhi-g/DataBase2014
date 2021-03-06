--Query A: Print the names of artists from Switzerland, i.e., artists whose area is Switzerland.

select artist.name from artist, area  where area.id = areaid and area.name = 'Switzerland';

--Query B: Print the names of areas with the highest number male artists, female artists and groups. For each of these 3 areas, print the number of artists of each of the three types in the area.
 -- (1) Groups
  select name, countArtists  from area r, (
  select t.areaid, count(*) as countArtists
  from artist t
  where t.type = 'Group' and t.areaid is not null
  group by t.areaid
  order by countArtists DESC) where r.id = areaid and rownum <=1;
  
 -- (2) Male 
  select name, countArtists  from area r,(
  select t.areaid,  count(*) as countArtists
  from artist t
  where t.areaid is not null and t.gender = 'Male'
  group by t.areaid
  order by countArtists DESC) where r.id = areaid and rownum <=1;
  
 --  (3) Female
  select name, countArtists  from area r,(
  select t.areaid,  count(*) as countArtists
  from artist t
  where t.areaid is not null and t.gender = 'Female'
  group by t.areaid
  order by countArtists DESC) where r.id = areaid and rownum <=1; 
  
 --Query C: List the names of 10 groups with the most recorded tracks. 
 
 select * from (
  select name from 
 ( select t.artistid, count(*) as countRecordedTracks
    from artist_song t
    group by t.artistid
    order by countRecordedTracks desc 
  ), artist r where r.id = artistid and r.type = 'Group'
) where rownum <=10;

--Query D:
select * from (
select a.name from
  (select art.artistid, count(distinct a1.releasename) as countRecordings
  from album a1, track t, artist_song art
  where art.trackid = t.id and t.recordingid = a1.id
  group by art.artistid
  order by countRecordings desc) res, artist a
  where a.id =res.artistid and a.type = 'Group'
) where rownum<=10;


--Query E: 
Select * 
From(Select (count(DISTINCT Genre.name)) AS countGenre 
	From Artist Art,Artist_genre AG, Genre Genre 
	WHERE art.gender = 'Female' AND art.id=ag.artistid AND ag.genreID=genre.id 
	group BY art.name 
	ORDER BY countGenre DESC) 
where rownum<= 1;


--Query F: List all cities which have more female than male artists.
select r1.name from (Select Area.name, area.id, count(DISTINCT Artist.name) AS countFemale 
						From Artist, Area 
						WHERE Artist.gender='Female' AND Artist.areaID=Area.ID 
						group BY area.name, area.id) r1,
					(Select Area.name, area.id, count(DISTINCT Artist.name) AS countMale 
						From Artist, Area 
						WHERE Artist.gender='Male' 
						AND Artist.areaID=Area.ID 
						group BY area.name, area.id) r2 
where countFemale > countMale and r1.id = r2.id;

--Query G:
select album.* from (
select mediumid, count(t.id)
from track t
group by t.mediumid
having  count(t.id) >=  (select max(count(t1.id))
                            from track t1
                            group by t1.mediumid)), album where mediumid = album.id;
