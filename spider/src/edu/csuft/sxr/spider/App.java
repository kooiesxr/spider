package edu.csuft.sxr.spider;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @author Z
 *
 */
public class App {
	// alt+/

	public static void main(String[] args) {
		//线程池
		//固定大小
		ExecutorService pool=Executors.newFixedThreadPool(4);
		
		//无限缓存
//		pool= Executors.newCachedThreadPool();
		//一个线程
//		pool =Executors.newSingleThreadExecutor();
		
		ArrayList<Film> list=new ArrayList<>();
		String url="https://movie.douban.com/top250?start";
		
		for(int i=0;i<10;i++) {
			String path=String.format("%s=%d", url,i*25);
			pool.submit(new Spider(path, list));
		}
		pool.shutdown();
		System.out.println(pool.isTerminated());
		
		
		while(!pool.isTerminated()) {
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		     }
		System.out.println(list.size());
		     for(Film film : list) {
		    	 System.out.print(film.toCSV());
		     }
			
		
		     		     
		     
		     //写入文件
		     String file="d:/film.csv";                //绝对路径
		      file="film.csv";                         //相对路径
		     
		     //排序
		      Collections.sort(list);
		     
		     try (FileWriter out=new FileWriter(file)){
		    	 //写数据
				for(Film film : list) {
					out.write(film.toCSV());
				}
				System.out.println("ok");
			} catch (Exception e) {
				// TODO: handle exception
			}
		     //数据排序
		     Collections.sort(list);
		     System.out.println(list.size());
		     
		     ExecutorService  pool2=Executors.newFixedThreadPool(4);
		     
		     for(Film film :list) {
		    	 
		    	 System.out.println(film.url);
		    	 pool2.execute(new Spider(film.url,null));
		     }
		         pool2.shutdown();
		     
		}	
		// 目标：URL
/*		String url = "https://movie.douban.com/top250";//豆瓣的网址

		// 使用JSOUP抓取文
		try {
			Document doc = Jsoup.connect(url).get();

			Elements es = doc.select(".grid_view .item");//范围内部的图片
			System.out.println(es.size());
//创建一个影片列表
			ArrayList<Film> list=new ArrayList<>();
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
				System.out.println(f);
//				Element t=e.select(".title").first();//class的标签
//				String num=e.select(".star span").last().text();//范围内的最后一个标签
//				System.out.println(t.text()+","+num);
	*/			
				
				//f.id
                //f.title
				//list.add(f);
			//}

			//String title = doc.title();
			//String data = doc.data();
			//System.out.println(title);
			//System.out.println(data);
//	} catch (Exception e) {
			// TODO: handle exception
		//	e.printStackTrace();

		}
	


