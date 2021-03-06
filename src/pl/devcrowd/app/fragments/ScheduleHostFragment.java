package pl.devcrowd.app.fragments;

import pl.devcrowd.app.R;
import pl.devcrowd.app.activities.MainActivity;
import pl.devcrowd.app.utils.DebugLog;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;

public class ScheduleHostFragment extends Fragment implements
		OnTabChangeListener {
	private FragmentTabHost mTabHost;

	private OnShowFragment onShowFragment;

	private int fragmentPosition;

	private static final String TEMP_ROOM_NAME_1 = "Sala 126";
	private static final String TEMP_ROOM_NAME_2 = "Sala 226";

	public static final String ROOM_NUMBER = "room";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mTabHost = new FragmentTabHost(getActivity());
		mTabHost.setOnTabChangedListener(this);

		mTabHost.setup(getActivity(), getChildFragmentManager(),
				R.id.frame_container);

		Bundle room1 = new Bundle();
		room1.putString(ROOM_NUMBER, "126");
		Bundle room2 = new Bundle();
		room2.putString(ROOM_NUMBER, "226");

		mTabHost.getTabWidget().setBackgroundColor(
				getResources().getColor(R.color.tabhostBg));
		mTabHost.addTab(
				mTabHost.newTabSpec(TEMP_ROOM_NAME_1).setIndicator(
						TEMP_ROOM_NAME_1), ScheduleListFragment.class, room1);
		mTabHost.addTab(
				mTabHost.newTabSpec(TEMP_ROOM_NAME_2).setIndicator(
						TEMP_ROOM_NAME_2), ScheduleListFragment.class, room2);
		resetTabsView();
		selectCurrentTab();
		fragmentPosition = getArguments().getInt(
				MainActivity.FRAGMENT_DRAWER_POSITION);
		return mTabHost;
	}

	@Override
	public void onTabChanged(String tabId) {
		resetTabsView();
		selectCurrentTab();
	}

	private void selectCurrentTab() {
		mTabHost.getCurrentTabView().setBackgroundResource(
				R.drawable.tab_background);
		TextView title = (TextView) mTabHost.getCurrentTabView().findViewById(
				android.R.id.title);
		title.setTextColor(getResources().getColor(
				R.color.drawer_list_title_color));
	}

	private void resetTabsView() {
		for (int i = 0; i < mTabHost.getTabWidget().getChildCount(); i++) {
			mTabHost.getTabWidget()
					.getChildAt(i)
					.setBackgroundResource(R.drawable.tab_background_unselected);
			TextView title = (TextView) mTabHost.getTabWidget().getChildAt(i)
					.findViewById(android.R.id.title);
			title.setTextColor(getResources().getColor(
					R.color.drawer_list_title_color));
		}
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		mTabHost = null;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			onShowFragment = (OnShowFragment) activity;
		} catch (ClassCastException e) {
			String message = activity.toString()
					+ " must implement OnShowFragment";
			DebugLog.e(message);
			throw new IllegalArgumentException(message);
		}
	}

	@Override
	public void onStart() {
		super.onStart();
		onShowFragment.onFragmentShowed(fragmentPosition);
	}
	
	

}
