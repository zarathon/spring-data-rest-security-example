package com.greenmile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.greenmile.bean.Member;
import com.greenmile.bean.Team;
import com.greenmile.repository.TeamRestRepository;

@BasePathAwareController
@RequestMapping("/teams")
public class TeamController implements ResourceProcessor<Resource<Team>> {
	
	@Autowired
    private EntityLinks entityLinks;
	
	@Autowired
	TeamRestRepository teamRestRepository;
	
	@RequestMapping(value="/{idTeam}/addMember", produces="application/json", method=RequestMethod.POST)
	public ResponseEntity addMember(@PathVariable("idTeam") Team team, @RequestParam("idMember") Member member ){		
		
		if(member==null){
			return new ResponseEntity("This member not exist.", HttpStatus.NOT_FOUND);
		} else if(team==null){
			return new ResponseEntity("This team not exist.", HttpStatus.NOT_FOUND);
		} else if(team.getMembers().contains(member)){
			return new ResponseEntity("This member is already on team.", HttpStatus.CONFLICT);
		}
		
		team.getMembers().add(member);
		teamRestRepository.save(team);
		
		Resource<Team> resource = new Resource<Team>(team);
				
		return new ResponseEntity(resource, HttpStatus.CREATED);
	}

	@Override
	public Resource<Team> process(Resource<Team> resource) {
		Team team = resource.getContent();
		resource.add(entityLinks.linkForSingleResource(Team.class, team.getId()+"/addMember").withRel("addMember"));
		return resource;
	}
}
