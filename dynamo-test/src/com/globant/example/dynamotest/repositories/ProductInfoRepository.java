package com.globant.example.dynamotest.repositories;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.globant.example.dynamotest.model.ProductInfo;

@EnableScan
public interface ProductInfoRepository extends CrudRepository<ProductInfo, String> {

	List<ProductInfo> findById(String id);

	ProductInfo findOneByMsrp(String msrp);
	
	List<ProductInfo> findByMsrp(String msrp);
	
	@Query("SELECT f FROM ProductInfo f WHERE :beginTime <=  f.createTime && :endTime >=  f.createTime")
	List<ProductInfo> findByCreateTimeBetween(@Param("beginTime") Long beginTime, @Param("endTime")Long endTime );
	
}
