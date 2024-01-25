SELECT CountryName, SUM(Population) FROM City
JOIN State as s
ON City.StateId = s.StateId
JOIN COUNTRY
ON COUNTRY.CountryId = s.CountryId
GROUP BY Country.CountryId, Country.CountryName;