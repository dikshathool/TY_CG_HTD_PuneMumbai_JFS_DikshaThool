package com.capgemini.onlinemedicalstorespringboot.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "discussion_board")
public class MessageBean {
		@Id
		@Column(name = "message_id")
		@GeneratedValue(strategy = GenerationType.AUTO)
		private int messageId;
		
		@Column(name = "user_id")
		private int userId;
		
		@Column
		private String message;
		
		@Column(name = "message_type")
		private String messageType;
		
		//Getters and Setters
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
		public String getMessageType() {
			return messageType;
		}
		public void setMessageType(String messageType) {
			this.messageType = messageType;
		}
		
		
}//End of Class
