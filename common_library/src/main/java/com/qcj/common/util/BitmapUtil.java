package com.qcj.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;

/**
 * author：qiuchunjia time：上午10:22:07 类描述：这个类是实现
 *
 */

public class BitmapUtil {
	private static final int IMAGESIZE = 70; // 设置每张图片的最大不能超过100k
	private static final int DEFAULTHEIGHT = 200; // 默认设置图片为100的高度
	private static final int DEFAULTWEIGHT = 200; // 默认设置图片为100的宽度

	/**
	 * 等比例压缩图片
	 * 
	 * @param image
	 * @return
	 */
	public static Bitmap compressImage(Bitmap originBitmap) {
		if (originBitmap != null) {
			InputStream is = bm2Is(originBitmap);
			Bitmap bitmap = getBitmapByIs(is, 0, 0);
			return bitmap;
		}
		return null;
	}

	/**
	 * 根据文件路径来获取 图片
	 * 
	 * @param srcPath
	 * @return
	 */
	public static Bitmap getImageByPath(String srcPath) {
		Options newOpts = new Options();
		/**
		 * 开始读入图片，此时把options.inJustDecodeBound设置为true，
		 * 目的在于通过option获取bitmap的宽和高就ok了
		 * 
		 * */
		newOpts.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);// 此时返回bm为空
		newOpts.inJustDecodeBounds = false;
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		// 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
		float hh = 800f;// 这里设置高度为800f
		float ww = 480f;// 这里设置宽度为480f
		// 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
		int be = 1;// be=1表示不缩放
		if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
			be = (int) (newOpts.outWidth / ww);
		} else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
			be = (int) (newOpts.outHeight / hh);
		}
		if (be <= 0)
			be = 1;
		newOpts.inSampleSize = be;// 设置缩放比例
		// 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
		bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
		return compressImage(bitmap);// 压缩好比例大小后再进行质量压缩
	}

	/**
	 * 通过输入流获取获取bitmap
	 * 
	 * @param image
	 * @return
	 */
	public static Bitmap getImageByIs(InputStream is) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Bitmap originBitmap;
		baos = is2Boas(is);
		int count = baos.toByteArray().length / 1024; // 获取原始的图片多少k
		Options options = new Options();
		options.inSampleSize = count / IMAGESIZE; // 压缩的倍数，经过计算来压缩 压缩后保证图片在100k
		originBitmap = BitmapFactory.decodeByteArray(baos.toByteArray(), 0,
				baos.toByteArray().length, options);
		return compressImage(originBitmap);// 压缩好比例大小后再进行质量压缩

	}

	/**
	 * 把输入流转换为输出流ByteArrayOutputStream
	 * 
	 * @param is
	 * @param baos
	 * @return
	 * @throws IOException
	 */
	private static ByteArrayOutputStream is2Boas(InputStream is) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		try {
			while ((len = is.read(buffer)) != -1) {
				baos.write(buffer, 0, len);
			}
			baos.close();
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return baos;
	}

	/**
	 * bitmap 转为输入流
	 * 
	 * @param originBitmap
	 * @return
	 */
	private static InputStream bm2Is(Bitmap originBitmap) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		originBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		InputStream isBm = new ByteArrayInputStream(baos.toByteArray());
		return isBm;
	}

	/********************** 解析bitmap的优化 ********************************/
	/**
	 * 按照需要的宽高来解析
	 * 
	 * @param options
	 * @param reqWidth
	 *            需要的宽度
	 * @param reqHeight
	 *            需要的高度
	 * @return
	 */
	private static int calculateInSampleSize(Options options,
			int reqWidth, int reqHeight) {
		// 源图片的高度和宽度
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;
		if (height > reqHeight || width > reqWidth) {
			// 计算出实际宽高和目标宽高的比率
			final int heightRatio = Math.round((float) height
					/ (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);
			// 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
			// 一定都会大于等于目标的宽和高。
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}
		return inSampleSize;
	}

	/**
	 * 更加resid来解析资源id
	 * 
	 * @param res
	 * @param resId
	 *            资源id
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */
	public static Bitmap getBitmapByResId(Resources res, int resId,
			int reqWidth, int reqHeight) {
		if (reqHeight <= 0) {
			reqHeight = DEFAULTHEIGHT;
		}
		if (reqWidth <= 0) {
			reqWidth = DEFAULTWEIGHT;
		}
		// 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
		final Options options = new Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(res, resId, options);
		// 调用上面定义的方法计算inSampleSize值
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);
		// 使用获取到的inSampleSize值再次解析图片
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeResource(res, resId, options);
	}

	/**
	 * 通过文件路径来获取bitmap
	 * 
	 * @param path
	 *            文件路径
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */
	public static Bitmap getBitmapByPath(String path, int reqWidth,
			int reqHeight) {
		if (reqHeight <= 0) {
			reqHeight = DEFAULTHEIGHT;
		}
		if (reqWidth <= 0) {
			reqWidth = DEFAULTWEIGHT;
		}
		// 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
		final Options options = new Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, options);
		// 调用上面定义的方法计算inSampleSize值
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);
		// 使用获取到的inSampleSize值再次解析图片
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(path, options);
	}

	/**
	 * 根据输入流来得到bitmap
	 * 
	 * @param is
	 *            输入流
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */
	public static Bitmap getBitmapByIs(InputStream is, int reqWidth,
			int reqHeight) {
		if (reqHeight <= 0) {
			reqHeight = DEFAULTHEIGHT;
		}
		if (reqWidth <= 0) {
			reqWidth = DEFAULTWEIGHT;
		}
		// bitmap 直接解析输入流比较容易为空，所以转为bytearray。。。 这样解析就不会出问题，难道这是一个bug
		ByteArrayOutputStream baos = is2Boas(is);
		// 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
		Options options = new Options();
		options.inJustDecodeBounds = true;
		byte[] bytes = baos.toByteArray();
		BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
		// 调用上面定义的方法计算inSampleSize值
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);
		// 使用获取到的inSampleSize值再次解析图片
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
	}
}
