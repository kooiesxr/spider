package edu.csuft.sxr.spider;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class SpiderFilmDetail implements Runnable {

	
	
	String url;
	List<Film> list;
	
    public SpiderFilmDetail(String url,List<Film> list) {
	      super();
	      this.url=url;
	      this.list=list;
    	
    	
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
          try {
			Document doc=Jsoup.connect(url).get();
			Element e=d.getElementById("content");
			
			
			String name=e.selectFirst("hl span").text();
			
			String year=e.selectFirst(".year").text();
			
			String director=e.select("#info span").get(0).selectFirst(".attrs a").text();
			String script=e.select("#info .atts").get(1).text();
			String actor=e.select(" .actor .attrs").text();
			
			System.out.printf("%s,%s,%s,%s\n",
					name,
					year,
					director,
					script,
					actor
					);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
