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
		// Ŀ�꣺URL
		String url = "https://movie.douban.com/top250";//�������ַ

		// ʹ��JSOUPץȡ�ĵ�
		try {
			Document doc = Jsoup.connect(url).get();

			Elements es = doc.select(".grid_view .item");//��Χ�ڲ���ͼƬ
			System.out.println(es.size());
//����һ��ӰƬ�б�
			ArrayList<Film> list=new ArrayList<>();
			for (Element e : es) {//ѭ��ÿ��ӰƬ
				Film f= new Film();
				Element t=e.select(".title").first();//class�ı�ǩ
				String num=e.select(".star span").last().text();//��Χ�ڵ����һ����ǩ
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
