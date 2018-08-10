package br.pucpr.appdev.contascrud.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import br.pucpr.appdev.contascrud.R;
import br.pucpr.appdev.contascrud.controller.FormActivity;
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
    public void onBindViewHolder(@NonNull ContaHolder holder, final int position) {
        final Conta c = contas.get(position);
        holder.descricao.setText(c.getDescricao());
        holder.valor.setText("$ " + String.valueOf(c.getValor()));
        holder.tipo.setText("[ " + c.getTipo() + " - " + c.getFormaPagamento().getNome() + " ]");

        final Activity ctx = (Activity) holder.itemView.getContext();

        holder.removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(ctx);

                alertBuilder.setTitle("Você deseja remover a conta " + c.getDescricao() + "?");
                alertBuilder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DataStore.getInstance().removeConta(position);
                        notifyDataSetChanged();
                    }
                });
                alertBuilder.setNegativeButton("Não", null);
                alertBuilder.show();
            }
        });

        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ctx, FormActivity.class);
                i.putExtra("conta", c);
                i.putExtra("position", position);
                ctx.startActivityForResult(i, 2000);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contas.size();
    }

    class ContaHolder extends RecyclerView.ViewHolder {

        TextView descricao, valor, tipo;
        ImageButton removeBtn, editBtn;

        public ContaHolder(View itemView) {
            super(itemView);

            descricao = itemView.findViewById(R.id.txtViewDescricao);
            valor = itemView.findViewById(R.id.txtViewValor);
            tipo = itemView.findViewById(R.id.txtViewTipo);
            removeBtn = itemView.findViewById(R.id.removeConta);
            editBtn = itemView.findViewById(R.id.editConta);
        }
    }

}
