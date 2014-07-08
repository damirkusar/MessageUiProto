package ch.kusar.rochedevicemessages;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
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

        ListView lv = (ListView) findViewById(R.id.messageList);

        CardLayoutAdapter adapter = new CardLayoutAdapter(getApplicationContext(), R.layout.list_cardlayout, list);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(getApplicationContext(), MessageDetailActivity.class);
                intent.putExtra(DETAIL_MESSAGE, list.get(position));
                startActivity(intent);
            }
        });
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
