package jutecbd.munna.julibrary;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageView fab = findViewById(R.id.fab);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Cart View", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent intent = new Intent(getApplicationContext(), Cart.class);
                startActivity(intent);
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery_businessStadies, R.id.nav_slideshow_low,
                R.id.nav_tools_biologicalScience, R.id.nav_share_IIT, R.id.nav_send_IBA,
                R.id.nav_socialScience, R.id.nav_artsAndHuman, R.id.nav_irsg, R.id.nav_biclc)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.bookRegistrationMenu){
            Intent intent = new Intent(getApplicationContext(), BookRegistration.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.logOutMenu){
            SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("phone", "");
            editor.commit();
            Intent intent = new Intent(getApplicationContext(), Register.class);
            startActivity(intent);
            finish();
        }
        else if(item.getItemId()==R.id.aboutMenu){
            Intent intent = new Intent(getApplicationContext(), AboutUs.class);
            startActivity(intent);
        }

        else if(item.getItemId()==R.id.shareMenu){
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            String subject = "JU Library Apps help you very much";
            String text = "Hey!\n" +
                    "https://play.google.com/store/apps/details?id=jutecbd.munna.julibrary";
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, text);
            startActivity(Intent.createChooser(intent, "share"));
            Toast.makeText(getApplicationContext(), "Share Your Friend About Quiz", Toast.LENGTH_SHORT).show();
        }

        else if(item.getItemId() == R.id.orderListMenu){
            Intent intent = new Intent(getApplicationContext(), checkOrder.class);
            startActivity(intent);
        }

        else if(item.getItemId() == R.id.myProfileMenu){
            Intent intent = new Intent(getApplicationContext(), MyDetails.class);
            startActivity(intent);
            finish();
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();

        MyDatabasehelper myDatabasehelper = new MyDatabasehelper(this);
        Cursor cursor = myDatabasehelper.showData();

        TextView textView = findViewById(R.id.cartWithCountTextViewid);
        textView.setText(cursor.getCount()+"");
        Toast.makeText(getApplicationContext(), "You have "+cursor.getCount()+" Item in cart", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Are You sure, you want to exit?").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        return;
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.setTitle("Exit!");
        alertDialog.setIcon(R.drawable.test);
        alertDialog.show();

    }
}
