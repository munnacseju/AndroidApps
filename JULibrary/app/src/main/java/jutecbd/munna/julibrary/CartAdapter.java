package jutecbd.munna.julibrary;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class CartAdapter extends BaseAdapter {
    private TextView plusTextView;
    private TextView minushTextView;
    private Context context;
    private List<cartHandle> cartHandleList;
    private int itemCount = 0;
    private int itemNO = 0;
    MyDatabasehelper myDatabasehelper;

    public CartAdapter(Context context, List<cartHandle> cartHandleList) {
        this.context = context;
        this.cartHandleList = cartHandleList;
    }

    @Override
    public int getCount() {
        return cartHandleList.size();
    }

    @Override
    public Object getItem(int i)
    {
        return i;
    }

    @Override
    public long getItemId(int i) {

        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.sample_cart, viewGroup, false);

        final TextView getItemTextView = view.findViewById(R.id.getItemid);
        getItemTextView.setText(i+"");

        final TextView nameTextView = view.findViewById(R.id.cartNameTextViewid);
        final TextView priceTextView = view.findViewById(R.id.cartPriceTextViewid);
        final TextView countTextView = view.findViewById(R.id.cartCountTextViewid);
        ImageView deleteImageView = view.findViewById(R.id.cartDeleteImageViewid);
        plusTextView = view.findViewById(R.id.cartPlustTextViewid);
        minushTextView = view.findViewById(R.id.cartCountMinusViewid);


        nameTextView.setText(cartHandleList.get(i).getName());
        int itemPrice = Integer.parseInt(cartHandleList.get(i).getItemCount())*Integer.parseInt(cartHandleList.get(i).getPrice());
        priceTextView.setText("Price: "+itemPrice+"tk");
        countTextView.setText(cartHandleList.get(i).getItemCount());
        itemCount = Integer.parseInt(cartHandleList.get(i).getItemCount());

        final cartHandle cartHandle = cartHandleList.get(i);
        cartHandle.setItemNo(i);
        cartHandleList.set(i, cartHandle);


        plusTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemNO = Integer.parseInt(getItemTextView.getText().toString().trim());
                myDatabasehelper = new MyDatabasehelper(context);

                itemCount = Integer.parseInt(countTextView.getText().toString());
                itemCount++;
                int itemPrice = itemCount*Integer.parseInt(cartHandleList.get(itemNO).getPrice());
                priceTextView.setText("Price: "+itemPrice+"tk");
                countTextView.setText(itemCount+"");
                myDatabasehelper.updateData(cartHandleList.get(itemNO).getId(), cartHandleList.get(itemNO).getName(), itemCount+"", cartHandleList.get(itemNO).getPrice());

            }
        });


        minushTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemNO = Integer.parseInt(getItemTextView.getText().toString().trim());

                myDatabasehelper = new MyDatabasehelper(context);
                itemCount = Integer.parseInt(countTextView.getText().toString());
                if(itemCount<2){
                    Toast.makeText(context, "Sorry! You have only one item ", Toast.LENGTH_SHORT).show();
                }
                else {
                    itemCount--;
                    int itemPrice = itemCount*Integer.parseInt(cartHandleList.get(itemNO).getPrice());
                    priceTextView.setText("Price: "+itemPrice+"tk");
                    countTextView.setText(itemCount+"");
                    myDatabasehelper.updateData(cartHandleList.get(itemNO).getId(), cartHandleList.get(itemNO).getName(), itemCount+"", cartHandleList.get(itemNO).getPrice());
                }
            }
        });

        deleteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemNO = Integer.parseInt(getItemTextView.getText().toString().trim());
                alertDialogBuilderRun("Do you want to delete "+cartHandleList.get(itemNO).getName()+" ?");
            }
        });

        return view;
    }


    public void alertDialogBuilderRun(String string){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setMessage(string)
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        myDatabasehelper = new MyDatabasehelper(context);
                        myDatabasehelper.deleteDAta(cartHandleList.get(itemNO).getId());
                        cartHandleList.remove(itemNO);
                        notifyDataSetChanged();

                        /*Intent intent = new Intent(context, Cart.class);
                        context.startActivity(intent);
                        ((Activity) context).finish();


                         */


                        dialogInterface.cancel();
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
    }


}
