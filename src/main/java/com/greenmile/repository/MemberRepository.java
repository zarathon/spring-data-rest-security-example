package com.greenmile.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import com.greenmile.bean.Member;

@PreAuthorize("hasRole('ADMIN')")
@RepositoryRestResource(collectionResourceRel="member", path="member")
public interface MemberRepository extends PagingAndSortingRepository<Member, Long> {
	
	@PreAuthorize("hasRole('ADMIN')")
	List<Member> findByNameIgnoreCaseContains(@Param("name") String name);

}
