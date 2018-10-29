package charstars.uscfit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PopUpInfoTest {

    UserInfo uInfo;
    PopUpInfo popUpInfo;

    @Before
    public void setUp() throws Exception {
        uInfo = new UserInfo();
        popUpInfo = new PopUpInfo();
    }

    @Test
    public void updateUserInfoAllEmptyFields() {
        Assert.assertFalse(popUpInfo.updateUserInfo(uInfo, "", "", ""));
    }

    @Test
    public void updateUserInfoOneEmptyField() {
        Assert.assertFalse(popUpInfo.updateUserInfo(uInfo, "", "1", "2"));
    }

    /* Note: with the current code, the following two tests are impossible, because the input keyboards
    are restricted to an int for age, and doubles for height and weight */
    @Test
    public void updateUserInfoStrings() {
        Assert.assertFalse(popUpInfo.updateUserInfo(uInfo, "a", "b", "c"));
    }

    @Test
    public void updateUserInfoInvalidNumbers() {
       Assert.assertFalse(popUpInfo.updateUserInfo(uInfo, "63.5", "200", "300"));
    }

    @Test
    public void updateUserInfoValidNumbers() {
        Assert.assertTrue(popUpInfo.updateUserInfo(uInfo, "100", "200", "300"));
        assertEquals(uInfo.getAge(), 100);
        assertEquals(uInfo.getHeight(), 200, 0.001); // add small epsilon
        assertEquals(uInfo.getWeight(), 300, 0.001);

        Assert.assertTrue(popUpInfo.updateUserInfo(uInfo, "5", "20.1", "20.5"));
        assertEquals(uInfo.getAge(), 5);
        assertEquals(uInfo.getHeight(), 20.1, 0.001); // add small epsilon
        assertEquals(uInfo.getWeight(), 20.5, 0.001);
    }
}