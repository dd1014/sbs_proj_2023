package com.kdy.exam.project.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.kdy.exam.project.vo.Member;

@Mapper
public interface MemberRepository {

	
	public Member getMember(@Param("id") int id);

	
	public List<Member> getMembers();

	
	public void join(@Param("loginId")String loginId, @Param("loginPw")String loginPw, @Param("name")String name, @Param("nickname")String nickname, @Param("cellphoneNo")String cellphoneNo, @Param("email")String email);

	
	public void deleteMember(@Param("id")int id);

	
	public void modifyMember(@Param("id")int id, @Param("loginId")String loginId, @Param("loginPw")String loginPw, @Param("name")String name, @Param("nickname")String nickname, @Param("cellphoneNo")String cellphoneNo, @Param("email")String email);
	
	
	public int getLastInsertId();


	public Member getMemberByLoginId(@Param("loginId") String loginId);
	
	public Member getMemberByNameAndEmail(@Param("name") String name, @Param("email") String email);

	
	@Update("""
			<script>
			UPDATE `member`
			<set>
				updateDate = NOW(),
				<if test="loginPw != null">
					loginPw = #{loginPw},
				</if>
				<if test="name != null">
					name = #{name},
				</if>
				<if test="nickname != null">
					nickname = #{nickname},
				</if>
				<if test="email != null">
					email = #{email},
				</if>
				<if test="cellphoneNo != null">
					cellphoneNo = #{cellphoneNo},
				</if>
			</set>	
			WHERE id = #{id}
			</script>
			""")
	void modify(int id, String loginPw, String name, String nickname, String email,
			String cellphoneNo);
	
}