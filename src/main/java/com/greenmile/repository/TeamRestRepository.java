package com.greenmile.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import com.greenmile.bean.Team;

@PreAuthorize("hasRole('ADMIN')")
@RestResource(path="teams", rel="teams")
public interface TeamRestRepository extends PagingAndSortingRepository<Team, Long>{

	@RestResource(path = "findByName")
	List<Team> findByNameIgnoreCaseContains(@Param("name") String name);

}
