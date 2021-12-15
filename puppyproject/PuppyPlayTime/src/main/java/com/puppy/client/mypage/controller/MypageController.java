package com.puppy.client.mypage.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.puppy.client.common.vo.PetVO;
import com.puppy.client.mypage.service.MypageService;

@Controller
@RequestMapping(value = "/mypage")
public class MypageController {
	private Logger log = LoggerFactory.getLogger(MypageController.class);
	
	@Autowired
	private MypageService mypageService;
	
	//펫리스트 구현하기
	@RequestMapping(value="/petList.do", method=RequestMethod.GET)
	public String petList(Model model) {
		log.info("petList 호출 성공");
		
		List<PetVO> petList = mypageService.petList();
		model.addAttribute("petList", petList);
		model.addAttribute("data");
		
		return "client/mypage/mypagePetinfo";
	}
	//펫등록 폼 출력하기
	@RequestMapping(value="/insertForm.do")
	public String insertForm() {
		log.info("insertForm 호출 성공");
		return "client/mypage/mypagePetinfoinsert";
	}
	
	//펫등록 구현하기
	@RequestMapping(value="/petInsert.do", method=RequestMethod.POST)
	public String petInsert(@ModelAttribute PetVO pvo, Model model) {
		log.info("petInsert 호출 성공");
		
		int result=0;
		String url="";
		
		result=mypageService.petInsert(pvo);
		if(result==1) {
			url="/mypage/petList.do";
		}else{
			model.addAttribute("code", 1);
			url="/mypage/insertForm.do";
		}
		
		return "redirect:"+url;
	}
	
	//펫 상세보기 구현
	@RequestMapping(value="petDetail.do", method=RequestMethod.GET)
	public String petDetail(@ModelAttribute PetVO pvo, Model model) {
		log.info("petDetail 호출 성공");
		log.info("p_no = " + pvo.getP_no());
		
		PetVO detail=new PetVO();
		detail=mypageService.petDetail(pvo);
		
		if(detail!=null) {
			detail.setP_name(detail.getP_name().toString().replaceAll("\n","<br>"));
		}
		model.addAttribute("detail", detail);
		return "client/mypage/mypagePetinfoupdate";
	}
	
	//펫수정 폼 출력하기
	@RequestMapping(value="/updateForm.do")
	public String updateForm(@ModelAttribute PetVO pvo, Model model) {
		log.info("updateForm 호출 성공");
		
		log.info("p_no = " + pvo.getP_no());
		
		PetVO updateData=new PetVO();
		updateData=mypageService.petDetail(pvo);
		
		model.addAttribute("updateData", updateData);
		return "client/mypage/mypagePetinfoupdate";
	}
	
	//펫수정 구현하기
	@RequestMapping(value="/petUpdate.do", method=RequestMethod.GET)
	public String petUpdate(@ModelAttribute PetVO pvo) {
		log.info("petUpdate 호출 성공");
		
		int result=0;
		String url="";
		
		result=mypageService.petUpdate(pvo);
		
		if(result==1) {
			url="/mypage/petList.do"; // 수정후 목록으로 이동
			
		}else {
			url="mypage/updateForm.do?p_no="+pvo.getP_no();
		}
		return "redirect:"+url;
	}
	
	//펫 삭제 구현하기
	@RequestMapping(value="/petDelete.do")
	public String petDelete(@ModelAttribute PetVO pvo) {
		log.info("petDelete 호출 성공");
		//아래 변수에는 입력 성공에 대한 상태값 담습니다.(1 or 0)
		int result=0;
		String url="";
		result=mypageService.petDelete(pvo.getP_no());
		
		if(result==1) {
			url="/mypage/petList.do";
		}else {
			url="/mypage/petDetail.do?p_no="+pvo.getP_no();
		}
		return "redirect:"+url;
	}
}
