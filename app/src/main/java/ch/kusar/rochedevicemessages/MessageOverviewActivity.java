package ch.kusar.rochedevicemessages;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import ch.kusar.rochedevicemessages.Adapter.CardLayoutAdapter;
import ch.kusar.rochedevicemessages.Models.Message;
import ch.kusar.rochedevicemessages.R;
import ch.kusar.rochedevicemessages.Services.MessageService;

public class MessageOverviewActivity extends Activity {
    public static final String DETAIL_MESSAGE = "DETAIL_MESSAGE";

    private MessageService messageService;
    private ArrayList<Message> list;
    private CardLayoutAdapter cardLayoutAdapter;

    private ActionMode mActionMode;

    public MessageOverviewActivity() {
        this.messageService = new MessageService();
        this.list = messageService.getMessages();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_overview);

        ActionBar actionBar = getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#33b5e5")));

        getActionBar().setDisplayHomeAsUpEnabled(true);

        ListView lv = (ListView) findViewById(R.id.messageList);

        this.cardLayoutAdapter = new CardLayoutAdapter(getApplicationContext(), R.layout.list_cardlayout, list);
        lv.setAdapter(this.cardLayoutAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(getApplicationContext(), MessageDetailActivity.class);
                intent.putExtra(DETAIL_MESSAGE, list.get(position));
                startActivity(intent);
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent,
                                           View view, int position, long id) {
                onListItemSelect(position);
                return true;
            }

        });
    }

    private void onListItemSelect(int position) {
        this.cardLayoutAdapter.toggleSelection(position);
        boolean hasCheckedItems = this.cardLayoutAdapter.getSelectedCount() > 0;

        if (hasCheckedItems && mActionMode == null)
            // there are some selected items, start the actionMode
            mActionMode = startActionMode(new ActionModeCallback());
        else if (!hasCheckedItems && mActionMode != null)
            // there no selected items, finish the actionMode
            mActionMode.finish();

        if (mActionMode != null)
            mActionMode.setTitle(String.valueOf(this.cardLayoutAdapter
                    .getSelectedCount()) + " selected");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_removeAll) {
            this.cardLayoutAdapter.removeAll();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class ActionModeCallback implements ActionMode.Callback {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // inflate contextual menu
            mode.getMenuInflater().inflate(R.menu.context_menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

            switch (item.getItemId()) {
                case R.id.menu_delete:
                    // retrieve selected items and delete them out
                    SparseBooleanArray selected = cardLayoutAdapter.getSelectedIds();
                    for (int i = (selected.size() - 1); i >= 0; i--) {
                        if (selected.valueAt(i)) {
                            Message selectedItem = cardLayoutAdapter
                                    .getItem(selected.keyAt(i));
                            cardLayoutAdapter.remove(selectedItem);
                        }
                    }
                    mode.finish(); // Action picked, so close the CAB
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            // remove selection
            cardLayoutAdapter.removeSelection();
            mActionMode = null;
        }
    }
}


