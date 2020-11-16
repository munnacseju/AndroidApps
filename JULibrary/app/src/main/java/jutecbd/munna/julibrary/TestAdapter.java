package jutecbd.munna.julibrary;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class TestAdapter extends BaseAdapter {

    private Context context;
    private List<BookRegistrationHandle> bookRegistrationHandleList;

    public TestAdapter(Context context, List<BookRegistrationHandle> bookRegistrationHandleList) {
        this.context = context;
        this.bookRegistrationHandleList = bookRegistrationHandleList;
    }

    @Override
    public int getCount() {
        return bookRegistrationHandleList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.sample_book_list, viewGroup, false);


        TextView nameTextView = view.findViewById(R.id.nameTextViewid);
        TextView writerTextView = view.findViewById(R.id.writerNameTextViewid);
        TextView priceTextView = view.findViewById(R.id.priceTextViewid);
        TextView vertionTextView = view.findViewById(R.id.versionTextViewid);
        TextView departmentTextView = view.findViewById(R.id.departmentTextViewid);
        TextView languageTextView = view.findViewById(R.id.languageTextViewid);
        ImageView imageView = view.findViewById(R.id.imageViewid);

        nameTextView.setText(bookRegistrationHandleList.get(i).getName());
        writerTextView.setText(bookRegistrationHandleList.get(i).getWriterName());
        priceTextView.setText(bookRegistrationHandleList.get(i).getPrice());
        vertionTextView.setText(bookRegistrationHandleList.get(i).getVersion());
        departmentTextView.setText(bookRegistrationHandleList.get(i).getDepartmentName());
        languageTextView.setText(bookRegistrationHandleList.get(i).getLanguage());


        String imageDownloadUri = bookRegistrationHandleList.get(i).getImageDownloadUri();

        //imageView.setImageResource(Integer.parseInt(imageDownloadUri));


        Picasso.with(context)
                .load(imageDownloadUri)
                .placeholder(R.drawable.loading_image)
                .into(imageView);

        return view;
    }

}
