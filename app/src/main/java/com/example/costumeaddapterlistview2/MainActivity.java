package com.example.costumeaddapterlistview2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
ListView listView;
ImageButton Add_note;
ActivityResultLauncher<Intent> activityResultLauncher;
CostumeAddapter addapter;
ArrayList<Note> arrayList;
Button delete;
Button Cancel;

int current_postion=-1;
int delete_button;
ActivityResultLauncher<Intent> activityResultLauncher1;
boolean flag=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.list_note);
        Add_note=findViewById(R.id.add_button);
        delete=findViewById(R.id.delete_button);
        Cancel=findViewById(R.id.cancel_button_delete);
        arrayList=new ArrayList<>();
        arrayList.add(new Note("s","sd"));
        addapter=new CostumeAddapter(this,R.layout.activity_modelview,arrayList);
        listView.setAdapter(addapter);
        activityResultLauncher=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        //getSerializableExtra("note"); btrje3lk object mn3ou3 serilizable so lezm ta3ml casting
                        if(o.getData()!=null){
                            Note note = (Note) o.getData().getSerializableExtra("note");
                            if (o.getResultCode() == RESULT_OK) {
                                addapter.AddNote(note);

                            }
                            else if(o.getResultCode()==RESULT_CANCELED){
                                addapter.editNote(current_postion,note);

                            }
                        }


                    }
                }
        );
        Intent intent=new Intent(getApplicationContext(),Write_note.class);


        Add_note.setOnClickListener(v->{
            flag=true;
            gotoactivitybuttonadd(intent);


        });





        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Note note=addapter.getItem(position);
                intent.putExtra("add_note",note);
                flag=false;
                current_postion=position;
                gotoactivitybuttonlist(intent);



            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                delete.setVisibility(View.VISIBLE);
                Cancel.setVisibility(View.VISIBLE);
                delete_button=position;
                return true;
            }
        });

        delete.setOnClickListener(v->{
            addapter.deltenote(delete_button);
            delete.setVisibility(View.GONE);
            Cancel.setVisibility(View.GONE);
        });

        Cancel.setOnClickListener(v->{
            delete.setVisibility(View.GONE);
            Cancel.setVisibility(View.GONE);
        });






    }

    public void gotoactivitybuttonadd(Intent intent1){
        intent1.putExtra("button",flag);
        if(intent1.resolveActivity(getPackageManager())!=null){
           activityResultLauncher.launch(intent1);
        }


    }



    public void gotoactivitybuttonlist(Intent intent1){
        intent1.putExtra("button",flag);
        if(intent1.resolveActivity(getPackageManager())!=null){
            activityResultLauncher.launch(intent1);
        }

    }
}