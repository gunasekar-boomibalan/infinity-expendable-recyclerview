package com.android.pbp.expendablerecyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private ArrayList<Element> listItem;
    private ArrayList<Element> baseItem;

    public RecyclerViewAdapter(ArrayList<Element> item, ArrayList<Element> baseItem) {
        this.listItem = item;
        this.baseItem = baseItem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        StringBuilder space=new StringBuilder();
        space.append("");
        for(int i= 0; i < listItem.get(position).getLevel();i++){
            space.append("\t\t\t");
        }

        holder.textView.setText(space.toString()+listItem.get(position).getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Element element = listItem.get(position);
                ArrayList<Element> mElements = listItem;
                if (!element.isHasChild()) {
                    collapseOthers(element, mElements);
                    Toast.makeText(holder.itemView.getContext(),element.getTitle()+" dosen't have child",Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                    return;
                }

                if (element.isExtended()) {
                    collapse(position, element, mElements);
                } else {
                    expend(position, element, mElements, baseItem);
                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.title);
        }
    }

    private void expend(int position, Element element, ArrayList<Element> elements, ArrayList<Element> elementsData) {
        element.setExtended(true);
        int i = 1;
        for (Element e : elementsData) {
            if (e.getParentId().equalsIgnoreCase(element.getId())) {
                e.setExtended(false);
                elements.add(position + i, e);
                i++;
            }
        }
        collapseOthers(element, elements);
    }

    private void collapseOthers(Element element, ArrayList<Element> elements) {
        ArrayList<String> parents = new ArrayList<>();
        getParents(element.getId(), elements, parents);
        for (int j = 0; j < elements.size(); j++) {
            if (!parents.contains(elements.get(j).getId())) {
                collapse(j, elements.get(j), elements);
            }
        }
    }

    private void getParents(String id, ArrayList<Element> elements, ArrayList<String> parents) {
        for (int j = 0; j < elements.size(); j++) {
            if (elements.get(j).getId().equalsIgnoreCase(id)) {
                parents.add(elements.get(j).getId());
                if (!elements.get(j).getParentId().equalsIgnoreCase("A")) {
                    getParents(elements.get(j).getParentId(), elements, parents);
                }
            }
        }
    }

    private void collapse(int position, Element element, ArrayList<Element> elements) {
        element.setExtended(false);
        //Delete the corresponding sub-node data within the node, including the sub-node of the sub-node.
        ArrayList<Element> elementsToDel = new ArrayList<Element>();
        for (int i = position + 1; i < elements.size(); i++) {
            if (element.getLevel() >= elements.get(i).getLevel())
                break;
            elementsToDel.add(elements.get(i));
        }
        elements.removeAll(elementsToDel);
    }

}
