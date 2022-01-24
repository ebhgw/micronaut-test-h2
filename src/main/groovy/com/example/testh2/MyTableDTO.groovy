package com.example.testh2

import io.micronaut.core.annotation.Introspected
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity

@Introspected
@MappedEntity(value='MYTABLE')
class MyTableDTO {
    @Id
    @GeneratedValue
    Long oid
    String name
    String description
    String status

    String toString () {
        "oid $oid, name $name, status $status"
    }
}
