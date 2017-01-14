package com.greenmile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greenmile.bean.Member;
import com.greenmile.bean.Team;
import com.greenmile.exception.MemberAlreadyBeOnTeam;
import com.greenmile.repository.TeamRestRepository;

@BasePathAwareController
@RequestMapping("/teams")
public class TeamController implements ResourceProcessor<Resource<Team>> {
	
	@Autowired
    private RepositoryEntityLinks entityLinks;
	
	@Autowired
	TeamRestRepository teamRestRepository;
	
	@RequestMapping(value="/{idTeam}/addMember", produces="application/json", method=RequestMethod.POST)
	public ResponseEntity<Team> addMember(@PathVariable("idTeam") Team team, @RequestParam("idMember") Member member ){		
		
		
		if(member==null){
			throw new ResourceNotFoundException("This member not exist.");
		} else if(team==null){
			throw new ResourceNotFoundException("This team not exist.");
		} else if(team.getMembers().contains(member)){
			throw new MemberAlreadyBeOnTeam();
		}
		
		team.getMembers().add(member);
		teamRestRepository.save(team);
		
		//Resource<Team> resource = new Resource<Team>(team,entityLinks.linkForSingleResource(Team.class, team));
				
		return new ResponseEntity<Team>(team, HttpStatus.CREATED);
	}

	@Override
	public Resource<Team> process(Resource<Team> resource) {
		Team team = resource.getContent();
		resource.add(entityLinks.linkForSingleResource(Team.class, team.getId()+"/addMember").withRel("addMember"));
		return resource;
	}
}
