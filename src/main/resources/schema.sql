DROP TABLE IF EXISTS consultants CASCADE;
DROP TABLE IF EXISTS divisions CASCADE;
DROP TABLE IF EXISTS directions CASCADE;
DROP TABLE IF EXISTS offices CASCADE;
DROP TABLE IF EXISTS sub_divisions CASCADE;
DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE IF NOT EXISTS divisions (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  name VARCHAR(250) NOT NULL,
  CONSTRAINT pk_divisions PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS directions (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  name VARCHAR(250) NOT NULL,
  CONSTRAINT pk_directions PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS offices (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  name VARCHAR(250) NOT NULL,
  CONSTRAINT pk_offices PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS sub_divisions (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  name VARCHAR(250) NOT NULL,
  CONSTRAINT pk_sub_divisions PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS users (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  name VARCHAR(250) NOT NULL,
  CONSTRAINT pk_users PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS consultants (
   id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   division_id BIGINT NOT NULL REFERENCES divisions(id) ON DELETE CASCADE,
   direction_id BIGINT NOT NULL REFERENCES directions(id) ON DELETE CASCADE,
   office_id BIGINT NOT NULL REFERENCES offices(id) ON DELETE CASCADE,
   sub_division_id BIGINT REFERENCES sub_divisions(id) ON DELETE CASCADE,
   user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
   number_Of_Task FLOAT NOT NULL,
   CONSTRAINT pk_consultants PRIMARY KEY (id)
);