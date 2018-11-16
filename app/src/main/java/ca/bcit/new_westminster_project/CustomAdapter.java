package ca.bcit.new_westminster_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<CustomObject> objects;

    private class ViewHolder {
        TextView textView1;
        TextView textView2;
    }

    public CustomAdapter(Context context, List<CustomObject> objects) {
        inflater = LayoutInflater.from(context);
        this.objects = objects;
    }

    public int getCount() {
        return objects.size();
    }

    public CustomObject getItem(int position) {
        return objects.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.single_row_listview, null);
            holder.textView1 = (TextView) convertView.findViewById(R.id.Address);
            holder.textView2 = (TextView) convertView.findViewById(R.id.option_info);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView1.setText(objects.get(position).getAddress());
        holder.textView2.setText(objects.get(position).getInfo());
        return convertView;
    }
}
