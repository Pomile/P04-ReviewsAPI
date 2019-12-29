CREATE TABLE IF NOT EXISTS PRODUCTS(
    product_id SERIAL,
    productCode VARCHAR NOT NULL UNIQUE,
    name VARCHAR NOT NULL,
    image TEXT NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    stock INTEGER NOT NULL,
    description VARCHAR NOT NULL,
    ratings INTEGER NOT NULL DEFAULT 0,
    createdAt TIMESTAMP NOT NULL DEFAULT NOW(),
    updatedAt TIMESTAMP NOT NULL DEFAULT NOW(),
    CONSTRAINT products_product_id_pk
        PRIMARY KEY (product_id)
);
