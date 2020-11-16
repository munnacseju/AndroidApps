package jutecbd.munna.julibrary.ui.BICLC;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jutecbd.munna.julibrary.R;
import jutecbd.munna.julibrary.Test;

public class BICLC extends Fragment {


    public static BICLC newInstance() {
        return new BICLC();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.biclc_fragment, container, false);

        root.findViewById(R.id.biclcLinearLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Test.class);
                intent.putExtra("depertment", "BICLC");
                startActivity(intent);
            }
        });
    return root;
    }


}
