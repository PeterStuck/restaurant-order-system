-- 1 --
SELECT Country.CountryID, Country.Name FROM Country
JOIN City ON Country.CountryID=City.CountryID
GROUP BY City.CountryID
HAVING SUM(City.Population) > 400;

-- 2 --
SELECT Country.Name FROM Country
JOIN City ON Country.CountryID = City.CountryID
GROUP BY City.CountryID
HAVING SUM((SELECT COUNT(BuildingID) FROM Building WHERE Building.CityID = City.CityID)) = 0;