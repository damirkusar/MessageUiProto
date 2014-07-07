package ch.kusar.rochedevicemessages;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
    private MessageService messageService;

    public MessageOverviewActivity(){
        this.messageService = new MessageService();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_overview);

        ActionBar actionBar = getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#33b5e5")));

        ListView lv = (ListView) findViewById(R.id.messageList);
        final ArrayList<Message> list = messageService.getMessages();

        CardLayoutAdapter adapter = new CardLayoutAdapter(getApplicationContext(), R.layout.list_cardlayout, list);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(), "You Clicked at " + list.get(position).getMessage(), Toast.LENGTH_SHORT).show();
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
