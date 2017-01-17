package com.greenmile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenmile.bean.Member;
import com.greenmile.bean.Team;
import com.greenmile.exception.MemberAlreadyBeOnTeamException;
import com.greenmile.exception.TeamNotFoundException;
import com.greenmile.repository.MemberRestRepository;
import com.greenmile.repository.TeamRestRepository;

@Service
public class TeamService {
	
	@Autowired
	TeamRestRepository teamRestRepository;
	
	@Autowired
	MemberRestRepository memberRestRepository;
	
	@Autowired
	MemberService memberService;

	public Team addMember(Long idTeam, Long idMember){
		
		Team team = teamRestRepository.findOne(idTeam);
		Member member = memberRestRepository.findOne(idMember);
		
		if(validate(team) && memberService.validate(member)){
			isMember(team, member);
		}
		
		team.getMembers().add(member);
		teamRestRepository.save(team);
		
		return team;
	}
	
	public boolean validate(Team team){
		if(team==null){
			throw new TeamNotFoundException();
		}
		return true;
	}
	
	public boolean isMember(Team team, Member member){
		if(team.getMembers().contains(member)){
			throw new MemberAlreadyBeOnTeamException();
		}
		return true;
	}
	
}
