package com.greenmile.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.greenmile.bean.Member;

@RepositoryRestResource(collectionResourceRel="member", path="member")
public interface MemberRepository extends PagingAndSortingRepository<Member, Long> {
	List<Member> findByName(@Param("name") String name);
}
