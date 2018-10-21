package com.jooskim.demo.models;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface PersonRegistry extends PagingAndSortingRepository<Person, Long> {
    Person findByLastName(String name);
}