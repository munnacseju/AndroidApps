package jutecbd.munna.julibrary.ui.IRSG;

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

public class IRSG extends Fragment {


    public static IRSG newInstance() {
        return new IRSG();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.irsg_fragment, container, false);

        root.findViewById(R.id.irsgLinearLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Test.class);
                intent.putExtra("depertment", "IRSG");
                startActivity(intent);
            }
        });
        return root;
    }



}
