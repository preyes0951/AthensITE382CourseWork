package athens.edu.gradegenerator;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class StudentListFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView listView;
        RecyclerView.LayoutManager layoutManager;
        View rootView = (View) inflater.inflate(R.layout.studentlist,
                container,
                false);
        listView = (RecyclerView) rootView.findViewById(R.id.aStudentList);
        layoutManager = new LinearLayoutManager(getActivity());
        StudentAdapter adapter = new StudentAdapter(Cohort.getCohort().buildAList(),
                listView.getContext());
        listView.setAdapter(adapter);
        listView.setLayoutManager(layoutManager);
        return rootView;
    }
}
