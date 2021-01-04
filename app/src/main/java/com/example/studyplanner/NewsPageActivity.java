package com.example.studyplanner;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import android.widget.ListView;
import android.widget.TextView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.example.studyplanner.models.News;
import com.example.studyplanner.adapters.NewsAdapter;
import android.util.Log;

public class NewsPageActivity extends AppCompatActivity {

    ListView listView;
    TextView titleView;
    List<News> mdata = new ArrayList<>();

    @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_news);

         titleView = (TextView) findViewById(R.id.title);
         listView = (ListView) findViewById(R.id.mobile_list);
        parseWebsite();
     }

    private void parseWebsite() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();
                try {
                    String uriBase ="https://www.agh.edu.pl/wydarzenia/";
                    Document doc = Jsoup.connect(uriBase).get();
                    String titleM = doc.title();
                    builder.append(titleM);

                    Elements links = doc.select("div.news-list-item");
                    for (Element link : links) {

                        String uriPrepare = link.select("h2>a[href]").first().toString();
                        uriPrepare = uriPrepare.substring(21, uriPrepare.length() - 1);
                        uriPrepare = uriPrepare.substring(0, uriPrepare.indexOf("\""));
                        String uri = uriBase + uriPrepare;

                        String title = link.select("h2>a[href]").attr("title");
                        String detail = link.select("p").text();
                        News news = new News(title, detail, uri);
                        mdata.add(news);
                    }
                } catch (Exception e) {
                    Log.d("TAG",e.toString());
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        titleView.setText(builder.toString());
                        NewsAdapter newAdapter = new NewsAdapter(NewsPageActivity.this, mdata);
                        listView.setAdapter(newAdapter);
                    }
                });
            }
        }).start();
    }
}