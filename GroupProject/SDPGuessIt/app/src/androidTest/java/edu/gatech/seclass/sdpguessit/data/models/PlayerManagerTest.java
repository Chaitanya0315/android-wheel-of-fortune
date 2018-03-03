package edu.gatech.seclass.sdpguessit.data.models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.gatech.seclass.sdpguessit.data.managers.PlayerManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Haohao on 3/1/18.
 */

@RunWith(AndroidJUnit4.class)
public class PlayerManagerTest {
    PlayerManager playerManager;

    @Before
    public void setUp() {
        playerManager = new PlayerManager(InstrumentationRegistry.getTargetContext());
    }

    @After
    public void tearDown() {
        playerManager = null;
    }

    @Test
    public void TestIfPlayerAdded () {
        playerManager.addNewPlayer("haohao", "wang", "hwang404", "hwang404@gatech.edu");
        assertNotNull(playerManager.getPlayerByUsername("hwang404"));
    }

    @Test
    public void TestIfUsernameExist() {
        playerManager.addNewPlayer("haohao", "wang", "hwang404", "hwang404@gatech.edu");
        assertEquals(playerManager.doesUsernameExist("hwang404"), true);
    }
}
