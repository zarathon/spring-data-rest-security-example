package com.greenmile;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.greenmile.bean.Member;
import com.greenmile.bean.Team;
import com.greenmile.exception.MemberAlreadyBeOnTeamException;
import com.greenmile.exception.MemberNotFoundException;
import com.greenmile.exception.TeamNotFoundException;
import com.greenmile.repository.MemberRestRepository;
import com.greenmile.repository.TeamRestRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
@WebAppConfiguration
public class TeamRestTest {

	@Autowired
	MemberRestRepository memberRestRepository;
	
	@Autowired
	TeamRestRepository teamRestRepository;
	
	@Autowired
	WebApplicationContext context;
	
	//Variables
	private MockMvc mockMvc;
	Gson gson = new Gson();
	
	Team teamMock;
	Member memberMock;
	
	@Before
	public void setup(){
		
		mockMvc = MockMvcBuilders
					.webAppContextSetup(context)
					.apply(springSecurity())
					.build();
	
	}
	
	@Test
	@WithMockUser(roles="ADMIN")
	public void addAMemberToATeamWithValidTeamAndValidMember() throws Exception{
	
		String memberName = "Mestre Yoda";
		String teamName = "Lightside";
		
		
		memberMock = memberRestRepository.save(new Member(memberName));
		teamMock = teamRestRepository.save(new Team(teamName));
		
		
		Member memberTemp = new Member();
		memberTemp.setId(memberMock.getId());
		
		String json = gson.toJson(memberTemp);
		
		mockMvc.perform(
					post("/teams/{id}/addMember", teamMock.getId())
					.contentType(MediaType.APPLICATION_JSON)
					.content(json)
				)
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id", is(teamMock.getId().intValue())))
				.andExpect(jsonPath("$.name", is(teamName)))
				.andExpect(jsonPath("$.members").isNotEmpty())
				.andExpect(jsonPath("$.members[0].name", is(memberName)));
		
	}
	
	@Test
	@WithMockUser(roles="ADMIN")
	public void addAMemberToATeamWithValidTeamAndInvalidMember() throws Exception {

		String teamName = "Darkside";
		teamMock = teamRestRepository.save(new Team(teamName));
		
		int memberIDInvalid = 999;
		
		Member memberTemp = new Member();
		memberTemp.setId((long) memberIDInvalid);
		
		String json = gson.toJson(memberTemp);
		
		MvcResult mvcResult = mockMvc.perform(
					post("/teams/{id}/addMember", teamMock.getId())
					.contentType(MediaType.APPLICATION_JSON)
					.content(json)
				)
				.andExpect(status().isNotFound())
				.andReturn();
		
		assertEquals(mvcResult.getResolvedException().getClass(), MemberNotFoundException.class);
	}
	
	@Test
	@WithMockUser(roles="ADMIN")
	public void addAMemberToATeamWithInvalidTeamAndValidMember() throws Exception{
		
		String memberName = "R2D2";
		memberMock = memberRestRepository.save(new Member(memberName));
		
		int teamIDInvalid = 999;
		
		Member memberTemp = new Member();
		memberTemp.setId(memberMock.getId());
		
		String json = gson.toJson(memberTemp);
		
		
		MvcResult mvcResult = mockMvc.perform(
					post("/teams/{id}/addMember", teamIDInvalid)
					.contentType(MediaType.APPLICATION_JSON)
					.content(json)
				)
				.andExpect(status().isNotFound())
				.andReturn();
		
		assertEquals(mvcResult.getResolvedException().getClass(), TeamNotFoundException.class);
	}
	
	@Test
	@WithMockUser(roles="ADMIN")
	public void addMemberToATeamWithMemberAlreadyBeOnTeam() throws Exception{
		
		String memberName = "C3PO";
		String teamName = "Anotherside";
		
		
		memberMock = memberRestRepository.save(new Member(memberName));
		teamMock = teamRestRepository.save(new Team(teamName));
		
		
		Member memberTemp = new Member();
		memberTemp.setId(memberMock.getId());
		
		String json = gson.toJson(memberTemp);
		
		mockMvc.perform(
					post("/teams/{id}/addMember", teamMock.getId())
					.contentType(MediaType.APPLICATION_JSON)
					.content(json)
				)
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id", is(teamMock.getId().intValue())))
				.andExpect(jsonPath("$.name", is(teamName)))
				.andExpect(jsonPath("$.members").isNotEmpty())
				.andExpect(jsonPath("$.members[0].name", is(memberName)));
		
		MvcResult mvcResult = mockMvc.perform(
				post("/teams/{id}/addMember", teamMock.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
			)
			.andExpect(status().isConflict())
			.andReturn();
		
		assertEquals(mvcResult.getResolvedException().getClass(), MemberAlreadyBeOnTeamException.class);
	}
	
	
	
	
	
	
	
	
	
}
