package edu.csuft.sxr.spider;
/*
 * 抓取页面中影片信息的爬虫（任务）
 */

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Spider implements Runnable{
     /*
      * 页面路径
      */
	String url;
	/*
	 * 存储抓取的数据
	 */
	ArrayList<Film> list;
	
	public Spider(String url, ArrayList<Film> list) {
		super();
		this.url = url;
		this.list = list;
	}

	public void run() {
		//获得执行该任务线程的名称
		String name =Thread.currentThread().getName();
		System.out.println(name+"线程 ,处理：" +url);
		
		//JSOUP
		try {
			Document doc=Jsoup.connect(url).get();
			/*
			 * 从文档中查找结点
			 */
			Elements es = doc.select(".grid_view .item");//范围内部的图片
			for (Element e : es) {//循环每部影片
				
				Film f= new Film();
				
				f.id=Integer.parseInt(e.select(".pic em").first().text());
				f.poster=e.select("img").first().attr("src");
				f.info=e.select(".bd p").first().text();
				f.title=e.select(".title").first().text();
				f.rating=Double.parseDouble(e.select(".rating_num").first().text());
				String num=e.select(".star span").last().text();
				f.num=Integer.parseInt(num.substring(0,num.length()-3));
				f.quote=e.select(".inq").first().text();
				f.url=e.select(".pic a").first().attr("href");
/*				System.out.println(f);
    			Element t=e.select(".title").first();//class的标签
    			String num1=e.select(".star span").last().text();//范围内的最后一个标签
     			System.out.println(t.text()+","+num1);
	*/
			    //System.out.println(name+":" +f);
				list.add(f);
			}
			   System.out.println(name+"线程，完成："+ url);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
}
