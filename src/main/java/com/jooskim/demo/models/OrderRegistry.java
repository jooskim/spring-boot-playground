package com.jooskim.demo.models;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderRegistry extends PagingAndSortingRepository<Order, Long> {

}