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

public class CheckOrderAdapter extends BaseAdapter {
    private Context context;
    private List<OrderHandle> orderHandleList;


    public CheckOrderAdapter(Context context, List<OrderHandle> orderHandleList) {
        this.context = context;
        this.orderHandleList = orderHandleList;
    }

    @Override
    public int getCount() {
        return orderHandleList.size();
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
        view = layoutInflater.inflate(R.layout.sample_check_order, viewGroup, false);


         TextView totalPriceTextView, bookIdTextView, nameTextView, hallTextView, departmentTextView, phoneTextView, batchTextView, bookNameTextView, priceTextView, itemNoTextView;
         bookIdTextView = view.findViewById(R.id.checkOrderBookid);
         nameTextView = view.findViewById(R.id.checkOrdername);
         hallTextView = view.findViewById(R.id.checkOrderHall);
         departmentTextView = view.findViewById(R.id.checkOrderDept);
         phoneTextView = view.findViewById(R.id.checkOrderPhone);
         batchTextView = view.findViewById(R.id.checkOrderBatch);
         bookNameTextView = view.findViewById(R.id.checkOrderBookName);
         priceTextView = view.findViewById(R.id.checkOrderPrice);
         itemNoTextView = view.findViewById(R.id.checkOrderItemNumber);
         totalPriceTextView = view.findViewById(R.id.checkOrderTotalPrice);

         OrderHandle orderHandle = orderHandleList.get(i);

         bookIdTextView.setText(orderHandle.getBookid());
         nameTextView.setText(orderHandle.getName());
         hallTextView.setText(orderHandle.getHall());
         departmentTextView.setText(orderHandle.getDep());
         phoneTextView.setText(orderHandle.getPhone());
         batchTextView.setText(orderHandle.getBatch());
         bookNameTextView.setText(orderHandle.getBookNames());
         priceTextView.setText(orderHandle.getPrices());
         itemNoTextView.setText(orderHandle.getIterNumber());
         totalPriceTextView.setText(orderHandle.getTotalPrice());

        return view;
    }


}
