package jutecbd.munna.julibrary.ui.BiologicalScience;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import jutecbd.munna.julibrary.R;
import jutecbd.munna.julibrary.Test;

public class ToolsFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tools, container, false);


        root.findViewById(R.id.botanyLinearLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Test.class);
                intent.putExtra("depertment", "Botany");
                startActivity(intent);
            }
        });

        root.findViewById(R.id.bioChemistryLinearLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Test.class);
                intent.putExtra("depertment", "Biochemistry");
                startActivity(intent);
            }
        });

        root.findViewById(R.id.zoologyLinearLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Test.class);
                intent.putExtra("depertment", "Zoology");
                startActivity(intent);
            }
        });

        root.findViewById(R.id.pharmacyLinearLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Test.class);
                intent.putExtra("depertment", "Pharmacy");
                startActivity(intent);
            }
        });

        root.findViewById(R.id.microBiologyticsLinearLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Test.class);
                intent.putExtra("depertment", "Microbiology");
                startActivity(intent);
            }
        });

        root.findViewById(R.id.bioTechnologyLinearLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Test.class);
                intent.putExtra("depertment", "Biotechnology");
                startActivity(intent);
            }
        });

        root.findViewById(R.id.publicHeltLinearLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Test.class);
                intent.putExtra("depertment", "PHI");
                startActivity(intent);
            }
        });


        return root;
    }
}