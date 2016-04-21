package com.qcj.common.util;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

/**
 * author：qiuchunjia time：
 * 
 * 下午3:11:56
 * 
 * 类描述：这个类是实现uri和path相互之间的转换
 *
 */

public class Uri2Path {
	/**
	 * 把图片uri转换为文件路径
	 * 
	 * @param activity
	 * @param uri
	 * @return
	 */
	public static String getPhotoPath(Activity activity, Uri uri) {
		if (activity != null && uri != null) {
			String[] proj = { MediaStore.Images.Media.DATA };
			Cursor cs = activity.managedQuery(uri, proj, null, null, null);
			int column_index = cs
					.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			cs.moveToFirst();
			String path = cs.getString(column_index);
			return path;
		}
		return null;

	}

	/**
	 * 把音乐uri转换为文件路径
	 * 
	 * @param activity
	 * @param uri
	 * @return
	 */
	public static String getMusicPath(Activity activity, Uri uri) {
		if (activity != null && uri != null) {
			String[] proj = { MediaStore.Audio.Media.DATA };
			Cursor cs = activity.managedQuery(uri, proj, null, null, null);
			int column_index = cs
					.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
			cs.moveToFirst();
			String path = cs.getString(column_index);
			return path;
		}
		return null;

	}

	/**
	 * 把视频uri转换为文件路径
	 * 
	 * @param activity
	 * @param uri
	 * @return
	 */
	public static String getVideoPath(Activity activity, Uri uri) {
		if (activity != null && uri != null) {
			String[] proj = { MediaStore.Video.Media.DATA };
			Cursor videoCursor = activity.managedQuery(uri, proj, null, null,
					null);
			int actual_video_column_index = videoCursor
					.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
			videoCursor.moveToFirst();
			String video_path = videoCursor
					.getString(actual_video_column_index);
			return video_path;
		}
		return null;

	}
}
