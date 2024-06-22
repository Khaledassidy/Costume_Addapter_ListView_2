package com.example.costumeaddapterlistview2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.io.IOException;
import java.util.ArrayList;

public class Write_note extends AppCompatActivity {
    ImageButton save, share, gallary, takepic,back_button;
    EditText Title_note, Description_note,subtitle;
    TextView Title_of_application;
    ImageView imageView;
    ActivityResultLauncher<String> activityResultLauncher;

    ActivityResultLauncher<Intent> activityResultLauncher1;
    boolean flag=true;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_note);
        save = findViewById(R.id.save);
        share = findViewById(R.id.share);
        gallary = findViewById(R.id.gallary);
        takepic = findViewById(R.id.take_pic);
        Title_note = findViewById(R.id.title_for_note);
        Description_note = findViewById(R.id.description_of_note);
        Title_of_application = findViewById(R.id.title_app);
        subtitle=findViewById(R.id.subtitle);
        back_button=findViewById(R.id.back);

        Intent intent1 = getIntent();
        if(intent1!=null) {
            Note note1 = (Note) intent1.getSerializableExtra("add_note");
            flag = (boolean) intent1.getBooleanExtra("button", true);
            if (note1 != null) {
                Title_note.setText(note1.getTitle());
                Description_note.setText(note1.getDescription());

            }
        }




        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri o) {
                        imageView=findViewById(R.id.image_2);

                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), o);
                            imageView.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                        imageView.setVisibility(View.VISIBLE);




                    }
                }
        );


        activityResultLauncher1 = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        imageView=findViewById(R.id.image_2);
                        Bitmap bitmap=(Bitmap) o.getData().getExtras().get("data");
                        imageView.setImageBitmap(bitmap);
                        imageView.setVisibility(View.VISIBLE);




                    }
                }
        );


        save.setOnClickListener(v -> {
            Intent intent = new Intent(getBaseContext(), MainActivity.class);

                String title = Title_note.getText().toString();
                String description = Description_note.getText().toString();

                //hala2 bade raje3 data lal main activity mn 5elel l intent
                //badel ma 2a3ml put extra kaza mara 3ande she esmo put extra w bte5od she esmo seriallizable
                //seriallizable:heye 3amlyet ta7wel l object la text la nous
                //fa enta l mafroud te5od data le 3andak l title,description w bt7oto b object w ba3den t7awel l object la text la nous w teb3ato 3al activity l main ka pachage wa7de ka nous wa7ad

                //fa 2awl ba3ml object mn no3 note w 3abe fe l data title w l description
                Note note = new Note(title, description);

                //hala2 heed l object keef badak traj3o lal main activity mn 5elel l intent mn 5elle l put extra bas houn badak teb3at serililizable
                //bas houn 3atak 5ata2 lesh?
                //3ashen enta teb3at l object 2aw 3ashen tet3emal ma3 l object 3ala shakl nas text te2dar terslo w tes2blo 2aw ne7na mn sameha serializable
                //3ashn te2dar ta3mlo serialization enak t7alo la shakl serial ya3ne text lezm l class nafso l note ykoun mohya2 la heda she
                //3aeehn l object 2e2dar et3emal ma3o ka serialization 2aw betre2t serialization eno eb3ato 3ala shakl object been l activity lezm l object ykoun mohy2a lal 3amlye hay
                //keef b5ale mohy2a la heda she 2alak fe interface esmo serializable
                //kol le 3leek trou7 3ala note w ta3ml implement la heda l inferface
                //ya3ne public calls note implimnt serializable
                //Note implements Serializable :bas to7toblo hal jomle ka2no 2oltelo eno class l note  la ykoun demneyan fe 5asa2es l serializable ya3ne ka2no tab2t la class l note 5asyet l serializable eno ye2dar y7awlo eno ye2dar y7wlo ka sting w yeb3to w bas yest2lo yest2blo ka object ya3ne metl ma b3ato eno note 7a tes2blo note enta ma 2elak da3we keef saret serialization w keef 7awlo 3end l erserl la nous w 3end l este2bel reje3 7awlo la object
                intent.putExtra("note", note);
                intent.putExtra("wich_bottm",flag);
                setResult(RESULT_OK, intent);
                if(flag==true){
                    setResult(RESULT_OK, intent);
            }
            else{
                    setResult(RESULT_CANCELED,intent);

            }
            finish();

        });


        gallary.setOnClickListener(v -> {

            activityResultLauncher.launch("image/*");


        });


        takepic.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
            if (intent.resolveActivity(getPackageManager()) != null) {
                activityResultLauncher1.launch(intent);
            }


        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_SEND);
                String x=Title_note.getText().toString()+" "+Description_note.getText().toString()+" "+subtitle.getText().toString();
                intent.putExtra(Intent.EXTRA_TEXT,x);
                intent.setType("text/plain");
                startActivity(intent);



            }
        });

        back_button.setOnClickListener(v->{
            finish();
        });

    }
}




