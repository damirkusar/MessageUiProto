package ch.kusar.rochedevicemessages;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import ch.kusar.rochedevicemessages.Services.LoginService;

/**
 * Created by ku5ar on 07.07.14.
 */
public class LoginActivity extends Activity {

    public LoginService loginService;

    public LoginActivity(){
        this.loginService = new LoginService();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AutoCompleteTextView email = (AutoCompleteTextView) findViewById(R.id.email);
                final String emailValue = email.getText().toString();

                TextView password = (TextView) findViewById(R.id.password);
                final String passwordValue = password.getText().toString();

                if(loginService.ValidAuthorisation(emailValue, passwordValue)){
                    Toast.makeText(getApplicationContext(), "Logged In", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MessageOverviewActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Password combination not Valid. Try Again.", Toast.LENGTH_SHORT).show();
                }
            }
        });
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

