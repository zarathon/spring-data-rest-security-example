package com.greenmile.service;

import org.springframework.stereotype.Service;

import com.greenmile.bean.Member;
import com.greenmile.exception.MemberNotFoundException;

@Service
public class MemberService {

	public boolean validate(Member member){
		if(member==null){
			throw new MemberNotFoundException();
		}
		return true;
	}
}
