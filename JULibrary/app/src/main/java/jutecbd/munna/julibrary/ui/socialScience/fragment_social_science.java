package jutecbd.munna.julibrary.ui.socialScience;

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

public class fragment_social_science extends Fragment {

    public static fragment_social_science newInstance() {
        return new fragment_social_science();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_social_science, container, false);

        root.findViewById(R.id.economicsLinearLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Test.class);
                intent.putExtra("depertment", "Economics");
                startActivity(intent);
            }
        });

        root.findViewById(R.id.urpLinearLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Test.class);
                intent.putExtra("depertment", "URP");
                startActivity(intent);
            }
        });

        root.findViewById(R.id.anthropologyLinearLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Test.class);
                intent.putExtra("depertment", "Anthopology");
                startActivity(intent);
            }
        });


        root.findViewById(R.id.geographyLinearLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Test.class);
                intent.putExtra("depertment", "Geography");
                startActivity(intent);
            }
        });


        root.findViewById(R.id.gpLinearLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Test.class);
                intent.putExtra("depertment", "GP");
                startActivity(intent);
            }
        });

        root.findViewById(R.id.publicAddLinearLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Test.class);
                intent.putExtra("depertment", "PA");
                startActivity(intent);
            }
        });

        return root;
    }
}
