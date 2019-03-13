package athens.edu.gradegenerator;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class GradeActivity extends AppCompatActivity implements NavigationFragment.NavigationListener,
        StudentAdapter.ListItemListener, EditGradeFragment.SaveGradeListener {

    private Fragment dynamic_fragment;
    private ArrayList<Fragment> fragmentList;
    private ListIterator currentFragment;
    private Cohort myChort;
    private String editName;

    /**
     * BEGIN FRAGMENT COMMUNICATION *********************************************************
     */

    //implement NavigationListener interface for fragment communication
    public void nextPressed() {
        FragmentManager fm = getFragmentManager();
        if(dynamic_fragment != null) {
            fm.beginTransaction().remove(dynamic_fragment).commit();
        }
        if(currentFragment.hasNext()) {
            dynamic_fragment = (Fragment) currentFragment.next();
        }
        else {
            currentFragment = fragmentList.listIterator();
            dynamic_fragment = (Fragment) currentFragment.next();
        }
        fm.beginTransaction()
                .add(R.id.fragment_container_grade,dynamic_fragment)
                .commit();
    }
    public void prevPressed() {
        FragmentManager fm = getFragmentManager();
        if(dynamic_fragment != null) {
            fm.beginTransaction().remove(dynamic_fragment).commit();
        }
        if(currentFragment.hasPrevious()) {
            dynamic_fragment = (Fragment) currentFragment.previous();
        }
        else {
            while(currentFragment.hasNext()){
                dynamic_fragment = (Fragment) currentFragment.next();
            }
            currentFragment.previous();
        }
        fm.beginTransaction()
                .add(R.id.fragment_container_grade,dynamic_fragment)
                .commit();

    }
    //studentItemList listener
    @Override
    public void editPressed(String Name) {
        editName = Name;
        Toast.makeText(this, Name, Toast.LENGTH_LONG).show();
        FragmentManager fm = getFragmentManager();
        Fragment editFragment = fm.findFragmentById(R.id.fragment_new_grade);
        if (editFragment != null){
            fm.beginTransaction().remove(editFragment).commit();
        }
        editFragment = (Fragment) new EditGradeFragment();
        fm.beginTransaction()
                .add(R.id.fragment_new_grade,editFragment)
                .commit();
    }
    //saveGradeListener
    @Override
    public void savePressed(int Grade) {
        myChort.setGrade(editName,Grade);
        FragmentManager fm = getFragmentManager();
        Fragment editFragment = fm.findFragmentById(R.id.fragment_new_grade);
        if (editFragment != null){
            fm.beginTransaction().remove(editFragment).commit();
        }
    }

    /**
     * END FRAGMENT COMMUNICATION ************************************************************
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myChort = Cohort.getCohort();
        fragmentList = new ArrayList<>();
        fragmentList.add(new StudentMasterFragment());
        fragmentList.add(new StudentListFragment());
        fragmentList.add(new RandomizeFragment());
        currentFragment =  fragmentList.listIterator();

        FragmentManager fm = getFragmentManager();
        dynamic_fragment = fm.findFragmentById(R.id.fragment_container_grade);
        if (dynamic_fragment == null) {
            dynamic_fragment = (Fragment) currentFragment.next();
            fm.beginTransaction()
                    .add(R.id.fragment_container_grade,dynamic_fragment)
                    .commit();
        }
        Fragment fragment_nav = fm.findFragmentById(R.id.fragment_container_nav);
        if (fragment_nav == null) {
            fragment_nav = new NavigationFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container_nav,fragment_nav)
                    .commit();
        }
    }
}
