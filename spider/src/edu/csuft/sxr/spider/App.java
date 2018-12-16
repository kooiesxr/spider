package edu.csuft.sxr.spider;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 
 * @author Z
 *
 */
public class App {
	// alt+/

	public static void main(String[] args) {
		// 目标：URL
		String url = "https://movie.douban.com/top250";//豆瓣的网址

		// 使用JSOUP抓取文档
		try {
			Document doc = Jsoup.connect(url).get();

			Elements es = doc.select(".grid_view .item");//范围内部的图片
			System.out.println(es.size());
//创建一个影片列表
			ArrayList<Film> list=new ArrayList<>();
			for (Element e : es) {//循环每部影片
				Film f= new Film();
				Element t=e.select(".title").first();//class的标签
				String num=e.select(".star span").last().text();//范围内的最后一个标签
				System.out.println(t.text()+","+num);
				
				
				//f.id
                //f.title
				list.add(f);
			}

			//String title = doc.title();
			//String data = doc.data();
			//System.out.println(title);
			//System.out.println(data);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}
	}

}
