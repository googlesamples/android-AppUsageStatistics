/*
* Copyright 2014 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.appusagestatistics;

import com.example.android.appusagestatistics.AppUsageStatisticsFragment.StatsUsageInterval;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;

import java.util.List;

/**
 * Tests for {@link com.example.android.appusagestatistics.AppUsageStatisticsFragment}.
 */
public class AppUsageStatisticsFragmentTests
        extends ActivityInstrumentationTestCase2<AppUsageStatisticsActivity> {

    private AppUsageStatisticsActivity mTestActivity;

    private AppUsageStatisticsFragment mTestFragment;

    public AppUsageStatisticsFragmentTests() {
        super(AppUsageStatisticsActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        // Starts the activity under test using the default Intent with:
        // action = {@link Intent#ACTION_MAIN}
        // flags = {@link Intent#FLAG_ACTIVITY_NEW_TASK}
        // All other fields are null or empty.
        mTestActivity = getActivity();
        mTestFragment = (AppUsageStatisticsFragment)
                mTestActivity.getSupportFragmentManager().getFragments().get(0);
    }

    /**
     * Test if the test fixture has been set up correctly.
     */
    public void testPreconditions() {
        assertNotNull(mTestFragment.mUsageStatsManager);
        assertNotNull(mTestFragment.mUsageListAdapter);
        assertNotNull(mTestFragment.mRecyclerView);
        assertNotNull(mTestFragment.mLayoutManager);
        assertNotNull(mTestFragment.mOpenUsageSettingButton);
        assertNotNull(mTestFragment.mSpinner);
    }

    public void testStatsUsageInterval_getValue() {
        assertTrue(StatsUsageInterval.DAILY == StatsUsageInterval.getValue("Daily"));
        assertTrue(StatsUsageInterval.WEEKLY == StatsUsageInterval.getValue("Weekly"));
        assertTrue(StatsUsageInterval.MONTHLY == StatsUsageInterval.getValue("Monthly"));
        assertTrue(StatsUsageInterval.YEARLY == StatsUsageInterval.getValue("Yearly"));
        assertNull(StatsUsageInterval.getValue("NonExistent"));
    }

    public void testGetUsageStatistics() {
        List<UsageStats> usageStatsList = mTestFragment
                .getUsageStatistics(UsageStatsManager.INTERVAL_DAILY);

        // Whether the usageStatsList has any UsageStats depends on if the app is granted
        // the access to App usage statistics.
        // Only check non null here.
        assertNotNull(usageStatsList);
    }

    @UiThreadTest
    public void testUpdateAppsList() {
        List<UsageStats> usageStatsList = mTestFragment
                .getUsageStatistics(UsageStatsManager.INTERVAL_DAILY);
        mTestFragment.updateAppsList(usageStatsList);

        // The result depends on if the app is granted the access to App usage statistics.
        if (usageStatsList.size() == 0) {
            assertTrue(mTestFragment.mUsageListAdapter.getItemCount() == 0);
        } else {
            assertTrue(mTestFragment.mUsageListAdapter.getItemCount() > 0);
        }
    }
}
