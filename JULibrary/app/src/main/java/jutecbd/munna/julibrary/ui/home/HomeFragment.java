package jutecbd.munna.julibrary.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import jutecbd.munna.julibrary.MainActivity;
import jutecbd.munna.julibrary.R;
import jutecbd.munna.julibrary.Test;
import jutecbd.munna.julibrary.checkOrder;

public class HomeFragment extends Fragment {

    private LinearLayout cseLinearLayout, mathLinearLayout, statLinearLayout, physicsLinearLayout, chemistryLinearLayout, geologyLinearLayout, invironmentalSciencesLinearLayout;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);


        root.findViewById(R.id.cseLinearLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Test.class);
                intent.putExtra("depertment", "CSE");
                startActivity(intent);
            }
        });

        root.findViewById(R.id.mathLinearLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Test.class);
                intent.putExtra("depertment", "Math");
                startActivity(intent);
            }
        });

        root.findViewById(R.id.statisticsLinearLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Test.class);
                intent.putExtra("depertment", "Statistics");
                startActivity(intent);
            }
        });

        root.findViewById(R.id.physicsLinearLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Test.class);
                intent.putExtra("depertment", "Physics");
                startActivity(intent);
            }
        });

        root.findViewById(R.id.cheLinearLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Test.class);
                intent.putExtra("depertment", "Chemistry");
                startActivity(intent);
            }
        });

        root.findViewById(R.id.geologyLinearLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Test.class);
                intent.putExtra("depertment", "GS");
                startActivity(intent);
            }
        });

        root.findViewById(R.id.environmenLinearLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Test.class);
                intent.putExtra("depertment", "Env");
                startActivity(intent);
            }
        });



        return root;
    }
}