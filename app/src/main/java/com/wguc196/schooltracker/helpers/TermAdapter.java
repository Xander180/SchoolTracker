package com.wguc196.schooltracker.helpers;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wguc196.schooltracker.R;
import com.wguc196.schooltracker.UI.TermDetailsActivity;
import com.wguc196.schooltracker.entities.Term;

import java.util.List;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermViewHolder> {

    private List<Term> mTerms;
    private final Context context;
    private final LayoutInflater mInflater;

    public TermAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    public class TermViewHolder extends RecyclerView.ViewHolder {
        private final TextView termItemView;

        public TermViewHolder(@NonNull View itemView) {
            super(itemView);
            termItemView = itemView.findViewById(R.id.item_button);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final Term current = mTerms.get(position);
                    Intent intent = new Intent(context, TermDetailsActivity.class);
                    intent.putExtra("termID", current.getTermID());
                    intent.putExtra("title", current.getTitle());
                    intent.putExtra("startDate", current.getStartDate());
                    intent.putExtra("endDate", current.getEndDate());
                    context.startActivity(intent);
                }
            });
        }
    }
    @NonNull
    @Override
    public TermAdapter.TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.list_item_primary, parent, false);
        return new TermViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TermAdapter.TermViewHolder holder, int position) {
        if (mTerms != null) {
            Term current = mTerms.get(position);
            String name = current.getTitle();
            holder.termItemView.setText(name);
        } else {
            holder.termItemView.setText("No term title.");
        }
    }

    @Override
    public int getItemCount() {
        if (mTerms != null) {
            return mTerms.size();
        }

        return 0;
    }

    public void setTerms(List<Term> terms) {
        mTerms = terms;
        notifyDataSetChanged();
    }

}
