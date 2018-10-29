package charstars.uscfit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginActivityTest {
    LoginActivity la;

    @Before
    public void setUp() throws Exception {
        la = new LoginActivity();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void emptyEmail() {
        Assert.assertTrue(la.fieldsAreEmpty("", "password"));
    }

    @Test
    public void emptyPassword() {
        Assert.assertTrue(la.fieldsAreEmpty("alina@alina.com", ""));

    }

    @Test
    public void emptyBoth() {
        Assert.assertTrue(la.fieldsAreEmpty("", ""));

    }

    @Test
    public void validBoth() {
        Assert.assertFalse(la.fieldsAreEmpty("alina@alina.com", "alinaalina"));

    }

}