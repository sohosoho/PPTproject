package com.puppy.client.mypage.service;

import java.util.List;

import com.puppy.client.common.vo.PetVO;

public interface MypageService {
	public List<PetVO> petList();
	public int petInsert(PetVO pvo);
}
