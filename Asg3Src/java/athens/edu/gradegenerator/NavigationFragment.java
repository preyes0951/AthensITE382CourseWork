package athens.edu.gradegenerator;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Iterator;

public class NavigationFragment extends Fragment {
    Button mPrevButton;
    Button mNextButton;

    //Fragment listener interface for communicating with GradeActivity
    //https://medium.com/groupon-eng/from-fragments-to-activity-the-lambda-way-32c768c72aa9
    public interface NavigationListener {
        public void nextPressed();
        public void prevPressed();
    }

    NavigationListener callback;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            callback = (NavigationListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement HeadlineListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.nav_view,container,false);
        mNextButton = (Button) v.findViewById(R.id.next_student);
        mPrevButton = (Button) v.findViewById(R.id.prev_student);

        mNextButton.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               //send message to activity
                                                callback.nextPressed();
                                           }
                                       }
        );

        mPrevButton.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               //send message to activity
                                               callback.prevPressed();
                                           }
                                       }
        );
        return v;
    }
}
