package com.project.board.domain;

import java.util.Date;

import org.codehaus.jackson.map.ObjectMapper;

public abstract class BaseVO {
	public Date createdOn;
	public Date updatedOn;

	public String queryType;
	public String queryType1;
	public int startIndex;
	public int pageSize;
	public int totalCount;

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}
	
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public String getQueryType1() {
		return queryType1;
	}

	public void setQueryType1(String queryType1) {
		this.queryType1 = queryType1;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	/**
	 * previous set pageSize
	 * */
	public void setPage( int page ){
		this.startIndex = ( 1 - page ) * pageSize;		
	}

	@Override
	public String toString() {
		String jsonString = "";
		try {
			ObjectMapper mapper = new ObjectMapper();
			jsonString = mapper.writeValueAsString(this);
		} catch (Exception e) {
			e.printStackTrace();
			return super.toString();
		}
		return jsonString;
	}
	
	

}
