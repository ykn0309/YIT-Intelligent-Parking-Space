```sql
insert into users (user_id, car_id, is_admin, wallet) values
(1, '京EA5331', 1, 10000.0);
insert into users (user_id, car_id, is_admin, wallet) values
(2, '沪AN9241', 1, 10000.0);

create table dynamic_map (
    id bigint primary key,
    data blob
);

mysql -u root -p --local-infile=1

set global local_infile=on;


load data local infile '/home/ykn/y2024/m10/YIT-Intelligent-Parking-Space/map/ParkLocationEntity_a.csv' into table parklocations fields terminated by ',' ignore 1 lines (park_id, xlabel, ylabel, zone, occupied);
```