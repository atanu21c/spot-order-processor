CREATE TABLE IF NOT EXISTS spot_order.orders
(
    id              BIGSERIAL          PRIMARY KEY,
    user_id         varchar(128)    not null,
    from_symbol     varchar(10)     not null,
    to_symbol       varchar(10)     not null,
    pair            varchar(20),
    from_amount     decimal,
    to_amount       decimal,
    side            varchar(20)     not null,
    order_type      varchar(20)     not null,
    price           decimal,
    requested_volume    decimal,
    order_status    varchar(20)     not null,
    order_ref_id    uuid,
    fee             decimal         not null,
    exchange        varchar(20)     not null,
    fiat_used       boolean         not null,
    created_at      timestamp       not null,
    created_by      varchar(50),
    updated_at      timestamp
);

CREATE UNIQUE INDEX IF NOT EXISTS spot_order_uindex
    on spot_order.orders (id);


CREATE TABLE IF NOT EXISTS spot_order.order_execution
(
    exe_id              BIGSERIAL          PRIMARY KEY,
    order_id            bigint          CONSTRAINT fk_order_id REFERENCES spot_order.orders(id),
    executed_volume     decimal,
    avg_price           decimal,
    total_fee           decimal,
    cost                decimal,
    exe_status          varchar(20)     not null,
    opened_at           timestamp,
    closed_at           timestamp
);

CREATE UNIQUE INDEX IF NOT EXISTS spot_order_execution_uindex
    on spot_order.order_execution (exe_id);

CREATE TABLE IF NOT EXISTS spot_order.order_trade
(
    trade_id            BIGSERIAL          PRIMARY KEY,
    order_id            bigint             CONSTRAINT fk_order_id REFERENCES spot_order.orders(id),
    external_order_id   varchar(255)       not null,
    pair                varchar(20)        not null,
    price               decimal            not null,
    volume              decimal            not null,
    cost                decimal,
    executed_at           timestamp
    );

CREATE UNIQUE INDEX IF NOT EXISTS spot_order_trade_uindex
    on spot_order.order_trade (trade_id);