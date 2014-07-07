package ch.kusar.rochedevicemessages.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ch.kusar.rochedevicemessages.Models.Message;
import ch.kusar.rochedevicemessages.R;

/**
 * Created by ku5ar on 07.07.14.
 */
public class CardLayoutAdapter extends ArrayAdapter<Message> {
    Context context;

    public CardLayoutAdapter(Context context, int layoutResourceId, List<Message> items) {
        super(context, layoutResourceId, items);
        this.context = context;
    }

    public class ViewHolder {
        ImageView image;
        TextView title;
        TextView description;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Message message = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_cardlayout, null);
            holder = new ViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.list_image);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.description = (TextView) convertView.findViewById(R.id.description);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.image.setImageResource(message.getImageId());
        holder.title.setText(message.getMessage());
        holder.description.setText(message.getDesc());

        return convertView;
    }
}


