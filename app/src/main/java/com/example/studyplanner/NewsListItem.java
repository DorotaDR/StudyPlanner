package com.example.studyplanner;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NewsListItem extends AppCompatActivity {

    private TextView textView;
    private TextView titleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newslist_item_activity);

        textView = (TextView) findViewById(R.id.itemAfterClikedList);
        titleView = (TextView) findViewById(R.id.titleAfterClikedList);
        String uri = getIntent().getExtras().getString("pageContent");

        parseNextWebsite(uri);
    }

    private void parseNextWebsite(String uri) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();
                final StringBuilder builderTitle = new StringBuilder();
                try {
                    Document doc = Jsoup.connect(uri).get();

                    String linkTitle = doc.select("h1").text();
                    builderTitle.append(linkTitle);

                    Elements links = doc.select("p");
                    links.remove(0);
                    links.remove(0);
                    links.remove(links.indexOf(links.last()));
                    for(Element link : links) {
                        if(link != links.first() && link != links.last()) {
                            String detail = link.text() + "\n\n";
                            builder.append(detail);
                        }
                    }

                } catch (Exception e) {
                    Log.d("TAG", e.toString());
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(builder.toString());
                        titleView.setText(builderTitle.toString());
                    }
                });
            }
        }).start();
    }
}
