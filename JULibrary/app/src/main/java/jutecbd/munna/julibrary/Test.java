package jutecbd.munna.julibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import jutecbd.munna.julibrary.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Test extends AppCompatActivity {

    private Spinner yearSpanner;
    private ProgressBar progressBar;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private ListView listView;
    private List<BookRegistrationHandle>bookRegistrationHandleList;
    private TestAdapter adapter;
    private String department;
    private String[] yearList = {"1st Year", "2nd Year", "3rd Year", "4th Year", "Masters"};
    private String year;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        setTitle("Book List");

        //Back Button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        department = getIntent().getStringExtra("depertment");


        listView = findViewById(R.id.olympaidScoreBoardListViewid);
        bookRegistrationHandleList = new ArrayList<>();
        progressBar = findViewById(R.id.progressBarid);
        yearSpanner = findViewById(R.id.selectYearSpinnerid);




        final ArrayAdapter<String> yearListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, yearList);
        yearSpanner.setAdapter(yearListAdapter);


        yearSpanner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                year = yearList[i];
                showData();
                Toast.makeText(getApplicationContext(), year+" selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getApplicationContext(), "Select None", Toast.LENGTH_SHORT).show();

            }
        });



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), Order.class);
                intent.putExtra("name", bookRegistrationHandleList.get(i).getName());
                intent.putExtra("price", bookRegistrationHandleList.get(i).getPrice());
                intent.putExtra("imageUri", bookRegistrationHandleList.get(i).getImageDownloadUri());
                intent.putExtra("id", bookRegistrationHandleList.get(i).getBookId());
                startActivity(intent);
            }
        });

        findViewById(R.id.deleteImageButtonid).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DeleteImage.class);
                intent.putExtra("depertment", department+year);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void alertDialogBuilderRun(String string){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(string)
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setTitle("No Item!");
        alertDialog.show();
        alertDialog.setIcon(R.drawable.test);
    }

    public void showData(){
        databaseReference = firebaseDatabase.getReference(department.trim()+year.trim());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                bookRegistrationHandleList.clear();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    BookRegistrationHandle bookRegistrationHandle = dataSnapshot1.getValue(BookRegistrationHandle.class);
                    bookRegistrationHandleList.add(bookRegistrationHandle);
                }
                if(bookRegistrationHandleList.isEmpty()){
                    alertDialogBuilderRun("This page has no content. Do you want to back?");

                }

                progressBar.setVisibility(View.GONE);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        adapter = new TestAdapter(this, bookRegistrationHandleList);
    }
}
