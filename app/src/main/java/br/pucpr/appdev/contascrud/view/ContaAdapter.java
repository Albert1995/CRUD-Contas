package br.pucpr.appdev.contascrud.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.pucpr.appdev.contascrud.R;
import br.pucpr.appdev.contascrud.model.Conta;
import br.pucpr.appdev.contascrud.model.DataStore;

public class ContaAdapter extends RecyclerView.Adapter<ContaAdapter.ContaHolder> {

    private List<Conta> contas;

    public ContaAdapter() {
        contas = DataStore.getInstance().getAllContas();
    }

    @NonNull
    @Override
    public ContaHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.conta_item, viewGroup, false);

        return new ContaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContaHolder holder, int position) {
        Conta c = contas.get(position);
        holder.descricao.setText(c.getDescricao());
        holder.valor.setText("$ " + String.valueOf(c.getValor()));
    }

    @Override
    public int getItemCount() {
        return contas.size();
    }

    class ContaHolder extends RecyclerView.ViewHolder {

        TextView descricao;
        TextView valor;

        public ContaHolder(View itemView) {
            super(itemView);

            descricao = itemView.findViewById(R.id.txtViewDescricao);
            valor = itemView.findViewById(R.id.txtViewValor);
        }
    }

}
