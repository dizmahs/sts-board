package com.project.board.domain;

import org.springframework.util.StringUtils;

public class ApiResultVO {
	public static final String RESULT_CODE_OK = "0";
	public static final String RESULT_CODE_ERROR = "1";

	public String result;
	public String message;
	public Object data;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public ApiResultVO buildSuccess() {
		this.result = RESULT_CODE_OK;
		this.message = "Success.";
		return this;
	}

	public ApiResultVO buildSuccess(String message, Object data) {
		ApiResultVO entity = buildSuccess();
		entity.data = data;
		if (StringUtils.hasText(message)) {
			entity.message = message;
		}
		return entity;
	}

	public ApiResultVO buildError() {
		this.result = RESULT_CODE_ERROR;
		this.message = "Fail.";
		return this;
	}

	public ApiResultVO buildError(String message, Object data) {
		ApiResultVO entity = buildError();
		entity.data = data;
		if (StringUtils.hasText(message)) {
			entity.message = message;
		}
		return entity;
	}
}
