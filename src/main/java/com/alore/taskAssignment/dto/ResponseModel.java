package com.alore.taskAssignment.dto;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseModel {

	private String message;
	private String status;
	private Object data;
}