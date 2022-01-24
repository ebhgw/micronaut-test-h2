package com.example.testh2


import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import spock.lang.Shared
import spock.lang.Specification

@MicronautTest
class MyTableRepoSpec extends Specification {

    @Inject
    @Shared
    MyTableRepository repo
    @Shared
    long countBefore, countAfter = 0

    void 'test save operations and find saved item by id'() {
        when: "saving a new element increment repo count and assign oid"
        countBefore = repo.count()
        println "Db has $countBefore records"
        MyTableDTO dto = new MyTableDTO(name: "name2", description: "a description", status: 'PENDING')
        MyTableDTO res1 = repo.save(dto) // whichever oid is in dto it is replaced with the one assigned by db
        countAfter = repo.count()
        println "Db has $countAfter records after saving"

        then:
        countBefore + (1 as Long) == countAfter
        res1.oid != null
        res1.name == dto.name
        res1.status == dto.status

        when: "reading back dto gives the same record values"
        MyTableDTO res2 = repo.findById(dto.oid).orElse(null)
        then:
        res2 != null
        res2.oid == dto.oid
        res2.name == dto.name
        res2.status == res1.status
    }

    void 'update status'() {
        given: "save an item"
        MyTableDTO dto = new MyTableDTO(name: "name3", description: "a description", status: 'PENDING')
        repo.save(dto)
        printTable("---> Before update")
        dto.status = 'STARTED'

        when:
        MyTableDTO res3 = repo.update(dto)
        printTable("---> After update")
        then:
        res3.oid == dto.oid
        res3.status == 'STARTED'
    }

    void printTable(String msg) {
        println "Print Table $msg"
        repo.findAll().each {
            println it
        }
    }

}
