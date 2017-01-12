package com.example.android.appusagestatistics;

import com.example.android.appusagestatistics.AppUsageStatisticsFragment.StatsUsageInterval;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.content.Context;

import java.util.List;
import java.lang.Object;

/**
 * Created by rtfeng on 2017/1/12.
 */

public class AppUsageStatisticsTest
        extends ActivityInstrumentationTestCase2<AppUsageStatisticsActivity> {
    private AppUsageStatisticsActivity mTestActivity;
    private  AppUsageStatisticsFragment mTestFragment;

    public AppUsageStatisticsTest(Class<AppUsageStatisticsActivity> activityClass) {
        super(activityClass);
    }

//    public AppUsageStatisticsTest() { super (AppUsageStatisticsActivity.class); }

    protected  void setUp() throws Exception {
        super.setUp();

        mTestActivity = getActivity();
        mTestFragment = (AppUsageStatisticsFragment)
                mTestActivity.getSupportFragmentManager().getFragments().get(0);
    }

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
        final List<UsageStats> queryUsageStats
    }

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

    UsageStatsManager mUsageStatsManager = (UsageStatsManager);
    long time = System.currentTimeMillis();
    List<UsageStats> stats = mUsageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, time-1000*10, time);
    if(stats != null) {
        for(UsageStats usageStats : stats) {

        }
    }
}
