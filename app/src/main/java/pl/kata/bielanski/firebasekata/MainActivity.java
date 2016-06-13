package pl.kata.bielanski.firebasekata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
	private static final String DB_LINK = "https://glowing-torch-4628.firebaseio.com/";
	private static final String BUS_STOP_LIST = "BusStopList";

	@Bind(R.id.bus_stops_list)
	protected ListView busStopsListView;

	private Firebase mRootRef;
	private Firebase mBusStops;
	private ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		Firebase.setAndroidContext(this);
		mRootRef = new Firebase(DB_LINK);
		mBusStops = mRootRef.child(BUS_STOP_LIST);
		adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
		busStopsListView.setAdapter(adapter);

		mBusStops.addChildEventListener(new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot dataSnapshot, String s) {
				String busStop = dataSnapshot.getValue(String.class);
				adapter.add(busStop);
				adapter.notifyDataSetChanged();
			}

			@Override
			public void onChildChanged(DataSnapshot dataSnapshot, String s) {

			}

			@Override
			public void onChildRemoved(DataSnapshot dataSnapshot) {

			}

			@Override
			public void onChildMoved(DataSnapshot dataSnapshot, String s) {

			}

			@Override
			public void onCancelled(FirebaseError firebaseError) {

			}
		});
	}
}
