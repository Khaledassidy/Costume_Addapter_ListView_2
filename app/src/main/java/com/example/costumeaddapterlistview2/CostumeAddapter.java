package com.example.costumeaddapterlistview2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextClock;
import android.widget.TextView;

import java.util.ArrayList;

public class CostumeAddapter extends BaseAdapter {
    private ArrayList<Note> arrayList;
   private Context context;
    private int resource;

    public CostumeAddapter(Context context,int resource,ArrayList<Note> arrayList){
        this.context=context;
        this.resource=resource;
        this.arrayList=arrayList;
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Note getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view=convertView;
        if(view==null){
            view= LayoutInflater.from(this.context).inflate(this.resource,null,false);

        }

        TextClock textClock=view.findViewById(R.id.text_clock);
        TextView description=view.findViewById(R.id.description);
        CheckBox checkBox=view.findViewById(R.id.checkbox_check);
        TextView title=view.findViewById(R.id.title_note);
        TextView subtitle=view.findViewById(R.id.subtitle_list);
        Note note=getItem(position);

        textClock.setTimeZone(note.getTime());
        description.setText(note.getDescription());
        checkBox.setChecked(false);
        title.setText(note.getTitle());
        subtitle.setText(note.getSubtitle());
        return view;
    }

    public void AddNote(Note note){
        arrayList.add(note);
        notifyDataSetChanged();
    }

    public void editNote(int postion,Note note){
        arrayList.set(postion,note);
        notifyDataSetChanged();
    }

    public void deltenote(int position){
        arrayList.remove(position);
        notifyDataSetChanged();
    }
}
