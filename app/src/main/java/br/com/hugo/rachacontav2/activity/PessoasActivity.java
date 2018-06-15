package br.com.hugo.rachacontav2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import br.com.hugo.rachacontav2.R;
import br.com.hugo.rachacontav2.adapter.PessoaAdapter;
import br.com.hugo.rachacontav2.model.Pessoa;

public class PessoasActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Pessoa> pessoas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerViewId);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.btnAddPessoaId);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PessoasActivity.this, AddPessoaActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bundle dados = data.getExtras();

        if(dados !=null){

            Pessoa pessoa = (Pessoa)dados.getSerializable("pessoa");
            pessoas.add(pessoa);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        carregarPessoas();
    }

    // PROCEDIMENTO PARA CARREGAR O RECYCLERVIEW
    public void carregarPessoas(){

        // CARREGAR A LISTA
        //Pessoa p = new Pessoa();
        //p.setNome("Kleber");
        //pessoas.add(p);

        // CONFIGURAR O ADAPTER
        PessoaAdapter pessoaAdapter = new PessoaAdapter(pessoas);

        // CONFIGURAR O RECYCLERVIEW
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(),
                LinearLayout.VERTICAL));
        recyclerView.setAdapter(pessoaAdapter);


    }

}
