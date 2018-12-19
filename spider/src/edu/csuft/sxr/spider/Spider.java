package edu.csuft.sxr.spider;
/*
 * ץȡҳ����ӰƬ��Ϣ�����棨����
 */

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Spider implements Runnable{
     /*
      * ҳ��·��
      */
	String url;
	/*
	 * �洢ץȡ������
	 */
	ArrayList<Film> list;
	
	public Spider(String url, ArrayList<Film> list) {
		super();
		this.url = url;
		this.list = list;
	}

	public void run() {
		//���ִ�и������̵߳�����
		String name =Thread.currentThread().getName();
		System.out.println(name+"�߳� ,����" +url);
		
		//JSOUP
		try {
			Document doc=Jsoup.connect(url).get();
			/*
			 * ���ĵ��в��ҽ��
			 */
			Elements es = doc.select(".grid_view .item");//��Χ�ڲ���ͼƬ
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
				f.url=e.select(".pic a").first().attr("href");
/*				System.out.println(f);
    			Element t=e.select(".title").first();//class�ı�ǩ
    			String num1=e.select(".star span").last().text();//��Χ�ڵ����һ����ǩ
     			System.out.println(t.text()+","+num1);
	*/
			    //System.out.println(name+":" +f);
				list.add(f);
			}
			   System.out.println(name+"�̣߳���ɣ�"+ url);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
}
