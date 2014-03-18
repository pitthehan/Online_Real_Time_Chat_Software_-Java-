package com.hehan.client.model;
import com.hehan.common.*;

public class ChatClientUser {
	public boolean checkUser(User u) {
		return new ClientConServer().sendLoginInfoToServer(u);
	}
}
