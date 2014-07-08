package ch.kusar.rochedevicemessages.Services;

import android.app.Application;

/**
 * Created by ku5ar on 07.07.14.
 */
public class LoginService extends Application {

    public boolean ValidAuthorisation(String email, String password) {
        if (email.equals("Roche") && password.equals("Roche")){
            return true;
        }

        return true;
    }
}
