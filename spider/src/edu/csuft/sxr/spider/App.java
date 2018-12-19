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
		//�̳߳�
		//�̶���С
		ExecutorService pool=Executors.newFixedThreadPool(4);
		
		//���޻���
//		pool= Executors.newCachedThreadPool();
		//һ���߳�
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
			
		
		     		     
		     
		     //д���ļ�
		     String file="d:/film.csv";                //����·��
		      file="film.csv";                         //���·��
		     
		     //����
		      Collections.sort(list);
		     
		     try (FileWriter out=new FileWriter(file)){
		    	 //д����
				for(Film film : list) {
					out.write(film.toCSV());
				}
				System.out.println("ok");
			} catch (Exception e) {
				// TODO: handle exception
			}
		     //��������
		     Collections.sort(list);
		     System.out.println(list.size());
		     
		     ExecutorService  pool2=Executors.newFixedThreadPool(4);
		     
		     for(Film film :list) {
		    	 
		    	 System.out.println(film.url);
		    	 pool2.execute(new Spider(film.url,null));
		     }
		         pool2.shutdown();
		     
		}	
		// Ŀ�꣺URL
/*		String url = "https://movie.douban.com/top250";//�������ַ

		// ʹ��JSOUPץȡ��
		try {
			Document doc = Jsoup.connect(url).get();

			Elements es = doc.select(".grid_view .item");//��Χ�ڲ���ͼƬ
			System.out.println(es.size());
//����һ��ӰƬ�б�
			ArrayList<Film> list=new ArrayList<>();
			for (Element e : es) {//ѭ��ÿ��ӰƬ
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
//				Element t=e.select(".title").first();//class�ı�ǩ
//				String num=e.select(".star span").last().text();//��Χ�ڵ����һ����ǩ
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
	


