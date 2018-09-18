package com.sinbaddrinkshop.drinkshop.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.sinbaddrinkshop.drinkshop.Model.Drink;
import com.sinbaddrinkshop.drinkshop.R;
import com.sinbaddrinkshop.drinkshop.utils.Common;

import java.util.List;

public class MultiChoiceAdapter extends RecyclerView.Adapter<MultiChoiceAdapter.MultiChoiceViewHolder> {

    Context context;
    List<Drink> optionList;

    public MultiChoiceAdapter(Context context, List<Drink> optionList) {
        this.context = context;
        this.optionList = optionList;
    }

    @Override
    public MultiChoiceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.multi_check_layout, null);
        return new MultiChoiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MultiChoiceViewHolder holder, final int position) {
        holder.checkBox.setText(optionList.get(position).getName());

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Common.toppingAddress.add(buttonView.getText().toString());
                    Common.topping += Double.parseDouble(String.valueOf(optionList.get(position).getPrice()));
                } else {
                    Common.toppingAddress.remove(buttonView.getText().toString());
                    Common.topping -= Double.parseDouble(String.valueOf(optionList.get(position).getPrice()));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return optionList.size();
    }

    class MultiChoiceViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;

        public MultiChoiceViewHolder(View itemView) {
            super(itemView);

            checkBox = (CheckBox) itemView.findViewById(R.id.multi_check);
        }
    }
}
