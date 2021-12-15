package com.puppy.client.mypage.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.puppy.client.common.vo.PetVO;

@Repository
public class MypageDAOImpl implements MypageDAO{

	@Autowired
	private SqlSession session;
	
	//펫리스트 구현
	@Override
	public List<PetVO> petList() {
		return session.selectList("petList");
	}

	//펫등록 구현
	@Override
	public int petInsert(PetVO pvo) {
		return session.insert("petInsert",pvo);
	}
	
	//펫상세 구현
	@Override
	public PetVO petDetail(PetVO pvo) {
		return (PetVO)session.selectOne("boardDetail",pvo);
	}

	//펫수정 구현
	@Override
	public int petUpdate(PetVO pvo) {
		return session.update("petUpdate",pvo);
	}
	
	//펫삭제 구현
	@Override
	public int petDelete(int p_no) {
		return session.delete("petDelete", p_no);
	}

}
