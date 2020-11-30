package com.example.studyplanner.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.studyplanner.R;
import com.example.studyplanner.models.Subject;

import java.util.HashMap;
import java.util.List;

public class ExpandableAdapter extends BaseExpandableListAdapter {

    Context context;
    List<String> listGroup;
    HashMap<String, List<Subject>> listItem;

    public ExpandableAdapter(Context context, List<String> listGroup, HashMap<String, List<Subject>> listItem) {
        this.context = context;
        this.listGroup = listGroup;
        this.listItem = listItem;
    }

    @Override
    public int getGroupCount() {
        return listGroup.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listItem.get(listGroup.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listGroup.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listItem.get(listGroup.get(groupPosition)).get(childPosition).getClassRoom();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String group = (String) getGroup((groupPosition));
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.exp_list_group, null);
        }

        TextView rodzic = convertView.findViewById(R.id.day_of_week);
        rodzic.setText(group);
        return convertView;
    }


    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.exp_list_item, null);

        TextView tv_subjectTitle = convertView.findViewById(R.id.tv_subjectTitle);
        TextView tv_classRoom = convertView.findViewById(R.id.tv_classRoom);
        TextView tv_date = convertView.findViewById(R.id.tv_date);

        tv_subjectTitle.setText(listItem.get(listGroup.get(groupPosition)).get(childPosition).getTitle());
        tv_classRoom.setText(String.valueOf(listItem.get(listGroup.get(groupPosition)).get(childPosition).getClassRoom()));
        tv_date.setText(listItem.get(listGroup.get(groupPosition)).get(childPosition).getHour());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
