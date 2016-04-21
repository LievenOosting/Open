package com.qcj.common.util;

import java.io.File;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

public class MyIntent {
	/**
	 * 返回一个打开相册的intent
	 * 
	 * @return
	 */
	public static Intent getGalleyIntent() {
		Intent intent = new Intent(Intent.ACTION_PICK,
				MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		return intent;
	}

	/**
	 * 返回一个打开相机的intent
	 * 
	 * @return
	 */
	public static Intent getCameraIntent() {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		return intent;
	}

	/**
	 * 返回一个获取本地文件的intent
	 * 
	 * @return
	 */
	public static Intent getLocalFileIntent() {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("*/*");
		intent.addCategory(Intent.CATEGORY_OPENABLE);
		Intent intentChoose = Intent.createChooser(intent, "请选择文件!");
		return intentChoose;
	}

	/**
	 * 返回一个选择本地视频的intent
	 * 
	 * @return
	 */
	public static Intent getVideoFileIntent() {
		Intent intent = new Intent(Intent.ACTION_PICK,
				MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
		return intent;
	}

	/**
	 * 返回一个打开拍摄视频的intent
	 * 
	 * @return
	 */
	public static Intent getshootVideoIntent() {
		Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
		// 设置视频大小
		intent.putExtra(MediaStore.EXTRA_SIZE_LIMIT,
				20 * 1024 * 1024); // 设置为20M
		return intent;
	}

	/*********************************************************************************/
	// android获取一个用于打开HTML文件的intent
	public static Intent getHtmlFileIntent(String param) {
		Uri uri = Uri.parse(param).buildUpon()
				.encodedAuthority("com.android.htmlfileprovider")
				.scheme("content").encodedPath(param).build();
		Intent intent = new Intent("android.intent.action.VIEW");
		intent.setDataAndType(uri, "text/html");
		return intent;
	}

	// android获取一个用于打开图片文件的intent
	public static Intent getImageFileIntent(String param) {
		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Uri uri = Uri.fromFile(new File(param));
		intent.setDataAndType(uri, "image/*");
		return intent;
	}

	// android获取一个用于打开PDF文件的intent
	public static Intent getPdfFileIntent(String param) {
		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Uri uri = Uri.fromFile(new File(param));
		intent.setDataAndType(uri, "application/pdf");
		return intent;
	}

	// android获取一个用于打开文本文件的intent
	public static Intent getTextFileIntent(String param, boolean paramBoolean) {
		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		if (paramBoolean) {
			Uri uri1 = Uri.parse(param);
			intent.setDataAndType(uri1, "text/plain");
		} else {
			Uri uri2 = Uri.fromFile(new File(param));
			intent.setDataAndType(uri2, "text/plain");
		}
		return intent;
	}

	// android获取一个用于打开音频文件的intent
	public static Intent getAudioFileIntent(String param) {
		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra("oneshot", 0);
		intent.putExtra("configchange", 0);
		Uri uri = Uri.fromFile(new File(param));
		intent.setDataAndType(uri, "audio/*");
		return intent;
	}

	// android获取一个用于打开视频文件的intent
	public static Intent getVideoFileIntent(String param) {
		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra("oneshot", 0);
		intent.putExtra("configchange", 0);
		Uri uri = Uri.fromFile(new File(param));
		intent.setDataAndType(uri, "video/*");
		return intent;
	}

	// android获取一个用于打开CHM文件的intent
	public static Intent getChmFileIntent(String param) {
		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Uri uri = Uri.fromFile(new File(param));
		intent.setDataAndType(uri, "application/x-chm");
		return intent;
	}

	// android获取一个用于打开Word文件的intent
	public static Intent getWordFileIntent(String param) {
		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Uri uri = Uri.fromFile(new File(param));
		intent.setDataAndType(uri, "application/msword");
		return intent;
	}

	// android获取一个用于打开Excel文件的intent
	public static Intent getExcelFileIntent(String param) {
		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Uri uri = Uri.fromFile(new File(param));
		intent.setDataAndType(uri, "application/vnd.ms-excel");
		return intent;
	}

	// android获取一个用于打开PPT文件的intent
	public static Intent getPptFileIntent(String param) {
		Intent intent = new Intent("android.intent.action.VIEW");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Uri uri = Uri.fromFile(new File(param));
		intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
		return intent;
	}
}
