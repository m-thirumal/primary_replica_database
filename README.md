# primary_replica_database
Read-write and read-only transaction routing to primary and replica PostgreSQL database with Spring boot
https://spring.io/blog/2007/01/23/dynamic-datasource-routing/

![Brainstrom](primary_replica_transaction.png)

## Routing

[http://localhost:8080/order/create](http://localhost:8080/order/create) will create new order/insert new row to `Primary` database

[http://localhost:8080/order](http://localhost:8080/order) will fetch record from `replica` database



## SQL

```

-- DROP SEQUENCE IF EXISTS public.order_id_seq;

CREATE SEQUENCE IF NOT EXISTS public.order_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.order_id_seq
    OWNED BY public."order".order_id;

ALTER SEQUENCE public.order_id_seq
    OWNER TO postgres;
    
CREATE TABLE IF NOT EXISTS public."order"
(
    order_id bigint NOT NULL DEFAULT nextval('order_id_seq'::regclass),
    name character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT order_pkey PRIMARY KEY (order_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."order"
    OWNER to postgres;
    
```