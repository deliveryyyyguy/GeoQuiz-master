package com.example.ricky.geoquiz.activities.materialtest;

/**
 * Created by Ricky on 5/17/2015.

public class AdapterBoxOffice extends RecyclerView.Adapter<AdapterBoxOffice.ViewHolderBoxOffice> {

    private LayoutInflater layoutInflater;
    List<Information> data = new ArrayList<>();
    private Context context;

    public AdapterBoxOffice(Context context, List<Information> data){
        layoutInflater= LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }

    public void setUpcoming(ArrayList<Information> listUpcoming){
        this.data = listUpcoming;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolderBoxOffice onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.custom_row, parent, false);
        ViewHolderBoxOffice viewHolder = new ViewHolderBoxOffice(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolderBoxOffice holder, int position) {
        Information current = data.get(position);
        holder.title.setText(current.title);
        holder.icon.setImageResource(current.iconId);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolderBoxOffice extends RecyclerView.ViewHolder {
        TextView title;
        ImageView icon;
        private Button button1;

        public ViewHolderBoxOffice(View itemView) {
            super(itemView);
            title= (TextView) itemView.findViewById(R.id.listText);
            icon= (ImageView) itemView.findViewById(R.id.listIcon);
            button1 = (Button) itemView.findViewById(R.id.card_view_button1);


        }
    }
}
 */