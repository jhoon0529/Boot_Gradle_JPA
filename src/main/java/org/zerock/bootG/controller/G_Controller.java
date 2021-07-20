package org.zerock.bootG.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.LongFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.bootG.entity.G_DTO;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/go")
public class G_Controller {
	
	@GetMapping("/ex01")		//     /templates/go/ex01.html
	public void ex01() {
		log.info(">>>>> Run ex01 Success Complete");
	}
	
	@GetMapping("/ex02")
	public void ex02(Model md) {
		log.info(">>>>> Run ex02 Success Complete");
		
		List<G_DTO> list = IntStream.rangeClosed(1, 20).asLongStream()
									.mapToObj(new LongFunction<G_DTO>() {

										@Override
										public G_DTO apply(long value) {
											G_DTO dto = G_DTO.builder()
																.sno(value)
																.first("first.."+value)
																.last("lost.."+value)
																.regtime(LocalDateTime.now())
																.build();
											return dto;
										}
									}).collect(Collectors.toList());
			md.addAttribute("list", list);
			
			/* 설명
			 * rangeClosed(start,end) :  숫자 범위 지정
			 * asLongStream() :  Stream을 Long타입으로 전환
			 * mapToObj() : 특화된 타입(obj=객체)을 리턴
			 * new LongFunction<타입>() : 새로운 map형식 <타입>에 객체를 저장
			 * apply() : map 타입 객체 리턴
			 * collect(Collectors.toList()) : Stream으로 처리된 결과를 toList()를 사용
			 * 											리스트 형식으로 저장
			 * List<G_DTO> : 저장된 collect를 List로 저장
			 * 
			 * Stream->범위 및 Long타입전환->Map형식 객체저장->Stream결과 List타입으로 저장
			 * 최종 출력을 위해 Model에 저장
			 */
	}
	
	//redirect 형식
	@GetMapping({"/inline"})
	public String exInline(RedirectAttributes redirectAttributes) {
			log.info(">>>>> Inline Success Complete");
		
			G_DTO dto = G_DTO.builder().sno(100L)
											.first("Inline...100")
											.last("Last...100")
											.regtime(LocalDateTime.now())
											.build();
			
			//Model 이 아닌 redirect 이용
			redirectAttributes.addFlashAttribute("result", "success");
			redirectAttributes.addFlashAttribute("dto", dto);
			
		return "redirect:/go/ex03";		//controller에서 ex03호출, redirect는 Get방식
	}
	@GetMapping("/ex03")		//inline 실행시 받아서 html 실행
	public void ex03() {
		log.info(">>>>> Redirect_ex03: from inline_ Success Complete");
	}
	
	@GetMapping("/ex04")
	public void ex04(RedirectAttributes redirectAttributes, Model md) {
		List<G_DTO> list = IntStream.rangeClosed(1, 10).asLongStream()
										.mapToObj( i->{
												G_DTO dto = G_DTO.builder()
																	.sno(i)
																	.first("st Time: "+i)
																	.last("ed Time: "+i)
																	.regtime(LocalDateTime.now())
																	.build();
												return dto;
										}).collect(Collectors.toList());
		redirectAttributes.addFlashAttribute("alist",list);
		md.addAttribute("list", list);
		log.info(">>>>> Run ex04 Success Complete");
//		for(G_DTO a : list) {
//			System.out.println(a);
//		}
	}
	
	
}
