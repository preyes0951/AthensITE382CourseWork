package athens.edu.gradegenerator;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomizeFragment extends Fragment {
    private Spinner studentEntryField;
    private EditText studentGradeField;
    private Button randBtn;

    private String studentName;
    private int studentGrade;
    private Cohort myCohort;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.random_view,container,false);

        studentEntryField = (Spinner) v.findViewById(R.id.student_name);
        studentGradeField = (EditText) v.findViewById(R.id.student_grade);
        randBtn = (Button) v.findViewById(R.id.random_button);
        myCohort = Cohort.getCohort();

        //spinner code courtesy of: https://www.tutorialspoint.com/android/android_spinner_control.htm
        studentEntryField.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                studentName = parent.getItemAtPosition(position).toString();
                studentGrade = myCohort.getStudentGrade(studentName);
                studentGradeField.setText(myCohort.getStudentGrade(studentName).toString());
            }
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        //listeners for grade field
        studentGradeField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_GO) {
                    updateText(v);
                }
                return false;
            }
        });
        studentGradeField.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN) {
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            updateText((TextView) v);
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });

        //event handler for random button
        randBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random rand = new Random();
                int randomGrade = rand.nextInt(101);
                myCohort.setGrade(studentName,randomGrade);
                studentGradeField.setText(Integer.toString(randomGrade));

            }
        });

        String firstStudent = myCohort.getAStudent();
        studentGradeField.setText(myCohort.getStudentGrade(firstStudent).toString());

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,myCohort.buildAList());
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        studentEntryField.setAdapter(dataAdapter);

        return v;
    }

    private void updateText(TextView textView) {
        String s = textView.getText().toString();
        Integer grade = Integer.parseInt(s);
        if (myCohort.contains(studentName)) {
            myCohort.setGrade(studentName, grade);
            Toast.makeText(getActivity(), R.string.strExistsWarning, Toast.LENGTH_LONG).show();
        } else {
            myCohort.addStudent(studentName, grade);
            Toast.makeText(getActivity(), R.string.strNotExistsAdded, Toast.LENGTH_LONG).show();
        }
    }
}
