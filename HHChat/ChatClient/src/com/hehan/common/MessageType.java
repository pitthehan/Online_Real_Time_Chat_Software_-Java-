/*
 * Define Message Type
 * constant define in interface, so easy to use
 */
package com.hehan.common;

public interface MessageType {
	String message_succeed = "1"; //indicate login successfully
	String message_login_fail = "2"; //indicate fail to login
	String message_comm_mes = "3"; //common message packet
	String messge_get_onLineFriend = "4"; //request online friend packet
	String message_ret_onLineFriend = "5"; //return online friend packet
	
}
