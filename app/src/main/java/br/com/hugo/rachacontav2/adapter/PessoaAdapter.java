package br.com.hugo.rachacontav2.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.hugo.rachacontav2.R;
import br.com.hugo.rachacontav2.model.Pessoa;

public class PessoaAdapter extends RecyclerView.Adapter<PessoaAdapter.MyViewHolder>{

    private List<Pessoa> lista;

    public PessoaAdapter(List<Pessoa> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lista_pessoa_item, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Pessoa item = lista.get(position);
        holder.txtNome.setText(item.getName());
        if(item.getFoto() != null) {
            Bitmap raw  = BitmapFactory.decodeByteArray(item.getFoto(),0,
                    item.getFoto().length);
            holder.imgFoto.setImageBitmap(raw);
        }
    }

    @Override
    public int getItemCount() {
        return this.lista.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView txtNome;
        ImageView imgFoto;

        public MyViewHolder(View itemView) {
            super(itemView);

            txtNome = itemView.findViewById(R.id.txtNomeId);
            imgFoto = itemView.findViewById(R.id.imgFotoId);
        }
    }
}
