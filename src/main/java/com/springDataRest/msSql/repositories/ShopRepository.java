package com.springDataRest.msSql.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.springDataRest.msSql.entities.Shop;

@RepositoryRestResource
public interface ShopRepository extends JpaRepository<Shop, Long> {

}
