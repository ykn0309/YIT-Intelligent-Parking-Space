```sql
create table yit_user (
    userid integer,
    username varchar(20),
    carid varchar(20),
    parkid varchar(10),
    startTime varchar(20),
    endTime varchar(20)
);

INSERT INTO yit_user (userid, username, carid, parkid) VALUES
(1, '易佳', '京EA5331', 'P01');

insert into users (user_id, car_id, is_admin, wallet) values
(1, '京EA5331', 1, 10000.0);
insert into users (user_id, car_id, is_admin, wallet) values
(2, '沪AN9241', 1, 10000.0);

```