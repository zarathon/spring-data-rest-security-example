package com.greenmile.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.ControllerEntityLinks;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greenmile.bean.Team;
import com.greenmile.service.TeamService;

@BasePathAwareController
@RequestMapping("/teams")
public class TeamController implements ResourceProcessor<Resource<Team>> {
	
	@Autowired
	TeamService teamService;
	@Autowired
    private EntityLinks entityLinks;
	
	@RequestMapping(value="/{idTeam}/addMember", produces="application/json", method=RequestMethod.POST)
	public ResponseEntity<Resource<Team>> addMember(@PathVariable("idTeam") Long idTeam, @RequestParam("idMember") Long idMember ){
		try{
			Team team = teamService.addMember(idTeam, idMember);
			Resource<Team> resource = new Resource<Team>(team);
			return new ResponseEntity<Resource<Team>>(resource, HttpStatus.CREATED);
		} catch(Exception e){
			return new ResponseEntity<Resource<Team>>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public Resource<Team> process(Resource<Team> resource) {
		Team team = resource.getContent();
		resource.add(entityLinks.linkForSingleResource(Team.class, team.getId()+"/addMember").withRel("addMember"));
		return resource;
	}

	
	
	

}
