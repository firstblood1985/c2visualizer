use c2visualizer;
drop table if exists site_user;
drop table if exists c2user;
drop table if exists wxuser;
drop table if exists workout_detail;
drop table if exists workout_summary;


CREATE TABLE c2user (
  id BIGINT NOT NULL AUTO_INCREMENT,
   create_time TIMESTAMP default current_timestamp,
   update_time TIMESTAMP default current_timestamp,
   logbook_id VARCHAR(255),
   username VARCHAR(255),
   password VARCHAR(255),
   full_name VARCHAR(255),
   age VARCHAR(255),
   location VARCHAR(255),
   affiliation VARCHAR(255),
   team VARCHAR(255),
   email VARCHAR(255),
   height VARCHAR(255),
   weight VARCHAR(255),
   member_since VARCHAR(255),
   synced BOOLEAN,
   CONSTRAINT pk_c2user PRIMARY KEY (id)
);

CREATE TABLE wxuser (
  id BIGINT NOT NULL AUTO_INCREMENT,
   open_id VARCHAR(255),
   create_time timestamp default current_timestamp,
   update_time timestamp default current_timestamp,
   CONSTRAINT pk_wxuser PRIMARY KEY (id)
);

CREATE TABLE site_user (
  id BIGINT NOT NULL AUTO_INCREMENT,
   c_2_user_id BIGINT default null,
   wx_user_id BIGINT default null,
   user_name VARCHAR(255),
   create_time timestamp default current_timestamp,
   update_time timestamp default current_timestamp,
   CONSTRAINT pk_siteuser PRIMARY KEY (id)
);

CREATE TABLE workout_summary (
  id BIGINT NOT NULL AUTO_INCREMENT,
   logbook_id VARCHAR(255),
   log_id VARCHAR(255) not null,
   url VARCHAR(255),
   create_time TIMESTAMP default current_timestamp,
   update_time TIMESTAMP default current_timestamp,
   workout_date_time TIMESTAMP default current_timestamp,
   work_out VARCHAR(255),
   workout_type VARCHAR(255),
   weight_class VARCHAR(255),
   verified VARCHAR(255),
   ranked VARCHAR(255),
   entered VARCHAR(255),
   meters INT,
   duration VARCHAR(255),
   pace VARCHAR(255),
   calories INT,
   average_heart_rate INT,
   average_watts INT,
   cal_per_hour INT,
   stroke_rate INT,
   stroke_count INT,
   drag_factor INT,

   CONSTRAINT pk_workoutsummary PRIMARY KEY (id)
);

ALTER TABLE site_user ADD CONSTRAINT FK_SITEUSER_ON_C_2_USER FOREIGN KEY (c_2_user_id) REFERENCES c2user (id);

ALTER TABLE site_user ADD CONSTRAINT FK_SITEUSER_ON_WX_USER FOREIGN KEY (wx_user_id) REFERENCES wxuser (id);

CREATE TABLE workout_detail (
  id BIGINT NOT NULL AUTO_INCREMENT,
   create_time TIMESTAMP default current_timestamp,
   update_time TIMESTAMP default current_timestamp,
   duration VARCHAR(255),
   meters INT,
   pace VARCHAR(255),
   watts INT,
   cal_per_hour INT,
   stroke_rate INT,
   heart_rate INT,
   log_id VARCHAR(255) not null,
   CONSTRAINT pk_workoutdetail PRIMARY KEY (id)
);

ALTER TABLE workout_summary ADD UNIQUE INDEX LOGID (log_id);

ALTER TABLE workout_detail ADD CONSTRAINT FK_WORKOUTDETAIL_ON_LOG FOREIGN KEY (log_id) REFERENCES workout_summary (log_id);
