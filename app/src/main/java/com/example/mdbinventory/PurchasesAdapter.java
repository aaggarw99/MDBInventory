package com.example.mdbinventory;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class PurchasesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    ArrayList<Purchase> purchases;

    public PurchasesAdapter(Context context, ArrayList<Purchase> purchases) {
        // passes in a context and list of social objects
        this.context = context;
        this.purchases = purchases;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        // our custom cell layout (card.xml)
        View row = inflater.inflate(R.layout.card, viewGroup, false);
        Item item = new Item(row);
        return item;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Purchase p = purchases.get(i);

        ((Item) viewHolder).nameOfPurchase.setText(p.getName());
        ((Item) viewHolder).date.setText(p.getDate());

        String price = "$" + Integer.toString(p.getCost());
        ((Item) viewHolder).price.setText(price);
    }

    @Override
    public int getItemCount() {
        return purchases.size();
    }


    // class that holds all cell values in recycler view
    public class Item extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public TextView nameOfPurchase;
        public TextView date;
        public TextView price;


        public Item(View itemView) {
            super(itemView);
            nameOfPurchase = itemView.findViewById(R.id.nameOfPurchase);
            date = itemView.findViewById(R.id.date);
            price = itemView.findViewById(R.id.cost);

            // this allows the cell to be clicked
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        // handles when cell is clicked on
        @Override
        public void onClick(View v) {
//            Purchase purchase = purchases.get(getAdapterPosition());
//            Intent intent = new Intent(context, SocialDetail.class);
//            intent.putExtra();
//            context.startActivity(intent);
        }

        @Override
        public boolean onLongClick(final View v) {
            final Purchase p = purchases.get(getAdapterPosition());

            AlertDialog.Builder builder = new AlertDialog.Builder(v.getRootView().getContext());
            builder.setMessage("Are you sure you want to delete this item?")
                    .setCancelable(false)
                    .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            removeAt(getAdapterPosition(), v.getRootView().getContext());

                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
            return true;
        }
    }

    public void removeAt(int position, Context context) {
        DatabaseHelper mDatabaseHelper = new DatabaseHelper(context);
        Purchase p = purchases.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, purchases.size());

        mDatabaseHelper.deleteitem(p.getName());
    }

    public void setSearchOperation(List<Purchase> newList) {
        purchases = new ArrayList<>();
        purchases.addAll(newList);
        notifyDataSetChanged();
    }
}
