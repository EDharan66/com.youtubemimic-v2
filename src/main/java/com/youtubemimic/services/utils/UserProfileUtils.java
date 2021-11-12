
package com.youtubemimic.services.utils;

import com.google.gson.Gson;
import com.googlecode.objectify.ObjectifyService;
import com.youtubemimic.bean.UserDataEntity;
import com.youtubemimic.services.config.ObjectifyWebListener;
import com.youtubemimic.services.utils.common.YouTubeMimicUtils;


import static com.youtubemimic.constants.YoutubeMimicConstant.ApiStatusCode.*;
import static com.youtubemimic.constants.YoutubeMimicConstant.Basic.*;
import static com.youtubemimic.constants.YoutubeMimicConstant.ApiError.*;
import static com.youtubemimic.constants.YoutubeMimicConstant.*;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserProfileUtils {
	private final HttpServletRequest request;
	private final HttpServletResponse response;
	private HashMap<String, Object> detailsMap;

	public UserProfileUtils(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public static UserProfileUtils getInstance(HttpServletRequest request, HttpServletResponse response) {
		return new UserProfileUtils(request, response);
	}

	private String getSession() {
		HttpSession session = this.request.getSession(false);
		return (String) session.getAttribute("userId");
	}

	public void processGet() {

		try {
			String userId = this.getSession();
			YouTubeMimicUtils.writeResponse(this.response,
					ObjectifyService.ofy().load().type(UserDataEntity.class).id(userId).now(), 200);
			return;
		} catch (Exception var4) {
			errorResponse(failed_load_data);
			throw var4;
		}
	}

	public void processPut() {
		UserDataEntity entity = getInputData();

		try {
			ObjectifyService.ofy().save().entities(entity).now();
			successResponse(entity.getUserId().toString(), successfully_data_update);
			return;
		} catch (Exception var3) {
			errorResponse(failed_load_data);
			throw var3;
		}
	}

	public void processDelete() {
		UserDataEntity entity = getInputData();

		try {
			ObjectifyService.ofy().delete().type(UserDataEntity.class).id(entity.getUserId()).now();
			successResponse(entity.getUserId().toString(), successfully_data_delete);
			return;
		} catch (Exception var3) {
			errorResponse(failed_delete);
			throw var3;
		}
	}

	private UserDataEntity getInputData() {
		return (new Gson()).fromJson(YouTubeMimicUtils.getRequestBody(this.request), UserDataEntity.class);
	}

	private void successResponse(String userId, String message) {
		detailsMap.put("id", userId);
		YouTubeMimicUtils.apiResponseWriter(this.response, SUCCESS, message, success, detailsMap, OK);
	}

	private void errorResponse(String message) {
		YouTubeMimicUtils.apiResponseWriter(this.response, ERROR, message, error, null, FAILED);
	}
}
