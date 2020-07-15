package com.vandenbreemen.corpuscles.otherstuff;

import com.vandenbreemen.corpuscles.CorpusclesData;
import com.vandenbreemen.corpuscles.Simulation;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class CorpuscleDataOverridingTest {

    @Test
    public void testOverridesOverriteData() {

        final AtomicBoolean overwriteCalled = new AtomicBoolean(false);
        CorpusclesData data = new CorpusclesData(10,10) {
            @Override
            protected void overwriteWith(CorpusclesData data) {
                super.overwriteWith(data);
                overwriteCalled.set(true);
            }
        };

        assertFalse(overwriteCalled.get());
        Simulation simulation = new Simulation(data);
        simulation.nextEpoch();

        assertTrue(overwriteCalled.get());


    }

}
