CREATE TABLE event_store
(
    id SERIAL PRIMARY KEY,
    aggregate_id binary(16),
    aggregate_id_text varchar(36) generated always as
     (insert(
        insert(
          insert(
            insert(hex(aggregate_id),9,0,'-'),
            14,0,'-'),
          19,0,'-'),
        24,0,'-')
     ) virtual,
    version BIGINT NOT NULL,
    data JSON NOT NULL,
    UNIQUE (aggregate_id, version)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;