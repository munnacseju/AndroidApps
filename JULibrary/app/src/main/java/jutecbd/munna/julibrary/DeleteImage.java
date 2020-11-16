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
import android.widget.TextView;
import android.widget.Toast;

import jutecbd.munna.julibrary.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DeleteImage extends AppCompatActivity {

    private ProgressBar progressBar;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private ListView listView;
    private List<BookRegistrationHandle>bookRegistrationHandleList;
    private TestAdapter adapter;
    private String department;
    private FirebaseStorage firebaseStorage;
    private String key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_image);

        setTitle("Book List");


        firebaseStorage = FirebaseStorage.getInstance();
        department = getIntent().getStringExtra("depertment");


        databaseReference = firebaseDatabase.getReference(department);
        listView = findViewById(R.id.olympaidScoreBoardListViewid);
        bookRegistrationHandleList = new ArrayList<>();
        progressBar = findViewById(R.id.progressBarid);






        adapter = new TestAdapter(this, bookRegistrationHandleList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                BookRegistrationHandle selectedItem = bookRegistrationHandleList.get(i);
                key = selectedItem.getKey();

                StorageReference storageReference = firebaseStorage.getReferenceFromUrl(selectedItem.getImageDownloadUri());
                storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        databaseReference.child(key).removeValue();
                        Toast.makeText(getApplicationContext(), "The book is deleted from this list", Toast.LENGTH_SHORT).show();

                    }
                });



            }
        });

    }

    @Override
    protected void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                bookRegistrationHandleList.clear();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    BookRegistrationHandle bookRegistrationHandle = dataSnapshot1.getValue(BookRegistrationHandle.class);
                    bookRegistrationHandle.setKey(dataSnapshot1.getKey());
                    bookRegistrationHandleList.add(bookRegistrationHandle);
                }
                progressBar.setVisibility(View.GONE);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        super.onStart();
    }


}
