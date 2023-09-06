
CREATE TABLE IF NOT EXISTS users (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  user_full_name VARCHAR(250) NOT NULL,
  CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS structure (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
  name VARCHAR(50) NOT NULL,
  division VARCHAR(250) NOT NULL,
  direction VARCHAR(250) NOT NULL,
  office VARCHAR(250) NOT NULL,
  subDivision VARCHAR(250),
  CONSTRAINT pk_structure PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS consultants (
   id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   number_of_task DOUBLE NOT NULL,
   structure_id BIGINT NOT NULL REFERENCES structure(id) ON DELETE CASCADE,
   users_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
   CONSTRAINT pk_consultants PRIMARY KEY (id)
);