package com.example.studyplanner.adapters;

import com.example.studyplanner.models.News;
import com.example.studyplanner.NewsListItem;
import com.example.studyplanner.R;
import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Intent;

public class NewsAdapter extends BaseAdapter{

    private List<News> newsList;
    private View view;
    private Context mContext;
    private ViewHolder viewHolder;

    public NewsAdapter(Context mContext,List<News> obj){
        this.mContext = mContext;
        this.newsList = obj;
    }

    @Override
    public int getCount() {
        return newsList.size();
    }

    @Override
    public News getItem(int position) {return newsList.get(position);}

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.activity_listview, null);
            viewHolder = new ViewHolder();
            viewHolder.newsTitle = (TextView) view.findViewById(R.id.label);
            viewHolder.newsDesc = (TextView)view.findViewById(R.id.label2);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.newsTitle.setText(newsList.get(position).getTitle());
        viewHolder.newsDesc.setText(newsList.get(position).getDetail());

        view.setOnClickListener(new View.OnClickListener() {
            Intent intent = new Intent(mContext, NewsListItem.class);
            @Override
            public void onClick(View v) {
                String uri = getItem(position).getUri();
                intent.putExtra("pageContent", uri);
                mContext.startActivity(intent);
            }
        });
        return view;
    }

    class ViewHolder{
        public TextView newsTitle;
        public TextView newsDesc;
    }

}
