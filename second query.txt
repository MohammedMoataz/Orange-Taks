SELECT 
    b.BASE_STATION_ID,
    b.LOCATION,
    r.REGION_NAME,
    st.STATION_TYPE,
    AVG(c.DURATION) AS AVG_DURATION,
    CASE
        WHEN AVG(c.DURATION) >= 120 THEN 'Excellent'
        WHEN AVG(c.DURATION) BETWEEN 60 AND 119 THEN 'Good'
        WHEN AVG(c.DURATION) BETWEEN 30 AND 59 THEN 'Underperforming'
        WHEN AVG(c.DURATION) < 30 THEN 'Critical'
    END AS PERFORMANCE_CATEGORY
FROM
    base_stations b
        JOIN
    regions r ON b.REGION_ID = r.REGION_ID
        JOIN
    base_stations_types st ON b.BASE_STATION_ID = st.BASE_STATION_ID
        JOIN
    call_records c ON b.BASE_STATION_ID = c.BASE_STATION_ID
GROUP BY 
    b.BASE_STATION_ID, b.LOCATION, r.REGION_NAME, st.STATION_TYPE;