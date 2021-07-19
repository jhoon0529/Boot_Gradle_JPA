package org.zerock.bootG.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.LongFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.bootG.entity.G_DTO;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/go")
public class G_Controller {
	
	@GetMapping("/ex01")		//     /templates/go/ex01.html
	public void ex01() {
		log.info(">>>>> Run Success Complete");
	}
	
	@GetMapping("/ex02")
	public void ex02(Model md) {
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
			
			/* 해설
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
	
}
