package com.example.mdbinventory;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

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

        // sets values in the cell to the data in our socials list for each social
        //((Item) viewHolder).eventName.setText(s.getEventName());

        ((Item) viewHolder).nameOfPurchase.setText(p.getName());
        ((Item) viewHolder).date.setText(p.getDate());
        ((Item) viewHolder).price.setText(p.getCost());
    }

    @Override
    public int getItemCount() {
        return purchases.size();
    }


    // class that holds all cell values in recycler view
    public class Item extends RecyclerView.ViewHolder implements View.OnClickListener {
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
        }

        // handles when cell is clicked on
        @Override
        public void onClick(View v) {
//            Purchase purchase = purchases.get(getAdapterPosition());
//            Intent intent = new Intent(context, SocialDetail.class);
//            intent.putExtra();
//            context.startActivity(intent);
        }
    }
}
