package com.greenmile.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import com.greenmile.bean.Member;

@PreAuthorize("hasRole('ADMIN')")
@RestResource(path="members", rel="members")
public interface MemberRepository extends PagingAndSortingRepository<Member, Long> {
	
	@RestResource(path = "findByName")
	List<Member> findByNameIgnoreCaseContains(@Param("name") String name, Pageable p);

}
