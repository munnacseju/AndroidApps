package jutecbd.munna.julibrary.ui.BusinessStadies;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import jutecbd.munna.julibrary.R;
import jutecbd.munna.julibrary.Test;

public class GalleryFragment extends Fragment{

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);


        root.findViewById(R.id.financeLinearLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Test.class);
                intent.putExtra("depertment", "Finance");
                startActivity(intent);
            }
        });

        root.findViewById(R.id.accountingLinearLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Test.class);
                intent.putExtra("depertment", "Accounting");
                startActivity(intent);
            }
        });

        root.findViewById(R.id.managementLinearLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Test.class);
                intent.putExtra("depertment", "Management");
                startActivity(intent);
            }
        });

        root.findViewById(R.id.marketingLinearLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Test.class);
                intent.putExtra("depertment", "Marketing");
                startActivity(intent);
            }
        });

        return root;
    }

}