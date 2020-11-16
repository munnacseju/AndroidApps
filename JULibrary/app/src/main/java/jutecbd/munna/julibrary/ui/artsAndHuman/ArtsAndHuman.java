package jutecbd.munna.julibrary.ui.artsAndHuman;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import jutecbd.munna.julibrary.R;
import jutecbd.munna.julibrary.Test;

public class ArtsAndHuman extends Fragment implements View.OnClickListener {

    LinearLayout  archilinearLayout, englinearLayout, dramalinearLayout, hislinearLayout, irlinearLayout, jourlinearLayout, phylolinearLayout, banglalinearLayout;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.arts_and_human_fragment, container, false);

        archilinearLayout = root.findViewById(R.id.archiLinearLayout);
        englinearLayout = root.findViewById(R.id.engLinearLayout);
        dramalinearLayout = root.findViewById(R.id.dramaLinearLayout);
        hislinearLayout = root.findViewById(R.id.hisLinearLayout);
        irlinearLayout = root.findViewById(R.id.irLinearLayout);
        jourlinearLayout = root.findViewById(R.id.jourLinearLayout);
        phylolinearLayout = root.findViewById(R.id.phyloLinearLayout);
        banglalinearLayout = root.findViewById(R.id.bangLinearLayout);


        banglalinearLayout.setOnClickListener(this);
        archilinearLayout.setOnClickListener(this);
        englinearLayout.setOnClickListener(this);
        dramalinearLayout.setOnClickListener(this);
        hislinearLayout.setOnClickListener(this);
        irlinearLayout.setOnClickListener(this);
        jourlinearLayout.setOnClickListener(this);
        phylolinearLayout.setOnClickListener(this);
        root.findViewById(R.id.fineArtsLinearLayout).setOnClickListener(this);

        return root;
    }


    @Override
    public void onClick(View view) {

        if (view.getId()==R.id.bangLinearLayout){
            Intent intent = new Intent(getContext(), Test.class);
            intent.putExtra("depertment", "Bangla");
            startActivity(intent);
        }

        if(view.getId()==R.id.archiLinearLayout){
            Intent intent = new Intent(getContext(), Test.class);
            intent.putExtra("depertment", "Archaeology");
            startActivity(intent);
        }

        else if(view.getId()==R.id.engLinearLayout){
            Intent intent = new Intent(getContext(), Test.class);
            intent.putExtra("depertment", "English");
            startActivity(intent);
        }

        else if(view.getId()==R.id.dramaLinearLayout){
            Intent intent = new Intent(getContext(), Test.class);
            intent.putExtra("depertment", "Drama");
            startActivity(intent);
        }

        else if(view.getId()==R.id.phyloLinearLayout){
            Intent intent = new Intent(getContext(), Test.class);
            intent.putExtra("depertment", "Philosophy");
            startActivity(intent);
        }

        else if(view.getId()==R.id.irLinearLayout){
            Intent intent = new Intent(getContext(), Test.class);
            intent.putExtra("depertment", "IR");
            startActivity(intent);
        }

        else if(view.getId()==R.id.jourLinearLayout){
            Intent intent = new Intent(getContext(), Test.class);
            intent.putExtra("depertment", "Journalism");
            startActivity(intent);
        }

        else if(view.getId()==R.id.hisLinearLayout){
            Intent intent = new Intent(getContext(), Test.class);
            intent.putExtra("depertment", "Histry");
            startActivity(intent);
        }

        else if(view.getId()==R.id.fineArtsLinearLayout){
            Intent intent = new Intent(getContext(), Test.class);
            intent.putExtra("depertment", "FineArts");
            startActivity(intent);
        }
    }
}
