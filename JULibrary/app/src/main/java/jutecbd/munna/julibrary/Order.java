package jutecbd.munna.julibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class Order extends AppCompatActivity implements View.OnClickListener {

    private String name, price, imageUri, id;
    AlertDialog.Builder alertDialogBuilder;
    private ImageView imageView;
    private ImageView cartImageView;
    private TextView countTextView, plusTextView, minusTextView;
    private int itemCount = 1;
    TextView orderPriceTextView;

    MyDatabasehelper myDatabasehelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        setTitle("Order");
        //BackButton
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        countTextView = findViewById(R.id.countTextViewid);
        plusTextView = findViewById(R.id.plusTextViewid);
        minusTextView = findViewById(R.id.minusTextViewid);
        orderPriceTextView = findViewById(R.id.orderPriceTextViewid);

        countTextView.setText(itemCount+"");

        myDatabasehelper = new MyDatabasehelper(Order.this);
        myDatabasehelper.getWritableDatabase();
        SharedPreferences sharedPreferences = getSharedPreferences("julibrary", Context.MODE_PRIVATE);

        imageView = findViewById(R.id.orderImageViewid);
        cartImageView = findViewById(R.id.cartImageViewid);


        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        price = getIntent().getStringExtra("price");
        imageUri = getIntent().getStringExtra("imageUri");

        orderPriceTextView.setText("Price: "+price+"tk");

         Picasso.with(this)
                 .load(imageUri)
                 .placeholder(R.drawable.loading_image)
                 .into(imageView);


         plusTextView.setOnClickListener(this);
         minusTextView.setOnClickListener(this);

        cartImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long rowId = myDatabasehelper.insertData(id,name,  String.valueOf(itemCount), price);
                if(rowId>0){
                    Toast.makeText(getApplicationContext(), "Successfully added to your cart", Toast.LENGTH_SHORT).show();
                }
                else {
                    myDatabasehelper.updateData(id, name, String.valueOf(itemCount), price);
                    Toast.makeText(getApplicationContext(), "Successfully chart updated!", Toast.LENGTH_SHORT).show();

                }
                Intent intent = new Intent(getApplicationContext(), Cart.class);
                startActivity(intent);
                finish();
            }
        });


    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.plusTextViewid){
            itemCount++;
            countTextView.setText(itemCount+"");
            int currentPrice = Integer.parseInt(price)*itemCount;
            orderPriceTextView.setText("Price: "+currentPrice+"tk");
        }
        else if(view.getId() == R.id.minusTextViewid){
            if(itemCount>1){
                itemCount--;
                countTextView.setText(itemCount+"");
                int currentPrice = Integer.parseInt(price)*itemCount;
                orderPriceTextView.setText("Price: "+currentPrice+"tk");
            }
            else {
                Toast.makeText(getApplicationContext(), "You must select at least 1 Item", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
