package com.example.testh2

import io.micronaut.data.annotation.Repository
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository

@Repository(value = "default")
@JdbcRepository(dialect = Dialect.H2)
interface MyTableRepository extends CrudRepository<MyTableDTO,Long> {}
