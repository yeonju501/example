package bit.hibooks.database;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class JsoupTests {
   
//   @Test
//   public void useJsoup() {
//      Document doc = null;
//      String url = "http://www.aladin.co.kr/ttb/api/ItemList.aspx?ttbkey=ttbbetweenhj7021246002&QueryType=Bestseller&MaxResults=50&start=1&SearchTarget=Book&output=xml&Version=20131101";
//      try {
//         doc = Jsoup.connect(url).get();
//      } catch (IOException e) {
//         e.printStackTrace();
//      }
//      
//      Elements items = doc.select("item");
//      for(Element item: items) {
//         
//         Elements tags = item.select("item > *");
//         List<String> list = tags.eachText();
//         String a = item.attr("itemId");
//         //
//         
//         int i = 1;
//         for(String tag: list) {
//            log.info(tag+"#"+i);
//            i++;
//         }
//      }
//   }
   @Test
   public void selectSOGAE() {
      Document doc = null;
      String url = "https://ridibooks.com/category/books/100?page=1";
      try {
         doc = Jsoup.connect(url).get();
      } catch (IOException e) {
         e.printStackTrace();
      }
      
      log.info(doc.select("a"));
      
   }
}
 