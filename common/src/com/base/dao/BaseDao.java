package com.base.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface BaseDao<T> extends PagingAndSortingRepository<T, Long>,JpaSpecificationExecutor<T>{
	
}
