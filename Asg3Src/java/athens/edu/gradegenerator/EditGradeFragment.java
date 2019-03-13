package athens.edu.gradegenerator;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class EditGradeFragment extends Fragment {

    private Button saveBtn;
    private EditText newGradeTextView;

    SaveGradeListener callback;

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            callback = (SaveGradeListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement HeadlineListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_grade,container,false);
        saveBtn = (Button) v.findViewById(R.id.savegrade);
        newGradeTextView = (EditText) v.findViewById(R.id.newgrade);

        saveBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.savePressed(Integer.parseInt(newGradeTextView.getText().toString()));
            }
        });


        return v;
    }

    public interface SaveGradeListener {
        public void savePressed(int Grade);
    }
}
