package com.youtubemimic.services.utils;

import com.google.gson.Gson;
import com.googlecode.objectify.ObjectifyService;
import com.youtubemimic.bean.PlaylistEntity;
import com.youtubemimic.bean.PlaylistVideo;
import com.youtubemimic.services.config.ObjectifyWebListener;
import com.youtubemimic.services.utils.common.YouTubeMimicUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static com.youtubemimic.constants.YoutubeMimicConstant.ApiStatusCode.*;
import static com.youtubemimic.constants.YoutubeMimicConstant.Basic.*;
import static com.youtubemimic.constants.YoutubeMimicConstant.ApiError.*;
import static com.youtubemimic.constants.YoutubeMimicConstant.*;


public class PlaylistUtils {
	private final HttpServletRequest request;
	private final HttpServletResponse response;
	private HashMap<String, Object> detailsMap;
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private Date date = new Date();

	public PlaylistUtils(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public static PlaylistUtils getInstance(HttpServletRequest request, HttpServletResponse response) {
		return new PlaylistUtils(request, response);
	}

	private String getSession() {
		HttpSession session = this.request.getSession(false);
		return (String) session.getAttribute("userId");
	}

	public void processGet(String playlistTitle) throws Exception {
		try {
			YouTubeMimicUtils.writeResponse(this.response,
					(!playlistTitle.isEmpty() && playlistTitle != "") ? getDbEntity(playlistTitle) : getDbEntitybyID(),
					200);
			return;
		} catch (Exception var4) {
			errorResponse(failed_get);
			throw var4;
		}
	}

	public void processPost() throws Exception {
		try {
			PlaylistEntity entity = getInputData();
			entity.setCreateAt(formatter.format(date));
			entity.setUserId(Long.parseLong(this.getSession()));
			saveData(entity);
			successResponse(successfully_data_create);
			return;
		} catch (Exception var6) {
			errorResponse(failed_create_data);
			throw var6;
		}
	}

	public void processPut() throws Exception {
		try {
			PlaylistEntity entity = getInputData();
			PlaylistEntity dbEntity = getDbEntity(entity.getPlaylistName());
			HashMap<String, PlaylistVideo> videoupdate;
			if (dbEntity == null) {
				dbEntity = entity;
				entity.setUserId(Long.parseLong(this.getSession()));
				dbEntity.setCreateAt(formatter.format(date));
				videoupdate = new HashMap<String, PlaylistVideo>();
			} else {
				videoupdate = (HashMap<String, PlaylistVideo>) dbEntity.getVideos();
			}
			videoupdate.put(entity.getVideoId(), new PlaylistVideo(entity.getVideoId(), formatter.format(date)));
			dbEntity.setVideos(videoupdate);
			saveData(dbEntity);
			successResponse(successfully_data_update);
			return;
		} catch (Exception e) {
			errorResponse(failed_data_update);
			throw e;
		}
	}

	public void processDelete() throws Exception {
		PlaylistEntity entity = getInputData();
		try {
			ObjectifyService.ofy().delete().type(PlaylistEntity.class)
					.id(entity.getPlaylistId()).now();
			successResponse(successfully_data_delete);
			return;
		} catch (Exception e) {
			errorResponse(failed_data_delete);
			throw e;
		}
	}

	public void processVideoDelete() throws Exception {
		PlaylistEntity entity = getInputData();

		try {
			String userId = this.getSession();
			entity.setUserId(Long.parseLong(userId));
			PlaylistEntity dbEntity = getDbEntity(entity.getPlaylistName());
			HashMap<String, PlaylistVideo> videoDelete;
			if (entity.getVideoId() != null) {
				videoDelete = (HashMap<String, PlaylistVideo>) dbEntity.getVideos();
				videoDelete.remove(entity.getVideoId());
				dbEntity.setVideos(videoDelete);
			} else {
				dbEntity.setVideos(null);
			}

			saveData(dbEntity);
			successResponse(successfully_data_delete);
			return;
		} catch (Exception var5) {
			errorResponse(failed_data_delete);
			throw var5;
		}
	}

	private PlaylistEntity getDbEntity(String playlistName) {
		return ObjectifyService.ofy().load().type(PlaylistEntity.class).filter("playlistName", playlistName)
				.filter("userId", Long.parseLong(this.getSession())).first().now();
	}

	private List<PlaylistEntity> getDbEntitybyID() {
		return ObjectifyService.ofy().load().type(PlaylistEntity.class)
				.filter("userId", Long.parseLong(this.getSession())).list();
	}

	private PlaylistEntity getInputData() {
		return (new Gson()).fromJson(YouTubeMimicUtils.getRequestBody(this.request), PlaylistEntity.class);
	}

	private void saveData(PlaylistEntity entity) {
		ObjectifyService.ofy().save().entities(entity).now();
	}

	private void successResponse(String message) {
		detailsMap = new HashMap<String, Object>();
		detailsMap.put("id", this.getSession().toString());
		YouTubeMimicUtils.apiResponseWriter(this.response, SUCCESS, message, success, detailsMap, OK);
	}

	private void errorResponse(String message) {
		YouTubeMimicUtils.apiResponseWriter(this.response, ERROR, message, error, null, FAILED);
	}
}
