package ch.kusar.rochedevicemessages.Adapter;

import android.content.Context;
import android.util.SparseBooleanArray;
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
    List<Message> messages;
    private SparseBooleanArray selectedItemsIds;

    public CardLayoutAdapter(Context context, int layoutResourceId, List<Message> items) {
        super(context, layoutResourceId, items);
        this.context = context;
        this.messages = items;
        this.selectedItemsIds = new SparseBooleanArray();
    }

    public class ViewHolder {
        ImageView image;
        TextView message;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Message message = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null && !message.isConfirmed()) {
            convertView = mInflater.inflate(R.layout.list_cardlayout, null);
            holder = new ViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.overview_image);
            holder.message = (TextView) convertView.findViewById(R.id.overview_message);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.image.setImageResource(message.getImageId());
        holder.message.setText(message.getMessage());

        return convertView;
    }

    @Override
    public void remove(Message object) {
        // super.remove(object);
        messages.remove(object);
        notifyDataSetChanged();
    }

    public void toggleSelection(int position) {
        selectView(position, !selectedItemsIds.get(position));
    }

    public void selectView(int position, boolean value) {
        if (value)
            selectedItemsIds.put(position, value);
        else
            selectedItemsIds.delete(position);

        notifyDataSetChanged();
    }

    public int getSelectedCount() {
        return this.selectedItemsIds.size();
    }

    public SparseBooleanArray getSelectedIds() {
        return this.selectedItemsIds;
    }

    public void removeSelection() {
        this.selectedItemsIds = new SparseBooleanArray();
        notifyDataSetChanged();
    }

    public void removeAll() {
        messages.clear();
        notifyDataSetChanged();
    }
}


