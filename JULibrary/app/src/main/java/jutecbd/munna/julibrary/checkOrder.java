package jutecbd.munna.julibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class checkOrder extends AppCompatActivity {

    DatabaseReference databaseReference;
    private ListView listView;
    private List<OrderHandle> orderHandleList;
    CheckOrderAdapter checkOrderAdapter;
    String key;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_order);

        listView = findViewById(R.id.checkOrderListViewid);
        orderHandleList = new ArrayList<>();

        checkOrderAdapter = new CheckOrderAdapter(this, orderHandleList);
        databaseReference = firebaseDatabase.getReference("o");

        setTitle("Total Order");

        //Back Button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                OrderHandle selectedItem = orderHandleList.get(i);
                alertDialogBuilderRun(selectedItem);
            }
        });
    }

    @Override
    protected void onStart() {
        final StringBuffer stringBuffer = new StringBuffer();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                orderHandleList.clear();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                   OrderHandle orderHandle = dataSnapshot1.getValue(OrderHandle.class);
                   orderHandleList.add(orderHandle);
                }
                listView.setAdapter(checkOrderAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Toast.makeText(getApplicationContext(), databaseReference.getDatabase().toString(), Toast.LENGTH_SHORT).show();

        super.onStart();
    }


    public void alertDialogBuilderRun(final OrderHandle selectedItem){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Delevary is successful?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String key = selectedItem.getKey();
                        databaseReference.child(key).removeValue();
                        Toast.makeText(getApplicationContext(), "You successfully remove this item", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setTitle("Table");
        alertDialog.show();
        alertDialog.setIcon(R.drawable.test);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
