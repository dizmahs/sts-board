package com.project.board.domain;

import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

public class AjaxBootstrapTablesVO<T> {

	public int total;
	public List<T> rows;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
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
