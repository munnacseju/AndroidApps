package jutecbd.munna.julibrary;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class BookRegistration extends AppCompatActivity implements View.OnClickListener {

    ProgressBar progressBar;
    EditText  writerEditText;
    Button  registrationButton;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    Button profileImageSelectorButton;
    ImageView profileImageView;
    private Uri imageUri;
    private static final int IMAGE_REQUEST=1;
    private Boolean imageSelect = false;
    private Spinner departMentSpinner, yearSpanner;
    StorageReference storageReference;
    TextView registrationTextView;
    EditText langaugeEditText, priceEditText, nameEditText, vertiontionEditText, bookIdEditText;
    String[] departmentList = {"CSE", "Math", "Statistics", "Physics", "Chemistry", "Env", "GS",
            "Economics", "URP", "Anthopology","Geography", "PA","GP",
            "Botany", "Biochemistry", "Zoology", "Pharmacy", "Microbiology", "Biotechnology", "PHI",
            "Philosophy","English", "Histry", "Archaeology", "Bangla", "Journalism", "Drama", "FineArts", "IR",
            "Low",
            "Finance", "Accounting", "Management", "Marketing",
            "IBA", "IIT", "BICLC", "IRSG"};

    String[] yearList = {"1st Year", "2nd Year", "3rd Year", "4th Year", "Masters"};
    String year;
    String department;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_registration);

        SharedPreferences sharedPreferences= getSharedPreferences("julibrary", Context.MODE_PRIVATE);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        progressBar = findViewById(R.id.progressBarid);
        storageReference = FirebaseStorage.getInstance().getReference("booklist");

        registrationTextView = findViewById(R.id.registrationTextViewid);
        langaugeEditText = findViewById(R.id.languageEditTextid);
        priceEditText = findViewById(R.id.priceEditTextid);
        nameEditText = findViewById(R.id.nameEditTextid);
        vertiontionEditText = findViewById(R.id.versionEditTextid);
        profileImageSelectorButton = findViewById(R.id.profileImageSelectorButtonid);
        profileImageView = findViewById(R.id.profileImageid);
        bookIdEditText = findViewById(R.id.bookIdEditTextid);

        yearSpanner = findViewById(R.id.selectYearSpinnerid);
        departMentSpinner = findViewById(R.id.selectdepartmentSpinnerid);
        writerEditText = findViewById(R.id.writerEditTextid);
        registrationButton = findViewById(R.id.registrationButtonid);
        registrationButton.setOnClickListener(this);
        profileImageSelectorButton.setOnClickListener(this);
        setTitle("Book Registration");

        if(sharedPreferences.getString("bookid","").equals("")){
            id=1;
            bookIdEditText.setText(id+"");
        }
        else {
            int id = Integer.parseInt((sharedPreferences.getString("bookid", "")));
            id++;
            bookIdEditText.setText(id+"");
        }

        ArrayAdapter<String> departmentListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, departmentList);
        departMentSpinner.setAdapter(departmentListAdapter);

        final ArrayAdapter<String> yearListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, yearList);
        yearSpanner.setAdapter(yearListAdapter);

        departMentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                department = departmentList[i];
                Toast.makeText(getApplicationContext(), department+" Selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getApplicationContext(), "You select None", Toast.LENGTH_SHORT).show();
            }

        });

        yearSpanner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                year = yearList[i];
                Toast.makeText(getApplicationContext(), year+" selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getApplicationContext(), "Select None", Toast.LENGTH_SHORT).show();

            }
        });
    }


    @Override
    public void onClick(View view) {



        if(view.getId()==R.id.registrationButtonid){
            if(imageSelect==false){
                Toast.makeText(getApplicationContext(), "You must select a profile image", Toast.LENGTH_SHORT).show();
                return;
            }
            progressBar.setVisibility(View.VISIBLE);
            saveImage();

        }
        else if(view.getId()==R.id.profileImageSelectorButtonid){
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, IMAGE_REQUEST);
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == IMAGE_REQUEST && resultCode == RESULT_OK && data!=null && data.getData()!=null){
            imageUri = data.getData();
            Picasso.with(this).load(imageUri).into(profileImageView);
            profileImageView.setVisibility(View.VISIBLE);

        }
        imageSelect = true;
    }

    //Get File Extention
    public String getFileExtention(Uri imageUri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(imageUri));
    }

    public void saveImage(){

        //Save Image

        StorageReference storageReference1 = storageReference.child(System.currentTimeMillis()+"."+getFileExtention(imageUri));

        storageReference1.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                databaseReference = firebaseDatabase.getReference(department.trim()+year.trim());

                String name, language;
                name = nameEditText.getText().toString().trim();
                language = langaugeEditText.getText().toString().trim();
                String price = (priceEditText.getText().toString().trim());
                String version = (vertiontionEditText.getText().toString().trim());
                String writerName = writerEditText.getText().toString().trim();
                String bookid = (bookIdEditText.getText().toString().trim());


                SharedPreferences sharedPreferences = getSharedPreferences("julibrary", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("bookid",bookid);
                editor.commit();


                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isSuccessful());
                Uri imageDownloadUri = uriTask.getResult();

                BookRegistrationHandle handle = new BookRegistrationHandle( bookid, name, writerName, department, language, imageDownloadUri.toString(), price, version);
                String key = databaseReference.push().getKey();
                databaseReference.child(key).setValue(handle);

                Toast.makeText(getApplicationContext(), "You Successfully Registerd!", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);


                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                finish();
                startActivity(intent);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Sorry! "+e, Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
