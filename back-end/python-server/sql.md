```sql
insert into users (user_id, car_id, is_admin, wallet) values
(1, '京EA5331', 1, 10000.0);
insert into users (user_id, car_id, is_admin, wallet) values
(2, '沪AN9241', 1, 10000.0);

create table dynamic_map (
    id integer primary key,
    data blob
);

```