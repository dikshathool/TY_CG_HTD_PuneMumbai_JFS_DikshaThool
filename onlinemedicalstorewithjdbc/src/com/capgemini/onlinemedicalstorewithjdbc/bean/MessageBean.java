package com.capgemini.onlinemedicalstorewithjdbc.bean;

public class MessageBean {
		
		private int messageId;
		private int userId;
		private String message;
		private String type;
		public int getMessageId() {
			return messageId;
		}
		public void setMessageId(int messageId) {
			this.messageId = messageId;
		}
		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		
}//End of Class
