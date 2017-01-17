package com.greenmile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.greenmile.bean.Member;
import com.greenmile.bean.Team;
import com.greenmile.service.TeamService;

@BasePathAwareController
@RequestMapping("/teams")
public class TeamController implements ResourceProcessor<Resource<Team>> {
	
	@Autowired
    private RepositoryEntityLinks entityLinks;
	
	@Autowired
	TeamService teamService;
	
	@PostMapping(value="/{idTeam}/addMember", produces="application/hal+json")
	@ResponseBody
	public ResponseEntity<Resource<Team>> addMember(@PathVariable("idTeam") Long idTeam, @RequestBody Member member ){	
		
		Team team = teamService.addMember(idTeam, member.getId());
		Resource<Team> resource = new Resource<Team>(team);
		
		return new ResponseEntity<Resource<Team>>(resource,HttpStatus.CREATED);
	}

	@Override
	public Resource<Team> process(Resource<Team> resource) {
		Team team = resource.getContent();
		resource.add(entityLinks.linkForSingleResource(Team.class, team.getId()+"/addMember").withRel("addMember"));
		return resource;
	}
}
