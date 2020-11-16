package jutecbd.munna.julibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Cart extends AppCompatActivity {
    private boolean flag = true;
    private ListView listView;
    private List<cartHandle> cartHandleList;
    private CartAdapter adapter;
    private MyDatabasehelper myDatabasehelper = new MyDatabasehelper(this);
    private AlertDialog.Builder alertDialogBuilder;
    private DatabaseReference databaseReference;
    private int totalPrice = 0;
    private String[] paymentList = { "bKash", "Rocket", "Nogot", "Kash On Delivary"};
    private String payment;
    private Spinner paymentSpinner;
    private EditText accountNumberEditText, transectionIdEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        databaseReference = FirebaseDatabase.getInstance().getReference("o") ;

        //Back Button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);




        setTitle("My Cart");

        cartHandleList = new ArrayList<>();
        listView = findViewById(R.id.listViewid);
        adapter = new CartAdapter(this, cartHandleList);
        paymentSpinner = findViewById(R.id.cartPaymentMethodSpinner);
        accountNumberEditText = findViewById(R.id.cartBkashAccountNoEditTextId);
        transectionIdEditText = findViewById(R.id.cartTransectionNoEditTextId);

        ArrayAdapter<String> departmentListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, paymentList);
        paymentSpinner.setAdapter(departmentListAdapter);


        paymentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                payment = paymentList[i];

                if(payment.equals("Kash On Delivary")){
                    findViewById(R.id.cartPaymentMethodLinearLayoutId).setVisibility(View.GONE);
                }
                else {
                    findViewById(R.id.cartPaymentMethodLinearLayoutId).setVisibility(View.VISIBLE);
                }

                Toast.makeText(getApplicationContext(), payment+" Selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getApplicationContext(), "You select None", Toast.LENGTH_SHORT).show();
            }

        });

        findViewById(R.id.cartOrderButtonid).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = false;
                Cursor cursor = myDatabasehelper.showData();
                if(cursor.getCount() ==0){
                    Toast.makeText(getApplicationContext(), "Sorry, Your cart is empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                totalPrice = 0;
                while (cursor.moveToNext()){
                    int pricesCount = Integer.parseInt(cursor.getString(3));
                    int itemCount = Integer.parseInt(cursor.getString(2));
                    totalPrice += pricesCount*itemCount;
                }


                alertDialogBuilderRun();


            }
        });

        findViewById(R.id.cartConformOrderButtonId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = true;
                makeOrder();
            }
        });
    }



    @Override
    protected void onStart() {

        Cursor cursor1 = myDatabasehelper.showData();
        cartHandleList.clear();
        while (cursor1.moveToNext()){
            cartHandle cartHandle = new cartHandle();

            cartHandle.setId(cursor1.getString(0));
            cartHandle.setName(cursor1.getString(1));
            cartHandle.setItemCount(cursor1.getString(2));
            cartHandle.setPrice(cursor1.getString(3));

            cartHandleList.add(cartHandle);
        }
        listView.setAdapter(adapter);
        super.onStart();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            if(flag == false){
                alertDialogBuilderRunToFinish();
            }
            else {
                finish();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(flag == false){
            alertDialogBuilderRunToFinish();
        }
        else{
            finish();
        }
    }

    public void alertDialogBuilderRun(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Your Total Price is "+totalPrice+"\nDo you want to conform your order?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        makePayment();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setTitle("Order");
        alertDialog.show();
        alertDialog.setIcon(R.drawable.test);
    }


    public void alertDialogBuilderRunToFinish(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Your order is not compleate. \nDo you want to back without completing order?")
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
        alertDialog.setTitle("Order Incompleate!");
        alertDialog.show();
        alertDialog.setIcon(R.drawable.test);
    }

    public void alertDialogBuilderRunToCompleteOrder(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Congratulations!\nYour order is successful.\nYou can cancel your order by calling 01999236811 within 2 hour")
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setTitle("Order Incompleate!");
        alertDialog.show();
        alertDialog.setIcon(R.drawable.test);
    }


    public void makePayment(){
        findViewById(R.id.cartOrderButtonid).setVisibility(View.GONE);
        findViewById(R.id.cartLinierLayoutId).setVisibility(View.VISIBLE);
        listView.setVisibility(View.GONE);

    }

    public void makeOrder(){

        Cursor cursor1 = myDatabasehelper.showData();
        SharedPreferences sharedPreferences = getSharedPreferences("julibrary", Context.MODE_PRIVATE);
        String phoneNumber = sharedPreferences.getString("phone", "");
        String name = sharedPreferences.getString("name", "");
        String department = sharedPreferences.getString("department", "");
        String hall = sharedPreferences.getString("hall", "")+sharedPreferences.getString("batch", "");
        payment += "\nAcc No: "+ accountNumberEditText.getText().toString().trim()+"\n Tx Id: "+transectionIdEditText.getText().toString().trim();

        String bookids="", bookNames="", iterNumber="", prices="";

        int i=1;
        while (cursor1.moveToNext()){

            if(i!=1){
                bookids+=", ";
                bookNames+=", ";
                iterNumber+=", ";
                prices+=", ";
            }
            i++;
            bookids += cursor1.getString(0);
            bookNames  += cursor1.getString(1);
            iterNumber  += cursor1.getString(2);
            prices  += cursor1.getString(3);

            myDatabasehelper.deleteDAta(cursor1.getString(0));
        }

        String key = databaseReference.push().getKey();
        OrderHandle orderHandle = new OrderHandle(name, phoneNumber, department, hall,payment, bookids, bookNames, iterNumber, prices, totalPrice+"", key);
        databaseReference.child(key).setValue(orderHandle);

        finish();
        Toast.makeText(getApplicationContext(), "Order Successful", Toast.LENGTH_SHORT).show();
    }

}
