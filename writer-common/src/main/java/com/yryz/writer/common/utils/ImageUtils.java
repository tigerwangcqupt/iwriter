package com.yryz.writer.common.utils;

import com.yryz.service.api.api.common.Constants;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 小工具
 * 
 * @author danshiyu
 *
 */
public class ImageUtils {

	/**
	 * 生成图像验证码图片
	 *
	 * @param code
	 * @param response
	 */
	public static void getSmsImgByCode(String code, HttpServletResponse response) {
		Random random = new Random();
		// 在服务器端内存中生成一个缓冲图片
		int width = 230;
		int height = 100;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		// 获取指向该图像上的Graphic画笔
		Graphics g = image.getGraphics();

		// 绘制填充矩形
		g.setColor(new Color(255, 255, 255));
		g.fillRect(0, 0, width, height);
		// 绘制边框
		g.setColor(new Color(0, 0, 0));
		g.drawRect(0, 0, width - 1, height - 1);

		// 生成干扰线条
		for (int i = 0; i < 600; i++) {
			g.setColor(getColor(100, 200, random));
			int x1 = random.nextInt(width);
			int y1 = random.nextInt(height);
			int x2 = random.nextInt(20);
			int y2 = random.nextInt(20);
			g.drawLine(x1, y1, x1 + x2, y1 + y2);
		}
		int codeLength = code.length();
		// 生成验证码
		for (int i = 0; i < codeLength; i++) {
			// 设置字体
			Font font = new Font("Times New Roman", Font.BOLD | Font.ITALIC, 80);
			g.setFont(font);
			// 设置随机颜色
			g.setColor(getColor(0, 100, random));
			g.drawString(String.valueOf(code.charAt(i)), 20 + i * 40, 60 + random.nextInt(15));
		}
		// 将服务器缓冲区中的图像输出到客户端浏览器
		try {
			ImageIO.write(image, "jpeg", response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取随机颜色
	 */
	private static Color getColor(int start, int end, Random random) {
		Integer r = start + random.nextInt(end - start);
		Integer g = start + random.nextInt(end - start);
		Integer b = start + random.nextInt(end - start);

		return new Color(r, g, b);
	}

}
