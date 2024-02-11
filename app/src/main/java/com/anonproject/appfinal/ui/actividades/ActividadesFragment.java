package com.anonproject.appfinal.ui.actividades;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.anonproject.appfinal.AddActividades;
import com.anonproject.appfinal.ListaActividades;

import com.anonproject.appfinal.R;
import com.anonproject.appfinal.databinding.FragmentActividadesBinding;


public class ActividadesFragment extends Fragment {

    private FragmentActividadesBinding binding;
    ImageView addActivity;
    ImageView showActivity;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ActividadesViewModel actividadesViewModel = new ViewModelProvider(this).get(ActividadesViewModel.class);

        binding = FragmentActividadesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        addActivity = root.findViewById(R.id.image_add_activity);
        showActivity = root.findViewById(R.id.image_show_activity);
        addActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Añadir Actividad",Toast.LENGTH_LONG).show();
                Intent intent= new Intent(view.getContext(), AddActividades.class);
                startActivity(intent);
            }
        });

        showActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Mostrar Actividad",Toast.LENGTH_LONG).show();
                Intent intent= new Intent(view.getContext(), ListaActividades.class);
                startActivity(intent);
            }
        });
        return root;

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}