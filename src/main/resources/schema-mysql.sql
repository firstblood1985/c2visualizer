use c2visualizer;
drop table if exists site_user;
drop table if exists c2user;
drop table if exists wxuser;

CREATE TABLE c2user (
  id BIGINT NOT NULL AUTO_INCREMENT,
   logbook_id VARCHAR(255),
   full_name VARCHAR(255),
   age VARCHAR(255),
   location VARCHAR(255),
   affiliation VARCHAR(255),
   team VARCHAR(255),
   email VARCHAR(255),
   height VARCHAR(255),
   weight VARCHAR(255),
   member_since VARCHAR(255),
   create_time timestamp  default current_timestamp,
   update_time timestamp  default current_timestamp,
   CONSTRAINT pk_c2user PRIMARY KEY (id)
);

CREATE TABLE wxuser (
  id BIGINT NOT NULL AUTO_INCREMENT,
   open_id VARCHAR(255),
   create_time timestamp default current_timestamp,
   update_time timestamp  default current_timestamp,
   CONSTRAINT pk_wxuser PRIMARY KEY (id)
);

CREATE TABLE site_user (
  id BIGINT NOT NULL AUTO_INCREMENT,
   c_2_user_id BIGINT,
   wx_user_id BIGINT,
   user_name VARCHAR(255),
   create_time timestamp  default current_timestamp,
   update_time timestamp  default current_timestamp,
   CONSTRAINT pk_siteuser PRIMARY KEY (id)
);

ALTER TABLE site_user ADD CONSTRAINT FK_SITEUSER_ON_C_2_USER FOREIGN KEY (c_2_user_id) REFERENCES c2user (id);

ALTER TABLE site_user ADD CONSTRAINT FK_SITEUSER_ON_WX_USER FOREIGN KEY (wx_user_id) REFERENCES wxuser (id);

