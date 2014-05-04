Query A: Print the names of artists from Switzerland, i.e., artists whose area is Switzerland.

select artist.name from artist, area  where area.id = areaid and area.name = 'Switzerland';

Query B: Print the names of areas with the highest number male artists, female artists and groups. For each of these 3 areas, print the number of artists of each of the three types in the area.
  (1) Groups
  select name, countArtists  from area r, (
  select t.areaid, count(*) as countArtists
  from artist t
  where t.type = 'Group' and t.areaid is not null
  group by t.areaid
  order by countArtists DESC) where r.id = areaid and rownum <=1;
  
  (2) Male 
  select name, countArtists  from area r,(
  select t.areaid,  count(*) as countArtists
  from artist t
  where t.areaid is not null and t.gender = 'Male'
  group by t.areaid
  order by countArtists DESC) where r.id = areaid and rownum <=1;
  
   (3) Female
  select name, countArtists  from area r,(
  select t.areaid,  count(*) as countArtists
  from artist t
  where t.areaid is not null and t.gender = 'Female'
  group by t.areaid
  order by countArtists DESC) where r.id = areaid and rownum <=1; 
  
 Query C: List the names of 10 groups with the most recorded tracks. 
 
 select * from (
  select name from 
 ( select t.artistid, count(*) as countRecordedTracks
    from artist_song t
    group by t.artistid
    order by countRecordedTracks desc 
  ), artist r where r.id = artistid and r.type = 'Group'
) where rownum <=10;


Query E: 
Select * 
From(Select (count(DISTINCT Genre.name)) AS countGenre 
	From Artist Art,Artist_genre AG, Genre Genre 
	WHERE art.gender = 'Female' AND art.id=ag.artistid AND ag.genreID=genre.id 
	group BY art.name 
	ORDER BY countGenre DESC) 
where rownum<= 1;
