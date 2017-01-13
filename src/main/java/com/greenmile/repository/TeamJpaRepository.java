package com.greenmile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.greenmile.bean.Team;

public interface TeamJpaRepository extends JpaRepository<Team, Long> {

}
