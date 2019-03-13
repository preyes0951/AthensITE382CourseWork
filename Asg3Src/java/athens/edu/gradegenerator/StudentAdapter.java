package athens.edu.gradegenerator;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;


public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    private List<String> listOfStudentNames;
    private Context studentAdapterContext;

    ListItemListener callback;

    @Override
    public StudentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View studentListView = inflater.inflate(R.layout.studentlistrow, parent, false);
        ViewHolder viewHolder = new ViewHolder(studentListView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StudentAdapter.ViewHolder holder, int position) {
        String name = listOfStudentNames.get(position);
        TextView textView = holder.nameTextView;
        textView.setText(name);
        Button button = holder.editButton;
        button.setText(studentAdapterContext.getString(R.string.strEdit));
    }

    @Override
    public int getItemCount() {
        return listOfStudentNames.size();
    }

    public StudentAdapter(List<String> listOfStudentNames, Context studentAdapterContext) {
        this.listOfStudentNames = listOfStudentNames;
        this.studentAdapterContext = studentAdapterContext;
    }

    public Context getStudentAdapterContext() {
        return studentAdapterContext;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public Button editButton;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.listrowname);
            editButton = (Button) itemView.findViewById(R.id.editstudent);

            try {
                callback = (ListItemListener) itemView.getContext();
            } catch (ClassCastException e) {
                throw new ClassCastException(itemView.getContext().toString() + " must implement HeadlineListener");
            }

            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.editPressed(nameTextView.getText().toString());
                }
            });
        }
    }

    public interface ListItemListener {
        public void editPressed(String Name);
    }
}
