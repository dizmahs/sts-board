package com.project.board.domain;

public class BoardVO extends BaseVO {

	public int boardID;
	public String title;
	public String content;
	public int hitCount;
	public String replyNo;
	public String step;
	public String boardName;
	public int userID;
	public int boardMasterID;

	public int getBoardID() {
		return boardID;
	}

	public void setBoardID(int boardID) {
		this.boardID = boardID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getHitCount() {
		return hitCount;
	}

	public void setHitCount(int hitCount) {
		this.hitCount = hitCount;
	}

	public String getReplyNo() {
		return replyNo;
	}

	public void setReplyNo(String replyNo) {
		this.replyNo = replyNo;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getBoardMasterID() {
		return boardMasterID;
	}

	public void setBoardMasterID(int boardMasterID) {
		this.boardMasterID = boardMasterID;
	}

	public BoardVO test( int id ){
		if( id >0 ){
			boardID = id;
		}
		
		userID = (int) (Math.random() * 100);
		title = "title" + userID;
		content = "this is test content...";
		return this;
	}

}
