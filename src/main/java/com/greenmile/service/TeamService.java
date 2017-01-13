package com.greenmile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenmile.bean.Member;
import com.greenmile.bean.Team;
import com.greenmile.repository.MemberRestRepository;
import com.greenmile.repository.TeamRestRepository;

@Service
public class TeamService {
	
	@Autowired
	TeamRestRepository teamRestRepository;
	
	@Autowired
	MemberRestRepository memberRestRepository;

	public Team addMember(Long idTeam, Long idMember) throws Exception{
		
		if(!teamRestRepository.exists(idTeam)){
			throw new Exception("This team not exist!");
		} else if(!memberRestRepository.exists(idMember)){
			throw new Exception("This member not exist!");
		} else if(teamRestRepository.findOne(idTeam).getMembers().contains(memberRestRepository.findOne(idMember))){
			throw new Exception("This member already be on team!");
		}else{
			Team team = teamRestRepository.findOne(idTeam);
			Member member = memberRestRepository.findOne(idMember);
		
			team.getMembers().add(member);
			teamRestRepository.save(team);
			
			return team;
		}
	}

}
