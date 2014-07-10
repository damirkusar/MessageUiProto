package ch.kusar.rochedevicemessages;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import ch.kusar.rochedevicemessages.Models.Message;
import ch.kusar.rochedevicemessages.R;

public class MessageDetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_detail);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        Message message = (Message) intent.getExtras().getSerializable(MessageOverviewActivity.DETAIL_MESSAGE);

        this.fillDataForLayout(message);
    }

    private void fillDataForLayout(Message messageObject) {
        ImageView image = (ImageView)findViewById(R.id.detail_image);
        TextView message = (TextView) findViewById(R.id.detail_message);
        TextView description = (TextView) findViewById(R.id.detail_description);
        TextView severity = (TextView) findViewById(R.id.detail_severity);
        TextView date = (TextView) findViewById(R.id.detail_date);

        image.setImageResource(messageObject.getImageId());
        message.setText(messageObject.getMessage());
        description.setText(messageObject.getDesc());
        severity.setText(messageObject.getSeverity());
        date.setText(messageObject.getDate());
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_refresh) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
