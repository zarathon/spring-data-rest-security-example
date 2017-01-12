package com.greenmile.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.greenmile.bean.Team;

@RepositoryRestResource(collectionResourceRel="team", path="team")
public interface TeamRepository extends PagingAndSortingRepository<Team, Long>{
	List<Team> findByNameIgnoreCaseContains(@Param("name") String name);
}
