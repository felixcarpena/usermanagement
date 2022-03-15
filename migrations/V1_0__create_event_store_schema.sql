create table event_store
(
    id SERIAL primary key,
    aggregate_id binary(16),
    aggregate_id_text varchar(36) generated always as (BIN_TO_UUID(aggregate_id)) virtual,
    type varchar(191),
    version bigint not null,
    data json not null,
    unique (aggregate_id, version),
    index type_index ((json_value(data, '$.type' returning char(191))))
)engine=InnoDB default charset=UTF8MB4;