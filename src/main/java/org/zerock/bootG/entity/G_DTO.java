package org.zerock.bootG.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name="G_Board")
@Builder(toBuilder = true)
public class G_DTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sno;
	private String first;
	private String last;
	private LocalDateTime regtime;
	
}
