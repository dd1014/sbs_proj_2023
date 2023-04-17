package com.kdy.exam.project.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.javassist.compiler.ast.Member;

@Mapper
public interface MemberRepository {


	public Member getMember(@Param("id") int id);
	
	
	public List<Member> getMembers();
	
	
	public void insertMember(@Param("loginId") String loginId, @Param("loginPw") String loginPw, @Param("name") String name,
							@Param("nickname") String nickname, @Param("cellphonNo") String cellphonNo, @Param("email") String email);
	
}
