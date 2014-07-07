package ch.kusar.rochedevicemessages.tests.Services;

import android.test.InstrumentationTestCase;

import ch.kusar.rochedevicemessages.Services.LoginService;

/**
 * Created by ku5ar on 07.07.14.
 */
public class LoginServiceTest extends InstrumentationTestCase {
    private LoginService loginService;

    protected void setUp() throws Exception {
        super.setUp();

        this.loginService = new LoginService();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public final void test_ValidAuthorisation_NotValidWithEmailAndPasswordParameter() {
        assertEquals(false, this.loginService.ValidAuthorisation("email", "password"));
    }

    public final void test_ValidAuthorisation_ValidWith_RocheEmail_And_RochePassword() {
        assertEquals(true, this.loginService.ValidAuthorisation("Roche", "Roche"));
    }
}
