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

  -- QUERY I (it is correct)
  select name from (
Select distinct song.name , count(*) as counter
from track, song, artist, artist_song ass, album
where ass.artistid = artist.id and ass.trackid = track.id and artist.name = 'Metallica' and track.mediumid = album.id
and track.recordingid = song.id
group by song.name
order by counter desc) where rownum <=25;
---------------------------------------------------------------------------------------------------------------------
--Query J
select t2.name, t1.name
from (
  select 
    gid,
    name,
    rank() over(partition by gid order by nrt desc) rnk
  from
    (select ag.genreid as gid, ag.artistid as aid, tr.crt as nrt, art.name as name
    from
      (SELECT genreid
        FROM
          (SELECT genreid,
            COUNT(DISTINCT artistid) AS cnt
          FROM artist_genre
          GROUP BY genreid
          ORDER BY cnt DESC
          )
        WHERE rownum<11) g, artist_genre ag, artist art,
        (select artistid, count(*) as crt
        from artist_song
        group by artistid) tr
        where art.gender = 'Female' and tr.artistid = art.id and g.genreid = ag.genreid and ag.artistid = art.id)) t1, genre t2
where t1.rnk = 1 and t2.id = gid;
---------------------------------------------------------------------------------------------------------------------

-- QUERY K
--List all genres that have no female artists, all genres that have no male artists and all genres that have no groups. 

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
--Query L
SELECT t4.name,
  t3.name, t3.countRecordedTracks
FROM
  (SELECT t2.areaid,
    t2.id, t2.name, t2.countRecordedTracks,
    row_number() over(partition BY t2.areaid order by t2.countRecordedTracks DESC) rnk
  FROM
    (SELECT id,
      r.name,
      areaid,
      countRecordedTracks
    FROM
      (SELECT t.artistid ,
        COUNT(*) AS countRecordedTracks
      FROM artist_song t
      GROUP BY t.artistid
      ) t1,
      artist r
    WHERE r.id   = t1.artistid
    AND r.gender = 'Male'
    ) t2
  ) t3, area t4
WHERE t3.rnk  <=5 and t4.id = t3.areaid 
AND t3.areaid IN (SELECT DISTINCT area.id
  FROM area,
    (SELECT artist.areaid,
      COUNT(DISTINCT artist.name) AS cnt
    FROM artist
    WHERE artist.areaid IS NOT NULL
    AND artist.type      = 'Group'
    GROUP BY artist.areaid
    ORDER BY cnt DESC
    ) t
  WHERE t.cnt >= 10
  ); 
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
--Query M
select distinct(1) from
(select g.id, count(distinct meds.trackid) as cnt
from
  artist g, 
  (select s1.artistid, s1.trackid
          from artist_song s1,
                artist_song s2, 
                track t1,
                track t2
          where 
                s1.artistid != s2.artistid
                and 
                t1.id = s1.trackid 
                and 
                t2.id = s2.trackid
                and 
                t1.mediumid = t2.mediumid) meds
where g.type = 'Group' and g.id = meds.artistid 
group by g.id
order by cnt DESC)
where rownum<11;
---------------------------------------------------------------------------------------------------------------------

-- QUERY P
  select genre.name from genre, (
select * from (
select fat2.id, count(*) as cnt
from (
select  genre.name,genre.id
from genre, artist_genre ag,artist, (select artist.name,artist.id, table1.counter from
artist, (
select A1.id,count(*) as counter
from artist A1, artist_genre AG, genre G
where A1.id = AG.artistid and Ag.genreid = G.id
group by A1.id
order by counter desc) table1
where artist.id = table1.id) fat1
where artist.id = fat1.id and artist.id = ag.artistid and ag.genreid = genre.id) fat2
group by fat2.id
order by cnt desc) where rownum <=1) fat3 where genre.id = fat3.id;
---------------------------------------------------------------------------------------------------------------------

--Query Q
select name, cnt
  from(
  select 
    s.name, count(distinct s.id) as cnt
  from song s
  group by s.name
  order by cnt DESC)
where rownum<=5;
---------------------------------------------------------------------------------------------------------------------
--Query S

select id, avs FROM
(SELECT t2.id,
  COUNT(t1.crs) AS cns,
  AVG(t1.crs)   AS avs
FROM
  (SELECT *
  FROM
    (SELECT recordingid,
      COUNT(DISTINCT mediumid) AS crs
    FROM track
    GROUP BY recordingid
    )
  WHERE crs>=100
  ) t1,
  artist t2,
  track t3,
  artist_song t4
WHERE t1.recordingid = t3.recordingid
AND t3.id            = t4.trackid
AND t4.artistid      = t2.id
GROUP BY t2.id
) WHERE cns>=10;
