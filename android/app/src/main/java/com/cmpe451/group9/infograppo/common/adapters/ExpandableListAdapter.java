package com.cmpe451.group9.infograppo.common.adapters;

/**
 * Created by gral on 11/20/16.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmpe451.group9.infograppo.R;

import java.util.List;
import java.util.Map;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Activity context;
    private Map<String, List<String>> topicsWithRelations;
    private List<String> topics;

    public ExpandableListAdapter(Activity context, List<String> topics,
                                 Map<String, List<String>> topicsWithRelations) {
        this.context = context;
        this.topicsWithRelations = topicsWithRelations;
        this.topics = topics;
    }

    public Object getChild(int groupPosition, int childPosition) {
        return topicsWithRelations.get(topics.get(groupPosition)).get(childPosition);
    }

    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }


    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String relation = (String) getChild(groupPosition, childPosition);
        LayoutInflater inflater = context.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.child_item, null);
        }

        TextView item = (TextView) convertView.findViewById(R.id.text_child);
        /*ImageView up = (ImageView) convertView.findViewById(R.id.image_relation_up);
        up.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView rate = (TextView) view.findViewById(R.id.text_child);
            }
        });*/
//        ImageView delete = (ImageView) convertView.findViewById(R.id.nav_view);
//        delete.setOnClickListener(new OnClickListener() {
//
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                builder.setMessage("Do you want to remove?");
//                builder.setCancelable(false);
//                builder.setPositiveButton("Yes",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                List<String> child =
//                                        topicsWithRelations.get(topics.get(groupPosition));
//                                child.remove(childPosition);
//                                notifyDataSetChanged();
//                            }
//                        });
//                builder.setNegativeButton("No",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                dialog.cancel();
//                            }
//                        });
//                AlertDialog alertDialog = builder.create();
//                alertDialog.show();
//            }
//        });

        item.setText(relation);
        return convertView;
    }

    public int getChildrenCount(int groupPosition) {
        return topicsWithRelations.get(topics.get(groupPosition)).size();
    }

    public Object getGroup(int groupPosition) {
        return topics.get(groupPosition);
    }

    public int getGroupCount() {
        return topics.size();
    }

    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String topicName = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.group_item,
                    null);
        }
        TextView item = (TextView) convertView.findViewById(R.id.text_group);
        item.setTypeface(null, Typeface.BOLD);
        item.setText(topicName);
        return convertView;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
