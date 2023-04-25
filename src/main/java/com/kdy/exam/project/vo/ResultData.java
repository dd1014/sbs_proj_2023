package com.kdy.exam.project.vo;

import lombok.Getter;
import lombok.ToString;

@ToString
public class ResultData <DT>{
	// (성공)S-1,S-2..
	// (실패)F-1,F-2..
	@Getter
	private String resultCode;

	@Getter
	private String msg;

	@Getter
	private String data1Name;
	
	@Getter
	private DT data1;
	
	private ResultData() {

	}

	// 실패
	public static ResultData from(String resultCode, String msg) {
		return from(resultCode, msg, null, null);
	}

	// 성공
	public static <DT> ResultData<DT> from(String resultCode, String msg, String data1Name, DT data1) {
		ResultData<DT> rd = new ResultData<DT>();
		rd.resultCode = resultCode;
		rd.msg = msg;
		rd.data1Name = data1Name;
		rd.data1 = data1;

		return rd;
	}
	
	public static <DT> ResultData<DT> newData(ResultData oldRd, String data1Name, DT data1) {
		return from(oldRd.getResultCode(),oldRd.getMsg(), data1Name, data1);
	}
	
	public boolean isSuccess() {
		return resultCode.startsWith("S-");
	}
	
	public boolean isFail() {
		return isSuccess() == false;
	}

	

}
